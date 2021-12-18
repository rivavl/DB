package com.example.football.model;

import javax.persistence.*;
import java.util.Date;


@Entity //класс отвечает за репрезентацию табл в бд
public class Match {
    @Id
    @SequenceGenerator(name = "matchSequence", sequenceName = "match_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "matchSequence")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    public Stadium stadium;

    public Date date;
}