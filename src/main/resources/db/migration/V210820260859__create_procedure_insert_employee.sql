DELIMITER $$
CREATE PROCEDURE prc_insert_employee(
    IN p_id BIGINT,
    OUT p_name VARCHAR(150),
    OUT p_salary DECIMAL(10,2),
    OUT p_birthday TIMESTAMP
)
BEGIN
    INSERT INTO employees (name, salary, birthday) VALUES ( p_name, p_salary, p_birthday);
    SET p_id = LAST_INSERT_ID();
END $$
