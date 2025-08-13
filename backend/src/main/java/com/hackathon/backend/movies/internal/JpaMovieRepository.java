package com.hackathon.backend.movies.internal;

import com.hackathon.backend.movies.Genre;
import com.hackathon.backend.movies.Movie;
import com.hackathon.backend.movies.MovieRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
interface JpaMovieRepository extends MovieRepository, JpaRepository<Movie, Long> {
}