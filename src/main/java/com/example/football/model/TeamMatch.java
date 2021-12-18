package com.example.football.model;

import javax.persistence.*;


@Entity //класс отвечает за репрезентацию табл в бд
public class TeamMatch {
    @Id
    @SequenceGenerator(name = "team_matchSequence", sequenceName = "team_match_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "team_matchSequence")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    public Player player;

    @ManyToOne
    @JoinColumn(name = "match_id")
    public Match match;

    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team team;

    public String number;
}