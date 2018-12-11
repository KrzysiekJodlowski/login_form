-- create database and table with users
CREATE DATABASE login_form WITH OWNER = krzysiek;
GRANT CONNECT ON DATABASE login_form TO krzysiek;

CREATE TABLE
IF NOT EXISTS login_data (
    user_login TEXT,
    user_password TEXT
);

INSERT INTO login_data (user_login, user_password)
VALUES
('admin', 'admin123'),
('mentor', 'mentor123'),
('student', 'student123');

-- search entry with specific login and password
SELECT * FROM login_data
WHERE
user_login=? AND user_password=?;

