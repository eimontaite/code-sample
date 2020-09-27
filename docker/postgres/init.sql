DROP TABLE IF EXISTS store_items;

ALTER USER postgres PASSWORD 'postgres';

CREATE TABLE store_items(
    id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255),
    content VARCHAR(1024),
    views INTEGER,
    timestamp INTEGER
)
WITH (
  OIDS=FALSE
);


