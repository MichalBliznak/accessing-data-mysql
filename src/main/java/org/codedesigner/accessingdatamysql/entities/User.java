package org.codedesigner.accessingdatamysql.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity // This tells Hibernate to make a table out of this class
@Table(catalog = "db_example")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private String address;
}