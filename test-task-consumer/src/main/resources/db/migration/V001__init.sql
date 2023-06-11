CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS ENRICHED_STRINGS
(
    ID          uuid PRIMARY KEY      DEFAULT uuid_generate_v4(),
    VALUE       varchar(101) not null,
    CREATE_DATE timestamp    not null DEFAULT NOW()
);
