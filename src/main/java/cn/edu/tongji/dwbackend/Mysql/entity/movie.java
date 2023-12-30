package cn.edu.tongji.dwbackend.Mysql.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "movie")
public class movie {
    @TableId(value = "movieID")
    private String movieID;
    private String title;
    private Double movieScore;
    private Date releaseDate;
    private int year;
    private int month;
    private int day;
    private int week;
    private int commentNum;
    private Double ratingScore;
}
