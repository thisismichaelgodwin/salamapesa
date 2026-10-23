package dev.lusungu.salamapesa.payments.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PaymentRequestDto(
        @NotNull UUID senderId,
        @NotNull UUID receiverId,
        @NotNull @Positive BigDecimal amount,
        @NotNull UUID idempotencyKey
) {
}
