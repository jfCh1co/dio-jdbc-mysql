package org.example;

import org.example.persistence.EmployeeAuditDAO;
import org.example.persistence.EmployeeDAO;
import org.example.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Main {

    private final static EmployeeDAO employeeDao = new EmployeeDAO();
    private final static EmployeeAuditDAO employeeAuditDao = new EmployeeAuditDAO();

    public static void main(String[] args) {


        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://172.19.2.65:3306/jdbc-sample",
                        "appuser",
                        "123456")
                .load();
        flyway.migrate();

        /*
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://172.19.2.65:3306/jdbc-sample",
                        "appuser",
                        "123456")
                .load();

        flyway.repair();
        flyway.migrate();
         */


        /* INSERT */
        var employee = new EmployeeEntity();
        employee.setName("joão");
        employee.setSalary(new BigDecimal("7000"));
        employee.setBirthday(OffsetDateTime.now().minusYears(18));
        System.out.println(employee);
        employeeDao.insert(employee);
        System.out.println(employee);

        var employe = new EmployeeEntity();
        employe.setName("josé");
        employe.setSalary(new BigDecimal("7000"));
        employe.setBirthday(OffsetDateTime.now().minusYears(18));
        System.out.println(employee);
        employeeDao.insert(employee);
        System.out.println(employee);


        /* Find All
        employeeDao.findAll().forEach(System.out::println);
         */

        /* Find by id
        System.out.println(employeeDao.findById(1));
        */

        /* Updade */
        var employeee = new EmployeeEntity();
        employeee.setId(1);
        employeee.setName("josi");
        employeee.setSalary(new BigDecimal(12000));
        employeee.setBirthday(OffsetDateTime.now().minusYears(23).minusDays(3));
        employeeDao.update(employeee);

        var employe3 = new EmployeeEntity();
        employe3.setId(2);
        employe3.setName("josimar");
        employe3.setSalary(new BigDecimal(12000));
        employe3.setBirthday(OffsetDateTime.now().minusYears(23).minusDays(3));
        employeeDao.update(employe3);

        /* Delete
        employeeDao.delete(1);
         */

        //----------------------------------------------//
        // ----------------- View ----------------------//
        //----------------------------------------------//

        employeeAuditDao.findAll().forEach(System.out::println);
    }

}
