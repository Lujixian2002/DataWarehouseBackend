package cn.edu.tongji.dwbackend.Mysql.Mapper;

import cn.edu.tongji.dwbackend.Mysql.entity.movie;
import cn.edu.tongji.dwbackend.dto.MovieDateResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDateMapper extends BaseMapper<movie> {

    @Select(value = "SELECT * FROM movie WHERE year=#{year} ORDER BY movieScore DESC Limit 100")
    List<movie> findByYearOrderByScoreDesc(@Param("year") int year);

    @Select(value = "SELECT COUNT(*) FROM movie WHERE year=#{year} ")
    int countByYearOrderByScoreDesc(@Param("year") int year);

    @Select(value = "SELECT * FROM movie WHERE year=#{year} AND month=#{month} ORDER BY movieScore DESC Limit 100")
    List<movie> findByYearMonthOrderByScoreDesc(@Param("year") int year,@Param("month")int month);

    @Select(value = "SELECT COUNT(*) FROM movie WHERE year=#{year} AND month=#{month} ")
    int countByYearMonthOrderByScoreDesc(@Param("year") int year,@Param("month")int month);

    @Select(value = "SELECT * FROM movie WHERE year=#{year} AND month>=#{startMonth} AND month<=#{endMonth} ORDER BY movieScore DESC Limit 100")
    List<movie> findByYearSeasonOrderByScoreDesc(@Param("year") int year,@Param("startMonth")int startMonth,@Param("endMonth") int endMonth);

    @Select(value = "SELECT COUNT(*) FROM movie WHERE year=#{year} AND month>=#{startMonth} AND month<=#{endMonth} ")
    int countByYearSeasonOrderByScoreDesc(@Param("year") int year,@Param("startMonth")int startMonth,@Param("endMonth") int endMonth);

    @Select(value = "SELECT year AS date, COUNT(*) AS count FROM movie WHERE year>=#{start} AND year<=#{end} " +
            "GROUP BY year " +
            "ORDER BY year")
    List<MovieDateResult> findMoviesCountByYears(@Param("start") int startYear,@Param("end") int endYear);

    @Select(value = "SELECT month AS date, COUNT(*) AS count FROM movie WHERE year=#{year} " +
            "GROUP BY month " +
            "ORDER BY month")
    List<MovieDateResult> findMoviesCountByMonths(@Param("year") int year);
}
