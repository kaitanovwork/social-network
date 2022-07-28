INSERT INTO roles (id, name)
VALUES (101, 'USER');

INSERT INTO users (id, email, first_name, last_name, password, role_id)
VALUES (101, 'user', 'user', 'user', '$2a$12$4u1tSnYIkFdXX1Lh.hEwyOqORMPMI7lLap1.pg.Si62auDld87IaS', 101);

INSERT INTO notifications (id, user_id, text, time, is_viewed)
VALUES (101, 101, 'notification text of true', '2015-11-21T13:48:50.023' ,true),
       (102, 101, 'notification text of false', '2015-11-21T13:48:50.023' ,false);
