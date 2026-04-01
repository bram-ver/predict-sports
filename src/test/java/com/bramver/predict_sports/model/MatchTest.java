package com.bramver.predict_sports.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MatchTest {
    @Test
    public void matchReturnsCorrectTeams() {
        String[] teams = new String[] {"Team A", "Team B"};
        Match match = new Match(teams[0], teams[1]);
        assertThat(match.getTeams()).isEqualTo(teams);
    }
}