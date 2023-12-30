package cn.edu.tongji.dwbackend.Mysql.controller;

import cn.edu.tongji.dwbackend.Mysql.entity.movie;
import cn.edu.tongji.dwbackend.Mysql.service.MovieDateService;
import cn.edu.tongji.dwbackend.dto.MovieDateResult;
import cn.edu.tongji.dwbackend.dto.MoviePageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movieDate")
public class MovieDateController {
    @Autowired
    MovieDateService movieDateService;
    @GetMapping("/year")
    public MoviePageResult getMovieByYear(@RequestParam int year)
    {
        return movieDateService.getMovieByReleaseYear(year);
    }

    @GetMapping("/year-month")
    public MoviePageResult getMovieByYearMonth(@RequestParam int year,@RequestParam int month)
    {
        return movieDateService.getMovieByReleaseYearMonth(year,month);
    }

    @GetMapping("/year-season")
    public MoviePageResult getMovieByYearSeason(@RequestParam int year,@RequestParam int season)
    {
        return movieDateService.getMovieByReleaseYearSeason(year,season);
    }

    @GetMapping("years")
    public List<MovieDateResult> getMovieCountByYears(@RequestParam int startYear, @RequestParam int endYear)
    {
        return movieDateService.getMovieCountByYears(startYear, endYear);
    }

    @GetMapping("months")
    public List<MovieDateResult> getMovieCountByMonths(@RequestParam int year)
    {
        return movieDateService.getMovieCountByMonths(year);
    }
}
