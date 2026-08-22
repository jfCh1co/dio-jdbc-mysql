package org.example.persistence;

import org.example.persistence.entity.EmployeeAuditEntity;
import org.example.persistence.entity.OperationEnum;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeAuditDAO {
    public List<EmployeeAuditEntity> findAll() {
        List<EmployeeAuditEntity> entities = new ArrayList<>();
        try(
                var conection = ConnectionUtil.getConnection();
                var statement = conection.createStatement();
                ){
            statement.executeQuery("SELECT * FROM view_employee_audit");
            var resultSet = statement.getResultSet();
            while (resultSet.next()){
                var birthdayTimestamp = resultSet.getTimestamp("birthday");
                var oldBirthdayTimestamp = resultSet.getTimestamp("old_birthday");

                var birthdayInstant = birthdayTimestamp != null
                        ? birthdayTimestamp.toInstant()
                        : null;

                var oldBirthdayInstant = oldBirthdayTimestamp != null
                        ? oldBirthdayTimestamp.toInstant()
                        : null;
                entities.add(new EmployeeAuditEntity(
                        resultSet.getLong("employee_id"),
                        resultSet.getString("name"),
                        resultSet.getString("old_name"),
                        resultSet.getBigDecimal("salary"),
                        resultSet.getBigDecimal("old_salary"),
                        birthdayInstant != null
                                ? OffsetDateTime.ofInstant(birthdayInstant, UTC)
                                : null,
                        oldBirthdayInstant != null
                                ? OffsetDateTime.ofInstant(oldBirthdayInstant, UTC)
                                : null,
                        OperationEnum.getByDbOperation(resultSet.getString("operation"))
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     return entities;
    }
}
