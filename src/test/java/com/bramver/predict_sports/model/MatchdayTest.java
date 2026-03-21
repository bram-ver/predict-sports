package com.bramver.predict_sports.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MatchdayTest {
    private Matchday matchday;
    private final String testSeason = "2025/2026";
    @BeforeEach
    public void initialize() {

        this.matchday = new Matchday(testSeason);
    }

    @Test
    public void matchdayReturnsCorrectSeason() {
        assertThat(matchday.getSeason()).isEqualTo(testSeason);
    }

    @Test
    public void matchdayListIsEmptyAtBeginning() {
        assertThat(matchday.getMatchList().size()).isEqualTo(0);
    }

    @Test
    public void addingMatchGrowsMatchdayListByOne() {
        matchday.addMatch(new Match());
        assertThat(matchday.getMatchList().size()).isEqualTo(1);
    }

    @Test
    public void addedMatchIsIncludedInMatchdayList() {
        Match match = new Match();
        matchday.addMatch(match);
        assertThat(matchday.getMatchList()).contains(match);
    }
}