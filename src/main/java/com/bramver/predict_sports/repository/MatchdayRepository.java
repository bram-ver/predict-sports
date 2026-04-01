package com.bramver.predict_sports.repository;

import com.bramver.predict_sports.model.Matchday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MatchdayRepository extends CrudRepository<Matchday, Long>, PagingAndSortingRepository<Matchday, Long> {
}
