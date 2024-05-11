package com.example.leagueapp.service;


import com.example.leagueapp.model.Player;
import com.example.leagueapp.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    IPlayerRepository iPlayerRepository;

    // Create a new player
    public Player addPlayer(Player player) {
        return iPlayerRepository.save(player);
    }


    // Bulk add players
    public List<Player> addPlayers(List<Player> players) {
        return iPlayerRepository.saveAll(players);
    }
    // Retrieve a player by ID
    public Player getPlayerById(Integer id) {
        return iPlayerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player with id " + id + " does not exist."));
    }

    //Retrieve all players
    public List<Player> getAllPlayers() {
        return iPlayerRepository.findAll();
    }

    //Update a player's details
    public Player updatePlayer(Integer id, Player player) {
        Player existingPlayer = iPlayerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player with id " + id + " does not exist."));
        existingPlayer.setName(player.getName());
        existingPlayer.setTeam(player.getTeam());
        return iPlayerRepository.save(existingPlayer); // make sure we save the updated player info back to the play database
    }

    //Delete a player
    public void deletePlayer(Integer id) {
        iPlayerRepository.deleteById(id);
    }


}
