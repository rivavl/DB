package com.example.football.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.football.model.Goal;

import java.util.Date;

@Repository
public interface GoalRepository extends CrudRepository<Goal,Integer> {
    @Query(value = "select 'Goal';",nativeQuery = true)
    public String func();

    //результативность данного игрока в данной встрече (по названию команды, городу, дате встречи и ФИО игрока)
    @Query(value = "select sum(g.score) from goal g join match m on g.match_id = m.id join stadium s on m.stadium_id = s.id  join team_match tm on tm.id=g.player_id join player p on tm.player_id = p.id join playing pl on m.id = pl.match_id join team t on pl.team_id = t.id where m.date=(:date) and t.name=(:team_name) and s.city=(:match_city) and p.name=(:player_name) group by g.player_id;",nativeQuery = true)
    public Integer resultant(@Param("team_name") String team_name, @Param("match_city") String match_city, @Param("date") Date date, @Param("player_name") String player_name);
}
