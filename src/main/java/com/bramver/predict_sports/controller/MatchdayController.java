package com.bramver.predict_sports.controller;

import java.util.Optional;
import com.bramver.predict_sports.model.Matchday;
import org.springframework.http.ResponseEntity;
import com.bramver.predict_sports.repository.MatchdayRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    @PostMapping
    private ResponseEntity<Void> createMatchday(@RequestBody Matchday newMatchdayRequest, UriComponentsBuilder ucb) {
        Matchday savedMatchday = matchdayRepository.save(newMatchdayRequest);

        URI locationOfNewMatchday = ucb.
                path("api/matchday/{id}")
                .buildAndExpand(savedMatchday.getId())
                .toUri();

        return ResponseEntity.created(locationOfNewMatchday).build();
    }

}
