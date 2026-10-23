package dev.lusungu.salamapesa.payments.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "sender_id", nullable = false)
    private UUID senderId;

    @NotNull
    @Column(name = "receiver_id", nullable = false)
    private UUID receiverId;

    @Column(name = "amount", precision = 40, scale = 10)
    private BigDecimal amount;

    @ColumnDefault("'Failure'")
    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @ColumnDefault("0")
    @Column(name = "version", nullable = false)
    @Version
    private Long version;


}