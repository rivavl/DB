package com.example.football.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.football.model.Playing;
import com.example.football.repo.sup.PlayingSup;

import java.util.List;

@Repository
public interface PlayingRepository extends CrudRepository<Playing,Integer> {
    @Query(value = "select 'Playing';",nativeQuery = true)
    public String func();

    //Даты встреч команды, ее противник и счет
    @Query(value = "select (select from match,(select t2.name from playing p2 join team t2 on p2.team_id = t2.id where match_id= p.match_id and t.name<>(:team_name))as team from playing p join match m on p.match_id = m.id join team t on p.team_id = t.id join goal g on m.id = g.match_id where t.name=(:team_name) group by g.match_id;",nativeQuery = true)
    public List<PlayingSup> date_match_team_score(@Param("team_name") String team_name);
}

