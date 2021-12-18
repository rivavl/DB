package com.example.football.model;

import javax.persistence.*;
import java.sql.Time;


@Entity //класс отвечает за репрезентацию табл в бд
public class Goal {
    @Id
    @SequenceGenerator(name = "goalSequence", sequenceName = "goal_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "goalSequence")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    public Match match;

    @ManyToOne
    @JoinColumn(name = "player_id")
    public TeamMatch player;

    public Integer score;
    public Time time;
}