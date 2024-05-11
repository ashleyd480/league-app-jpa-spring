package com.example.leagueapp.controller;

import com.example.leagueapp.model.Player;
import com.example.leagueapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    // Create a new player
    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {
        return playerService.addPlayer(player);
    }

    // Bulk add players
    @PostMapping("/playersBulk")
    public List<Player> createPlayers (@RequestBody List<Player> players) {
        return playerService.addPlayers(players);
    }

    // Retrieve a player by ID
    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable Integer id) {
        return playerService.getPlayerById(id);
    }

    // Retrieve all players
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    // Update a player's details
    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable Integer id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    // Delete a player
    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable Integer id) {
        playerService.deletePlayer(id);
    }
}
