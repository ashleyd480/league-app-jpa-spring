package com.example.leagueapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer teamId;
    private String name;


    @OneToMany(mappedBy = "team")
    private List<Player> players;

@JsonIgnore
    @ManyToMany(mappedBy = "teams")
    private List<Tournament> tournaments = new ArrayList<>();
}




