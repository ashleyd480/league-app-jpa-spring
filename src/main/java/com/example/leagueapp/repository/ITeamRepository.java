package com.example.leagueapp.repository;

import com.example.leagueapp.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamRepository extends JpaRepository <Team, Integer>  {
}
