package com.example.leagueapp.repository;

import com.example.leagueapp.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITournamentRepository extends JpaRepository <Tournament, Integer> {
}
