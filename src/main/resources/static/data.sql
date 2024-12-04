USE ayotl;

INSERT INTO roles (id, name, description) VALUES (1, 'ADMIN', 'Administrator');
INSERT INTO roles (id, name, description) VALUES (2, 'CUSTOMER', 'Customer');
INSERT INTO users (id, email, password, created_at, updated_at) VALUES
(1,  'admin@email.com', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', now(), now());
INSERT INTO users_data (user_id, first_name, last_name, second_last_name) VALUES (1, 'Administrator', 'Last Name', 'Second Last Name');
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
