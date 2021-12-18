package com.example.football.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.football.model.TeamMatch;
import com.example.football.repo.sup.TeamMatchSup;

import java.util.Date;
import java.util.List;

@Repository
public interface TeamMatchRepository extends CrudRepository<TeamMatch,Integer> {
    @Query(value = "select 'TeamMatch';",nativeQuery = true)
    public String func();

    @Query(value = "select tm.* from team_match tm join player p on tm.player_id = p.id where tm.match_id=(:match_id) and p.name=(:player_name);",nativeQuery = true)
    public TeamMatch get_player_by_match_name(@Param("match_id") Integer match_id, @Param("player_name") String player_name);

    @Query(value = "select * from team_match where match_id=(:match_id) and player_id=(:player_id);",nativeQuery = true)
    public TeamMatch get_player(@Param("match_id") Integer match_id, @Param("player_id") Integer player_id);

    //ФИО и номера игроков, участвовавших во встрече (по названию команды, городу и дате встречи)
    //исправить
    @Query(value = "select p.name, tm.number from team_match tm join player p on tm.player_id = p.id where tm.match_id=(select m.id from playing p join match m  on p.match_id = m.id join stadium s on m.stadium_id = s.id join team t on p.team_id = t.id where t.name=(:team_name) and s.city=(:match_city) and m.date=(:date));",nativeQuery = true)
    public List<TeamMatchSup> name_number_by_team_city_date(@Param("team_name") String team_name, @Param("match_city") String match_city, @Param("date") Date date);
}