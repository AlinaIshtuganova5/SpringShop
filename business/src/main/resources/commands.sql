INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');


INSERT INTO users (email, enabled, password, address,fio)
VALUES ('admin', TRUE, '$2a$10$gL8WGUN.pseSA7bN6a6Q.esQXb2r.jIMeWvcq3ARqD.NCp6P.v6fC', '','');
CREATE TABLE persistent_logins (
  username  VARCHAR(64) NOT NULL,
  series    VARCHAR(64) PRIMARY KEY,
  token     VARCHAR(64) NOT NULL,
  last_used TIMESTAMP   NOT NULL
);

INSERT INTO users_roles (users_id, role_id) VALUES (1,1);
INSERT INTO users_roles (users_id, role_id) VALUES (1,2);
