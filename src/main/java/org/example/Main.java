package org.example;

import org.example.persistence.entity.EmployeeDAO;
import org.example.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Main {

    private final static EmployeeDAO employeeDao = new EmployeeDAO();

    public static void main(String[] args) {
        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost/jdbc-sample", "root", "123456")
                .load();
        flyway.migrate();

        var employee = new EmployeeEntity();
        employee.setName("Chico");
        employee.setSalary(new BigDecimal("7000"));
        employee.setBirthday(OffsetDateTime.now().minusYears(18));
        System.out.println(employee);
        employeeDao.insert(employee);
        System.out.println(employee);
    }

}
