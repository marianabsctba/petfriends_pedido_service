CREATE TABLE domain_event_entry (
    global_index BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_identifier VARCHAR(255) NOT NULL,
    aggregate_identifier VARCHAR(255) NOT NULL,
    sequence_number BIGINT NOT NULL,
    type VARCHAR(255) NOT NULL,
    payload BLOB NOT NULL,
    payload_revision VARCHAR(255),
    meta_data BLOB NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    time_stamp VARCHAR(255) NOT NULL
);

CREATE TABLE snapshot_event_entry (
    event_identifier VARCHAR(255) NOT NULL,
    aggregate_identifier VARCHAR(255) NOT NULL,
    sequence_number BIGINT NOT NULL,
    type VARCHAR(255) NOT NULL,
    payload BLOB NOT NULL,
    payload_revision VARCHAR(255),
    meta_data BLOB NOT NULL,
    event_type VARCHAR(255) NOT NULL,
    time_stamp VARCHAR(255) NOT NULL
);
