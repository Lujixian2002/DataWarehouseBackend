package
        cn.edu.tongji.dwbackend.Mysql.service.impl;

import cn.edu.tongji.dwbackend.Mysql.Mapper.MovieDateMapper;
import cn.edu.tongji.dwbackend.Mysql.entity.*;
import cn.edu.tongji.dwbackend.Mysql.service.MovieDateService;
import cn.edu.tongji.dwbackend.dto.MovieDateResult;
import cn.edu.tongji.dwbackend.dto.MoviePageResult;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@MapperScan("cn.edu.tongji.dwbackend.Mysql.mapper")
@Service
public class MovieDateServiceImpl implements MovieDateService {
    @Autowired
    MovieDateMapper movieDateMapper;

    public MoviePageResult getMovieByReleaseYear(int year)
    {
        MoviePageResult moviePageResult = new MoviePageResult();
        moviePageResult.setMovies(movieDateMapper.findByYearOrderByScoreDesc(year));
        moviePageResult.setTotalRows(movieDateMapper.countByYearOrderByScoreDesc(year));
        return moviePageResult;
    }

    public MoviePageResult getMovieByReleaseYearMonth(int year,int month)
    {
        MoviePageResult moviePageResult = new MoviePageResult();
        moviePageResult.setMovies(movieDateMapper.findByYearMonthOrderByScoreDesc(year,month));
        moviePageResult.setTotalRows(movieDateMapper.countByYearMonthOrderByScoreDesc(year,month));
        return moviePageResult;
    }

    public MoviePageResult getMovieByReleaseYearSeason(int year,int season)
    {
        int startMonth = 1;
        int endMonth = 12;
        switch (season) {
            case 1:
                startMonth = 1;
                endMonth = 3;
                break;
            case 2:
                startMonth =4 ;
                endMonth = 6;
                break;
            case 3:
                startMonth = 7;
                endMonth = 9;
                break;
            case 4:
                startMonth = 10;
                endMonth =12;
                break;
        }
        MoviePageResult moviePageResult = new MoviePageResult();
        moviePageResult.setMovies(movieDateMapper.findByYearSeasonOrderByScoreDesc(year,startMonth,endMonth));
        moviePageResult.setTotalRows(movieDateMapper.countByYearSeasonOrderByScoreDesc(year,startMonth,endMonth));
        return moviePageResult;
    }

    public List<MovieDateResult> getMovieCountByYears(int startYear, int endYear)
    {
        List<MovieDateResult> result=new ArrayList<>();
        result=movieDateMapper.findMoviesCountByYears(startYear, endYear);
        return result;
    }

    public List<MovieDateResult> getMovieCountByMonths(int year)
    {
        List<MovieDateResult> result=new ArrayList<>();
        result=movieDateMapper.findMoviesCountByMonths(year);
        return result;
    }
}