CREATE TABLE employees (
                           id INT not null auto_increment,
                           name VARCHAR(150) not null,
                           salary DECIMAL(10,2) not null,
                           birthday TIMESTAMP not null,
                           primary key (id)
