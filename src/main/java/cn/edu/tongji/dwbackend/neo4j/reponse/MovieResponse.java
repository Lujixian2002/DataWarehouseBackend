package cn.edu.tongji.dwbackend.neo4j.reponse;

import java.util.List;

public class MovieResponse {
    private long time;
    private long movieCount;
    private List<String> movies;

    public void setTime(long time) {
        this.time = time;
    }

    public long getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(long movieCount) {
        this.movieCount = movieCount;
    }

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    public long getTime() {
        return time;
    }
}
