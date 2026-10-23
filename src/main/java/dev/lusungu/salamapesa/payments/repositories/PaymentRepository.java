package dev.lusungu.salamapesa.payments.repositories;

import dev.lusungu.salamapesa.payments.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
