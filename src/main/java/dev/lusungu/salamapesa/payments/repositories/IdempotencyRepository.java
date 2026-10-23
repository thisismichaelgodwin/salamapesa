package dev.lusungu.salamapesa.payments.repositories;

import dev.lusungu.salamapesa.payments.models.Idempotency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IdempotencyRepository extends JpaRepository<Idempotency, UUID> {

            boolean existsByUserIdAndKey(UUID userId, UUID key);

            boolean existsByKey(UUID key);
}
