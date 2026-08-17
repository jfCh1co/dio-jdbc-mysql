package org.example.persistence.entity;

import org.example.persistence.ConnectionUtil;

import com.mysql.cj.jdbc.StatementImpl;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

            if(statement instanceof StatementImpl imp) {
                entity.setId(imp.getLastInsertID());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void update(final EmployeeEntity entity){

    }
    public void delete(final long id){

    }
    public List<EmployeeEntity> findAll(){
        return null;
    }
    public EmployeeEntity findById(final long id) {
        return null;
    }

    private String formatOffSetDateTime(final OffsetDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
