package com.example.football.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.football.model.Match;

import java.util.Date;

@Repository
public interface MatchRepository extends CrudRepository<Match,Integer> {
    @Query(value = "select 'Match';",nativeQuery = true)
    public String func();

    @Query(value = "select * from match where date=(:date) and stadium_id=(:stadium_id);",nativeQuery = true)
    public Match get_match_by_param(@Param("date") Date date, @Param("stadium_id") Integer stadium_id);

    @Query(value = "select m.* from playing p join match m on p.match_id=m.id  where m.date=(:date) and p.team_id=(:team_id);",nativeQuery = true)
    public Match get_match_by_date_team(@Param("date") Date date, @Param("team_id") Integer team_id);
}