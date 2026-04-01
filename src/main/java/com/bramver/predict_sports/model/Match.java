package com.bramver.predict_sports.model;

import org.springframework.data.annotation.Id;

public class Match {
    @Id
    private Long id;

    private String homeTeam;
    private String awayTeam;

    public Match() {
    }

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public String[] getTeams(){
        return new String[] {this.homeTeam, this.awayTeam};
    }


}
