package org.example.webul.sb_crud_webul.repository;

import org.example.webul.sb_crud_webul.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MovieRepository {
     Movie save(Movie movie);
     List<Movie> findAll();
     Optional<Movie> findById(long id);
     Movie update(Movie m);
     void deleteById(long m);
     public List<Movie> ratingCut(float minR);
}
