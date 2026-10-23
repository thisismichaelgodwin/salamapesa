package dev.lusungu.salamapesa.payments.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "idempotency")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Idempotency {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "key", nullable = false)
    private UUID key;


    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "version", nullable = false)
    @Version
    private Long version;


}