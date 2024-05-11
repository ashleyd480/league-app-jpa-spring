package com.example.leagueapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Integer playerId;
    private String name;

    @ManyToOne // relationship between player & team w/o CascadeType; many players to one team
    @JoinColumn(name = "team_id") // foreign key in DB table/column in Player table. All players must have team.
    @JsonIgnore // used to control the JSON serialization process, ensuring that "team" isn't included in JSON output, which avoids circular reference issues.
    private Team team; // represents the team name that the player belongs to

}
