package org.example.persistence.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Getter
@Setter
public class EmployeeEntity {
    private long id;
    private String name;
    private BigDecimal salary;
    private OffsetDateTime birthday;
}
