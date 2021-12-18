package com.example.football.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.football.model.Player;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player,Integer> {
    @Query(value = "select 'Player';",nativeQuery = true)
    public String func();

    @Query(value = "select * from player where team_id=(:team_id);",nativeQuery = true)
    public List<Player> get_players_by_team(@Param("team_id") Integer team_id);

    @Query(value = "select * from player where team_id=(:team_id) and number=(:number);",nativeQuery = true)
    public Player get_player_by_team_and_number(@Param("team_id") Integer team_id,@Param("number") String number);
}
