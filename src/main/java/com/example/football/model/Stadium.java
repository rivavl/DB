package com.example.football.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity //класс отвечает за репрезентацию табл в бд
public class Stadium {
    @Id
    @SequenceGenerator(name = "stadiumSequence", sequenceName = "stadium_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "stadiumSequence")
    public Integer id;
    public String name;
    public String city;
    public Integer capacity;

}