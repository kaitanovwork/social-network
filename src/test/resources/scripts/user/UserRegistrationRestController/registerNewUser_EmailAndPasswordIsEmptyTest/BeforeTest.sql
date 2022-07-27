INSERT INTO roles (id, name)
VALUES (101, 'USER');

INSERT INTO users(id, email, password, first_name, last_name, role_id)
VALUES (101, 'test@gmail.com', '$2a$10$DU7TvLkcHshv20dLxvYaK.jltbesPYufA.kaT/P2ig.g/9.Ygxg1y', 'firstName', 'lastName', 101);
