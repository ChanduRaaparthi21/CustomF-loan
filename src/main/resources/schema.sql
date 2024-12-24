CREATE TABLE loans (
    id BIGSERIAL PRIMARY KEY,
    borrower_name VARCHAR(255) NOT NULL,
    amount NUMERIC(15, 2) NOT NULL,
    status VARCHAR(50) NOT NULL
);
