package com.example.leagueapp.controller;

import com.example.leagueapp.model.Player;
import com.example.leagueapp.model.Team;
import com.example.leagueapp.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    // Create a new team
    @PostMapping("/teams")
    public Team createTeam(@RequestBody Team team) {
        return teamService.addTeam(team);
    }


    // Retrieve a team by ID
    @GetMapping("/teams/{id}")
    public Team getTeamById(@PathVariable Integer id) {
        return teamService.getTeamById(id);
    }

    //Retrieve all teams
    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }


    //Update a team's details
    @PutMapping("/teams/{id}")
    public Team updateTeam(@PathVariable Integer id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }

    //Delete a team
    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable Integer id) {
        teamService.deleteTeam(id);
    }

    // Additional Methods
    // Add a player to a team
    @PostMapping("/teams/{id}/players")
    public Team addPlayerToTeam(@PathVariable Integer id, @RequestBody Player player) {
        return teamService.addPlayerToTeam(id, player);
    }

    // Delete a player from a team
    @DeleteMapping("/teams/{id}/players/{playerId}")
    public Team removePlayerFromTeam(@PathVariable Integer id, @PathVariable Integer playerId) {
        return teamService.removePlayerFromTeam(id, playerId);
    }


}
