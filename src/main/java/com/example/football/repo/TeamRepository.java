package com.example.football.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.football.model.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team,Integer> {
    @Query(value = "select 'Team';",nativeQuery = true)
    public String func();

    @Query(value = "select * from team where name=(:name) and city=(:city);",nativeQuery = true)
    public Team get_team_by_param(@Param("name") String name, @Param("city") String city);
}
