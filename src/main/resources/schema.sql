DROP TABLE IF EXISTS "asset";

CREATE TABLE "asset"
(
  "id"   INT AUTO_INCREMENT
    PRIMARY KEY,
  "name" VARCHAR(100)   NOT NULL,
  "cost" DECIMAL(18, 2) NOT NULL,
  "type" VARCHAR(30)    NOT NULL
);