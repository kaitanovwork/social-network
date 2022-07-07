TRUNCATE roles, users RESTART IDENTITY CASCADE;

INSERT INTO roles (id, name)
VALUES (101, 'USER'),
       (102, 'ADMIN');

INSERT INTO users (id, email, first_name, last_name, password, role_id)
VALUES (101, 'user', 'user', 'user', '$2a$12$4u1tSnYIkFdXX1Lh.hEwyOqORMPMI7lLap1.pg.Si62auDld87IaS', 101),
       (102, 'admin', 'admin', 'admin', '$2a$12$6wbxmDg7XUNgc4A9w9V3SuMAzE5lUVMzt/VC4eBqQrUjwoXX52WMy', 102)