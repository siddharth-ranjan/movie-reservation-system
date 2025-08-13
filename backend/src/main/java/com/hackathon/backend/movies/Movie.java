package com.hackathon.backend.movies;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movie_title", nullable = false)
    private String title;

    @Column(name = "movie_description", nullable = false)
    private String description;

    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Set<Genre> genres;

    @Column(name = "movie_runtime",  nullable = false)
    private Integer runtimeInMinutes;

    public Movie() {
    }

    public Movie(String title, String description, Set<Genre> genres, Integer runtimeInMinutes) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.runtimeInMinutes = runtimeInMinutes;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genre) {
        this.genres = genre;
    }

    public Integer getRuntimeInMinutes() {
        return runtimeInMinutes;
    }

    public void setRuntimeInMinutes(Integer runtimeInMinutes) {
        this.runtimeInMinutes = runtimeInMinutes;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre=" + genres +
                ", runtimeInMinutes=" + runtimeInMinutes +
                '}';
    }
}
