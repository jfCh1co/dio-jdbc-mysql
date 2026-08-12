package org.example.persistence;

import com.sun.jdi.connect.spi.Connection;
import lombok.NoArgsConstructor;

import java.sql.DriverManager;
import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {
        return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/store", "store", "store");
    }

}
