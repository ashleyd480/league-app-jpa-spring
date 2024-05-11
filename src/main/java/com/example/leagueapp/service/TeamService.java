package com.example.leagueapp.service;


import com.example.leagueapp.model.Player;
import com.example.leagueapp.model.Team;
import com.example.leagueapp.repository.IPlayerRepository;
import com.example.leagueapp.repository.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    ITeamRepository iTeamRepository;

    @Autowired
    IPlayerRepository iPlayerRepository;

    // Create a new team
    public Team addTeam(Team team) {
        return iTeamRepository.save(team);
    }


    // Retrieve a team by ID
    public Team getTeamById(Integer id) {
        return iTeamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team with id " + id + " does not exist."));
    }

    //Retrieve all teams
    public List<Team> getAllTeams() {
        return iTeamRepository.findAll();
    }

    //Update a team's details
    public Team updateTeam(Integer id, Team team) {
        Team existingTeam = iTeamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team with id " + id + " does not exist."));
        existingTeam.setName(team.getName());
        existingTeam.setTournaments(team.getTournaments());
        return iTeamRepository.save(existingTeam); // make sure we save the updated team info back to the team database
    }

    //Delete a team
    public void deleteTeam(Integer id) {
        iTeamRepository.deleteById(id);
    }

    // Additional Methods
    // Add a player to a team
    public Team addPlayerToTeam(Integer id, Player player) {
        // finding team by id
        Team existingTeam = iTeamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team with id " + id + " does not exist."));

        player.setTeam(existingTeam);

        //saving the player we want to add to the player database
        // this way player has an id
        iPlayerRepository.save(player);

        //existing list of players under the team with that id
        List<Player> players = existingTeam.getPlayers();

        //adding that player to that existing list of players under team with that id
        players.add(player);

        existingTeam.setPlayers(players);

        //saving that team with the newly added player to the team database
        return iTeamRepository.save(existingTeam);
    }

    public Team removePlayerFromTeam(Integer id, Integer playerId) {
        // finding team by id
        Team existingTeam = iTeamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team with id " + id + " does not exist."));

        //existing list of players under the team with that id
        List<Player> teamPlayersList = existingTeam.getPlayers();

        //filter out player with that id from that existing list of players
        List<Player> teamListAfterPlayerDeleted = teamPlayersList.stream().filter(p -> !p.getPlayerId().equals(playerId)).toList();

        //update the team's player list with that filtered list
        existingTeam.setPlayers(teamListAfterPlayerDeleted);

        //save the updated team with the new players list to the team database
        return iTeamRepository.save(existingTeam);
    }
}
