package org.example.webul.sb_crud_webul.repository;

import org.example.webul.sb_crud_webul.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class MemoryMovieRepository implements MovieRepository{
    private final Map<Long,Movie> store=new LinkedHashMap<>();
    private Long id=0L;

    @Override
    public Movie save(Movie movie) {
        movie.setId(++id);
        store.put(movie.getId(),movie);
        return movie;
    }

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Movie> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Movie update(Movie newm) {
        store.put(newm.getId(),newm);
        return newm;
    }

    @Override
    public void deleteById(long id) {
        store.remove(id);
    }


    public List<Movie> ratingCut(float minR){
        List<Movie> ratingMovie=new ArrayList<>();
        for(Movie movie : store.values()){
            if(movie.getRating()>=minR){
                ratingMovie.add(movie);
            }
        }

        return ratingMovie;
    }
}
