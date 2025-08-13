package com.hackathon.backend.movies.dto;

import com.hackathon.backend.movies.Genre;

import java.util.List;

public record MovieRegister(String title, String description, List<Genre> genreList, int runtimeInMinutes) {
}
