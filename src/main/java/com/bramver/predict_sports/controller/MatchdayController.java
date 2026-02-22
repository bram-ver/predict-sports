package com.bramver.predict_sports.controller;

import com.bramver.predict_sports.model.Matchday;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matchday")
public class MatchdayController {
    @GetMapping("/{requestedId}")
    private ResponseEntity<Matchday> findById(@PathVariable Long requestedId) {
        if (requestedId > 0L && requestedId <= 34L) {
            Matchday matchday = new Matchday(requestedId);
            return ResponseEntity.ok(matchday);
        }

        return ResponseEntity.notFound().build();
    }
}
