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
                .dataSource("jdbc:mysql://172.19.2.65:3306/jdbc-sample",
                        "appuser",
                        "123456")
                .load();
        flyway.migrate();

        /* INSERT
        var employee = new EmployeeEntity();
        employee.setName("Chico");
        employee.setSalary(new BigDecimal("7000"));
        employee.setBirthday(OffsetDateTime.now().minusYears(18));
        System.out.println(employee);
        employeeDao.insert(employee);
        System.out.println(employee);
        */

        /* Find All
        employeeDao.findAll().forEach(System.out::println);
         */

        /* Find by id
        System.out.println(employeeDao.findById(1));
        */

        /* Updade
        var employee = new EmployeeEntity();
        employee.setId(1);
        employee.setName("josé");
        employee.setSalary(new BigDecimal(12000));
        employee.setBirthday(OffsetDateTime.now().minusYears(23).minusDays(3));
        employeeDao.update(employee);
        */

        /* Delete
        employeeDao.delete(1);
         */
    }

}
