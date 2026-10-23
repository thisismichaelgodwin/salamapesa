package dev.lusungu.salamapesa.payments.services;

import dev.lusungu.salamapesa.payments.dtos.PaymentRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalService {


    public Boolean makePayment(PaymentRequestDto requestDto) {
        log.info("Payment is been processed for key {}", requestDto.idempotencyKey());
        return true;
    }


}
