package com.bramver.predict_sports.controller;

import java.util.Optional;
import com.bramver.predict_sports.model.Matchday;
import org.springframework.http.ResponseEntity;
import com.bramver.predict_sports.repository.MatchdayRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matchday")
public class MatchdayController {
    private final MatchdayRepository matchdayRepository;

    private MatchdayController(MatchdayRepository matchdayRepository) {
        this.matchdayRepository = matchdayRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Matchday> findById(@PathVariable Long requestedId) {
        Optional<Matchday> matchdayOptional = matchdayRepository.findById(requestedId);
        if (matchdayOptional.isPresent()) {
            return ResponseEntity.ok(matchdayOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
