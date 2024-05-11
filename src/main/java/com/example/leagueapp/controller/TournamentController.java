package com.example.leagueapp.controller;

import com.example.leagueapp.model.Player;
import com.example.leagueapp.model.Team;
import com.example.leagueapp.model.Tournament;
import com.example.leagueapp.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TournamentController {
    @Autowired
    TournamentService tournamentService;

    // Create a new tournament
    @PostMapping("/tournaments")
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.addTournament(tournament);
    }

    // Retrieve a tournament by ID
    @GetMapping("/tournaments/{id}")
    public Tournament getTournamentById(@PathVariable Integer id) {
        return tournamentService.getTournamentById(id);
    }

    // Retrieve all tournaments
    @GetMapping("/tournaments")
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    // Update a tournament's details
    @PutMapping("/tournaments/{id}")
    public Tournament updateTournament(@PathVariable Integer id, @RequestBody Tournament tournament) {
        return tournamentService.updateTournament(id, tournament);
    }

    // Delete a tournament
    @DeleteMapping("/tournaments/{id}")
    public void deleteTournament(@PathVariable Integer id) {
        tournamentService.deleteTournament(id);
    }

    // Additional Methods
    // Add a team to a tournament
    @PostMapping("/tournaments/{id}/teams")
    public Tournament addTeamToTournament(@PathVariable Integer id, @RequestBody Team team) {
        return tournamentService.addTeamToTournament(id, team);
    }

    // Delete a team from a tournament
    @DeleteMapping("/tournaments/{id}/teams/{teamId}")
    public Tournament removeTeamFromTournament(@PathVariable Integer id, @PathVariable Integer teamId) {
        return tournamentService.removeTeamFromTournament(id, teamId);
    }

    // Get all teams in a tournament
    @GetMapping("/tournaments/{id}/teams")
    public List<Team> getAllTeamsInTournament(@PathVariable Integer id) {
        return tournamentService.getAllTeamsInTournament(id);
    }

    // Get a list of all the players in a tournament
    @GetMapping("/tournaments/{id}/teams/players")
    public List<Player> getAllPlayersInTournament(@PathVariable Integer id) {
        return tournamentService.getAllPlayersInTournament(id);
    }

    // Bonus question
    //adding request parameter- it defaults to value of 10 but user can enter their limit
    // you can search as follows on Postman - /tournaments/1/players?limit=5

    @GetMapping(value ="/tournaments/{id}/players", params= {"limit"})

    public List<Player> getAllPlayersInTournamentLimit(@PathVariable Integer id, @RequestParam(defaultValue = "10") Integer limit) {
        return tournamentService.getAllPlayersinTournamentLimit(limit);
    }


}
