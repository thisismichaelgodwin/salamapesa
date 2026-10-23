package dev.lusungu.salamapesa.payments.services;

import dev.lusungu.salamapesa.payments.dtos.PaymentRequestDto;
import dev.lusungu.salamapesa.payments.models.Idempotency;
import dev.lusungu.salamapesa.payments.models.Payment;
import dev.lusungu.salamapesa.payments.repositories.IdempotencyRepository;
import dev.lusungu.salamapesa.payments.repositories.PaymentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final IdempotencyRepository idempotencyRepository;
    private final PaymentRepository paymentRepository;
    private final ExternalService externalService;

    @CircuitBreaker(name = "payment", fallbackMethod = "paymentFallback")
    @Retryable(delay = 1, maxDelay = 10, maxRetries = 5, jitter = 1, timeUnit = TimeUnit.SECONDS)
    @Transactional
    public String makePayment(PaymentRequestDto dto) {
        UUID userId = UUID.randomUUID();
        if (idempotencyRepository.existsByKey(dto.idempotencyKey())) {
            return "Payment already processed";
        } else {
            idempotencyRepository.save(Idempotency.builder().key(dto.idempotencyKey()).userId(userId).build());
        }

        if (Boolean.TRUE.equals(externalService.makePayment(dto))) {
            paymentRepository.save(Payment.builder()
                    .senderId(dto.senderId())
                    .receiverId(dto.receiverId())
                    .amount(dto.amount())
                    .status("Success")
                    .build());
            return "Payment is successful";
        } else {
            return "Payment has failed";
        }
    }

    private String paymentFallback(PaymentRequestDto dto, Throwable ex) {
        log.error(ex.getCause().getMessage());
        return "Payment has failed";
    }
}
