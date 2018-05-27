package com.loya.springtesting.entity;

import org.hibernate.boot.registry.selector.spi.StrategyCreator;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer salary;
    private String teamName;


    public User() {
    }


    public User( String name, Integer salary, String teamName) {
        this.name = name;
        this.salary = salary;
        this.teamName = teamName;
    }
    public User( int id,String name, Integer salary, String teamName) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.teamName = teamName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
