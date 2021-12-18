package com.example.football.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.football.model.Stadium;

@Repository
public interface StadiumRepository extends CrudRepository<Stadium,Integer> {
    @Query(value = "select 'Stadium';",nativeQuery = true)
    public String func();

    @Query(value = "select id from stadium where name=(:name) and city=(:city);",nativeQuery = true)
    public Integer get_id(@Param("name") String name,@Param("city") String city);

    @Query(value = "select * from stadium where id=(:id);",nativeQuery = true)
    public Stadium get_by_id(@Param("id") Integer id);

    @Query(value = "select * from stadium where name=(:name) and city=(:city);",nativeQuery = true)
    public Stadium get_stadium_by_param(@Param("name") String name,@Param("city") String city);

    @Query(value = "select count(id) from stadium;",nativeQuery = true)
    public Integer last_position();
}