INSERT INTO users (uuid, name, email, password, created, modified, last_login, token, is_active) VALUES ('62cee457-53cd-4467-b86c-5a1006137c86', 'Oscar Abril', 'abriloscar@abril.org', 'Password-123', '2023-10-08 13:00:00', '2023-10-08 13:00:00', '2023-10-08 13:00:00', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYnJpbG9zY2FyQGFicmlsLm9yZyIsImlhdCI6MTY5Njg5NDA3NywiZXhwIjoxNjk2ODk1NTE3fQ.rUQP3f3T1uANXxy8t4kKFYCx5iY9ycpEFUg3MIU8k-I', true);

INSERT INTO phones (user_id, number, city_code, country_code) VALUES (1, '1234567', '1', '57');
