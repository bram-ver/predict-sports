package com.bramver.predict_sports.model;

import java.util.ArrayList;
import java.util.List;

public class Matchday {
    private List<Match> matchList;
    private Long id;


    public Matchday(Long id) {
        this.id = id;
    }

    public Matchday() {
        this(null);
        this.matchList = new ArrayList<>();
    }


    public List<Match> matchList() {
        return this.matchList;
    }

    public void addMatch(Match match) {
        this.matchList.add(match);
    }

    public Long getId() {
        return id;
    }
}
