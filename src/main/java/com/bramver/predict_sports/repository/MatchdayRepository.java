package com.bramver.predict_sports.repository;

import com.bramver.predict_sports.model.Matchday;
import org.springframework.data.repository.CrudRepository;

public interface MatchdayRepository extends CrudRepository<Matchday, Long> {
}
