package com.example.leagueapp.repository;

import com.example.leagueapp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlayerRepository extends JpaRepository<Player, Integer> {
    @Query("SELECT p FROM Player p " +
            "JOIN p.team t " +
            "JOIN t.tournaments tt")List<Player> findAllPlayersInTournamentWithLimit(Integer limit);
}

// Explanation of my bonus answer:
// We resolved by first thinking of it as SQL. Then, we think how can translate to JPQL:
// Select p means selecting the entire Player entity- getting all the players
// Then we translate the join statements. With JPA, the joining is handled so we just care about joining the Player entity (p) with its team entity t and then joining team with its associate tournament entity (tt)
//SELECT *
//FROM player
//JOIN teams_tournament  ON player.team_id = teams_tournament.team_id
//JOIN tournament ON teams_tournament.tournament_id = tournament.tournament_id;