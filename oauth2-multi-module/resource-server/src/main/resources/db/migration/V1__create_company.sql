DROP TABLE IF EXISTS company;
CREATE TABLE company (
  id     VARCHAR(255) PRIMARY KEY,
  name   VARCHAR(255) UNIQUE,
  active BOOLEAN
);

INSERT INTO company
VALUES ('1', 'Company_1', true),
       ('2', 'Company_2', false),
       ('3', 'Company_3', false);