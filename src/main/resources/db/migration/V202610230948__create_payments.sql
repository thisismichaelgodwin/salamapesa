CREATE TABLE payments
(
    id          UUID PRIMARY KEY   DEFAULT UUIDV7(),
    sender_id   UUID      NOT NULL,
    receiver_id UUID      NOT NULL,
    amount      NUMERIC(40, 10),
    status      TEXT               DEFAULT 'Failure',
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    version     BIGINT    NOT NULL DEFAULT 0
);

CREATE TABLE idempotency
(
    id         UUID PRIMARY KEY   DEFAULT UUIDV7(),
    key        UUID      NOT NULL,
    user_id     UUID      NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    version    BIGINT    NOT NULL DEFAULT 0
);

CREATE UNIQUE INDEX idx_unique_keys ON idempotency (key, user_id);
CREATE INDEX idx_idempotency ON idempotency (key, user_id);

