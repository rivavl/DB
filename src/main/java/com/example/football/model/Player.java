package com.example.football.model;

import javax.persistence.*;
import java.util.Date;


@Entity //класс отвечает за репрезентацию табл в бд
public class Player {
    @Id
    @SequenceGenerator(name = "playerSequence", sequenceName = "player_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "playerSequence")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team team;

    public String name;
    public String number;
}