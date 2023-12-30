package cn.edu.tongji.dwbackend.Mysql.service;

//import cn.edu.tongji.dwbackend.dto.MovieInfoDto;
import cn.edu.tongji.dwbackend.Mysql.Mapper.MovieDateMapper;
import cn.edu.tongji.dwbackend.Mysql.entity.movie;
import cn.edu.tongji.dwbackend.dto.MovieDateResult;
import cn.edu.tongji.dwbackend.dto.MoviePageResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@MapperScan("cn.edu.tongji.dwbackend.Mysql.mapper")
@Service
public interface MovieDateService  {
    MoviePageResult getMovieByReleaseYear(int year);

    MoviePageResult getMovieByReleaseYearMonth(int year,int month);

    MoviePageResult getMovieByReleaseYearSeason(int year,int season);

    List<MovieDateResult> getMovieCountByYears(int startYear,int endYear);

    List<MovieDateResult> getMovieCountByMonths(int year);

}