CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE IF NOT EXISTS USERS(
    ID SERIAL PRIMARY KEY,
    FIRST_NAME VARCHAR(100) NOT NULL,
    LAST_NAME VARCHAR(100) NOT NULL,
    PROGRAM VARCHAR(20) NOT NULL
);


CREATE TABLE IF NOT EXISTS USER_ACTIONS (
    ID SERIAL PRIMARY KEY,
    TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    USER_ID INT NOT NULL,
    TYPE_ID INT NOT NULL,
    DESCRIPTION VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS TYPES (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS RULES (
 ID SERIAL PRIMARY KEY,
 PRIORITY INT NOT NULL,
 MESSAGE VARCHAR(100) NOT NULL,
 RULE VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS STORAGE (
  ID SERIAL,
  KEY VARCHAR(100) NOT NULL PRIMARY KEY,
  VALUE VARCHAR(1024) NOT NULL
);


CREATE TABLE IF NOT EXISTS USER_ACTION_RULE_HANDLED (
    ID SERIAL PRIMARY KEY,
    USER_ACTIONS_ID INT NOT NULL,
    RULE_ID INT NOT NULL,
    TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(USER_ACTIONS_ID,RULE_ID,TIME)
);


CREATE TABLE IF NOT EXISTS DAILY_TRACKING (
      ID SERIAL PRIMARY KEY,
      USER_ID INT NOT NULL,
      DATE DATE NOT NULL,
      COUNT INT NOT NULL,
      UNIQUE(USER_ID,DATE)
);




CREATE TABLE IF NOT EXISTS PAYMENT_METHODS(
    ID UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    ACCOUNT_TYPE VARCHAR (255) NOT NULL,
    ACCOUNT_NUMBER VARCHAR (255) NOT NULL,
    DESCRIPTION VARCHAR (255),
    USER_ID UUID NOT NULL,
    CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(USER_ID,ACCOUNT_TYPE,ACCOUNT_NUMBER)
);

CREATE TABLE IF NOT EXISTS PAYMENTS(
  ID UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  USER_ID UUID NOT NULL ,
  PAYEE_ID UUID NOT NULL,
  PAYMENT_METHOD_ID  UUID NOT NULL ,
  CURRENCY_NUMERIC_CODE INT NOT NULL,
  AMOUNT DOUBLE PRECISION NOT NULL,
  STATUS INT NOT NULL,
  RESULT INT,
  RISK_TRACKING_ID UUID,
  RISK_DECISION_DESCRIPTION VARCHAR (255),
  CREATED_AT TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UPDATED_AT TIMESTAMP
);



CREATE TABLE IF NOT EXISTS CURRENCIES(
  NUMERIC_CODE INT PRIMARY KEY,
  ALPHABETIC_CODE VARCHAR(3) NOT NULL,
  CURRENCY VARCHAR (100) NOT NULL
);

