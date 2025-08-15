package com.hackathon.backend.showtime;

import com.hackathon.backend.movies.Movie;
import com.hackathon.backend.movies.MovieService;
import com.hackathon.backend.showtime.dto.ShowtimeDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private final MovieService movieService;

    public ShowtimeService(ShowtimeRepository showtimeRepository, MovieService movieService) {
        this.showtimeRepository = showtimeRepository;
        this.movieService = movieService;
    }

    public ShowtimeDTO save(Showtime showtime) {
        // 1. Get the ID from the incomplete movie object.
        Long movieId = showtime.getMovie().getId();

        // 2. Fetch the COMPLETE and managed Movie entity from the database.
        // This throws an exception if the movie doesn't exist, handling your validation.
        Movie fullMovie = movieService.findMovieById(movieId);

        // 3. CRITICAL STEP: Replace the incomplete movie object with the full one.
        showtime.setMovie(fullMovie);

        // 4. Now, save the corrected showTimes object.
        Showtime savedShowTime = showtimeRepository.save(showtime);

        // 5. The converter will now work because savedShowTime has a complete Movie object.
        return convertToShowTimesDTO(savedShowTime);
    }

    public List<ShowtimeDTO> findByMovie(Long movieId) {
        List<Showtime> showTimes = showtimeRepository.findByMovieId(movieId);

        return showTimes.stream()
                .map(this::convertToShowTimesDTO) // Calls the conversion method for each item
                .collect(Collectors.toList());
    }

    public List<ShowtimeDTO> findAll() {
        List<Showtime> showTimes = showtimeRepository.findAll();

        return showTimes.stream()
                .map(this::convertToShowTimesDTO)
                .collect(Collectors.toList());
    }

    public ShowtimeDTO findById(Long id) {
        Showtime showtime = showtimeRepository.findById(id).orElse(null);
        if(showtime == null){
            throw new IllegalArgumentException("Movie id is not present!");
        }
        return convertToShowTimesDTO(showtime);
    }

    public Showtime findShowtimeEntityById(Long id) {
        return showtimeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Showtime with id " + id + " is not present!"));
    }

    public ShowtimeDTO convertToShowTimesDTO(Showtime showTime) {
        if(showTime.getMovie() == null){
            throw new IllegalArgumentException("Movie id is not present!");
        }

        return new ShowtimeDTO(
                showTime.getId(),
                showTime.getMovie().getId(),
                showTime.getMovie().getTitle(),
                showTime.getTheaterName(),
                showTime.getScreenNumber(),
                showTime.getShowDateTime(),
                showTime.getTicketPrice(),
                showTime.getAvailableSeats()
        );
    }

    @Transactional
    public void delete(Long showId) {
        if (!showtimeRepository.existsById(showId)) {
            // Or just let it fail silently, depending on your requirements
            throw new EntityNotFoundException("ShowTime not found with id: " + showId);
        }
        showtimeRepository.deleteById(showId);
    }
}
