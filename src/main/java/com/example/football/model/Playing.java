package com.example.football.model;

import javax.persistence.*;


@Entity //класс отвечает за репрезентацию табл в бд
public class Playing {

    @Id
    @SequenceGenerator(name = "playingSequence", sequenceName = "playing_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "playingSequence")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team team;

    @ManyToOne
    @JoinColumn(name = "match_id")
    public Match match;
}