package com.example.leagueapp.service;

import com.example.leagueapp.model.Player;
import com.example.leagueapp.model.Team;
import com.example.leagueapp.model.Tournament;
import com.example.leagueapp.repository.IPlayerRepository;
import com.example.leagueapp.repository.ITeamRepository;
import com.example.leagueapp.repository.ITournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService {
    @Autowired
    ITournamentRepository iTournamentRepository;

    @Autowired
    ITeamRepository iTeamRepository;

    @Autowired
    IPlayerRepository iPlayerRepository;

    // Create a new tournament
    public Tournament addTournament(Tournament tournament) {
        return iTournamentRepository.save(tournament);
    }

    // Retrieve a tournament by ID
    public Tournament getTournamentById(Integer id) {
        return iTournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tournament with id " + id + " does not exist."));
    }

    // Retrieve all tournaments
    public List<Tournament> getAllTournaments() {
        return iTournamentRepository.findAll();
    }

    //Update a tournament's details
    public Tournament updateTournament(Integer id, Tournament tournament) {
        Tournament existingTournament = iTournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tournament with id " + id + " does not exist."));
        existingTournament.setName(tournament.getName());
        existingTournament.setTeams(tournament.getTeams());
        return iTournamentRepository.save(tournament);
    }

    // Delete a tournament
    public void deleteTournament(Integer id) {
        iTournamentRepository.deleteById(id);
    }


    // Additional Methods
    // Add a team to a tournament
    public Tournament addTeamToTournament(Integer id, Team team) {
        //find tournament by id
        Tournament existingTournament = iTournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tournament with id " + id + " does not exist."));

        // saving the team we want to add to the team database
        // this way team has an id
        iTeamRepository.save(team);

        //existing list of teams under the tournament with that id
        List<Team> teams = existingTournament.getTeams();

        //adding that team to that existing list of teams under tournament with that id
        teams.add(team);

        //saving that tournament  with the newly added team to the tournament database
        return iTournamentRepository.save(existingTournament);

    }

    // Delete a team from a tournament

    public Tournament removeTeamFromTournament(Integer id, Integer teamId) {
        // finding tournament by id
        Tournament existingTournament = iTournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tournament with id " + id + " does not exist."));

        //existing list of teams in the tournament with that id
        List<Team> tournamentTeamsList = existingTournament.getTeams();

        //filter out team with that id from that existing list of teams
        List<Team> tournamentListAfterTeamDeleted = tournamentTeamsList.stream().filter(t -> !t.getTeamId().equals(teamId)).toList();

        //update the tournament's team list with that filtered list
        existingTournament.setTeams(tournamentListAfterTeamDeleted);

        //save the updated tournament with the new teams list to the tournament database
        return iTournamentRepository.save(existingTournament);
    }


    // Get all teams in a tournament

    public List<Team> getAllTeamsInTournament(Integer id) {
        // find tournament that matches that id
        Tournament existingTournament = iTournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tournament with id " + id + " does not exist."));

        //existing list of teams in the tournament with that id
        return existingTournament.getTeams();
    }

    // Get a list of all the players in a tournament

    public List<Player> getAllPlayersInTournament(Integer id) {
        // find tournament that matches that id
        Tournament existingTournament = iTournamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Tournament with id " + id + " does not exist."));


        //existing list of teams in the tournament with that id
        List<Team> tournamentTeamsList = existingTournament.getTeams();

        // Get all players from each team in the tournament

        // starting with empty array list of type Player
        List<Player> allPlayersInTournament = new ArrayList<>();

        // for the list of teams associated with that tournament, we iterate through it
        // then we take each team in that list and get its list of players
        // that list of players is then added to the players in team
        for (Team team : tournamentTeamsList) {
            List<Player> playersInTeam = team.getPlayers();
            allPlayersInTournament.addAll(playersInTeam);
        }


        return allPlayersInTournament;
    }


    // Bonus question
    public List<Player> getAllPlayersinTournamentLimit(Integer id, Integer limit) {
        Tournament existingTournament = iTournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament with id " + id + " does not exist."));

        List<Team> tournamentTeamsList = existingTournament.getTeams();

        List<Player> allPlayersInTournament = new ArrayList<>();

        for (Team team : tournamentTeamsList) {
            List<Player> playersInTeam = team.getPlayers();
            allPlayersInTournament.addAll(playersInTeam);
        }

        return allPlayersInTournament.subList(0, Math.min(allPlayersInTournament.size(), limit));
    }

    public List<Player> getAllPlayersinTournamentLimit (Integer limit)
    {
       return iPlayerRepository.findAllPlayersInTournamentWithLimit(limit);
    }
}

// Bonus explanation:
// We added the .sublist in the return
// allPlayersInTournament.size gives us the total number of players in tournament.
//limit is the max number of players that client wants to retrieve
//The .subList creatas a sublist of all players from allPlayersInTournament list. This sublist starts from index 0 and extends up to users' specified limit of players to display