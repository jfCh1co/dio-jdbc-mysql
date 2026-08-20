package org.example.persistence.entity;

import org.example.persistence.ConnectionUtil;

import com.mysql.cj.jdbc.StatementImpl;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {
    public void insert(final EmployeeEntity entity){
        try (   //Recursos que vão ser usados durante o try
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
                ) {

            //Padrão de inserção de dados
            var sql = "INSERT INTO employees (name, salary, birthday) values('" +
                    entity.getName() + "'," +
                    entity.getSalary() + "," +
                    "'" + formatOffSetDateTime(entity.getBirthday()) + "')";

            //Execução do updade
            statement.executeUpdate(sql);
            System.out.println(("Foram afetados: " + statement.getUpdateCount()));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void update(final EmployeeEntity entity){
        try (   //Recursos que vão ser usados durante o try
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {

            //Padrão de inserção de dados
            var sql = "UPDATE employees SET " +
                    "name      = '" + entity.getName() + "', " +
                    "salary    = '" + entity.getSalary() + "', " +
                    "birthday  = '" + formatOffSetDateTime(entity.getBirthday()) + "' " +
                    "WHERE id  = " + entity.getId();
            statement.executeUpdate(sql);
            System.out.println(("Foram afetados: " + statement.getUpdateCount()));


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void delete(final long id){
        try (   //Recursos que vão ser usados durante o try
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {
            var sql = "DELETE FROM employees WHERE id = " + id;
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<EmployeeEntity> findAll(){
        List<EmployeeEntity> entities = new ArrayList<>();

        try (   //Recursos que vão ser usados durante o try
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {
            // Procura e ordenamento pelo nome
            statement.executeQuery("SELECT * FROM employees ORDER BY name desc");
            var resultSet = statement.getResultSet();
            while (resultSet.next()) {
                var entity = new EmployeeEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));

                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                var birthday = OffsetDateTime.ofInstant(birthdayInstant, UTC);

                entity.setBirthday(birthday);

                entities.add(entity);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entities;
    }
    public EmployeeEntity findById(final long id) {
        var entity = new EmployeeEntity();

        try (   //Recursos que vão ser usados durante o try
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {

            // Procura por id
            statement.executeQuery("SELECT * FROM employees WHERE id = " + id);
            var resultSet = statement.getResultSet();
            if (resultSet.next()) {
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));

                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                var birthday = OffsetDateTime.ofInstant(birthdayInstant, UTC);

                entity.setBirthday(birthday);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entity;
    }

    private String formatOffSetDateTime(final OffsetDateTime dateTime) {
        var utcDateTime = dateTime.withOffsetSameInstant(UTC);
        return utcDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
