package cn.edu.tongji.dwbackend.Mysql.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "movie_style")
public class movie_style {
    private String movieID;
    private String styleID;
}
