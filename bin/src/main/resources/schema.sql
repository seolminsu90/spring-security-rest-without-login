CREATE TABLE TB_USER(
    ID VARCHAR(100) NOT NULL,
    
    CONSTRAINT PK_ID PRIMARY KEY (ID)
) DEFAULT CHARSET=utf8;

CREATE TABLE TB_AUTH(
	ID VARCHAR(100) NOT NULL,
    ROLE VARCHAR(100) NOT NULL,
    
    CONSTRAINT PK_AUTH_ID PRIMARY KEY (ID)
) DEFAULT CHARSET=utf8;

INSERT INTO TB_USER
  (ID)
  VALUES
  ('lms'),
  ('rsa'),
  ('lmj'),
  ('sms');
  
INSERT INTO TB_AUTH
  (ID, ROLE)
  VALUES
  ('lms', 'ROLE_A'),
  ('rsa', 'ROLE_A'),
  ('lmj', 'ROLE_B'),
  ('sms', 'ROLE_A');