INSERT INTO roles (id, name)
VALUES (101, 'USER');

INSERT INTO users(id, email, password, first_name, last_name, role_id)
VALUES (101, 'test', '$2a$12$I6mUCoal0K.PxEZjtVKf2uizDydXAlQYkvV0vp7X6Y/tobQVY86/O', 'firstName', 'lastName', 101);
