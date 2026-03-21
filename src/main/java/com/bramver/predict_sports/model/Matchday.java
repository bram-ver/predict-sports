package com.bramver.predict_sports.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

public class Matchday {
    @Id
    private Long id;

    @MappedCollection(idColumn = "MATCHDAY_ID")
    private List<Match> matchList;
    private String season;

    public Matchday(String season) {
        this.matchList = new ArrayList<>();
        this.season = season;
    }

    public Matchday() {}

    public String getSeason() {
        return this.season;
    }

    public List<Match> getMatchList() {
        return this.matchList;
    }

    public void addMatch(Match match) {
        this.matchList.add(match);
    }

    public Long getId() {
        return id;
    }
}
