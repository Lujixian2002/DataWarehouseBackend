package cn.edu.tongji.dwbackend.dto;

import cn.edu.tongji.dwbackend.Mysql.entity.movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviePageResult {
    private List<movie> movies;
    private int totalRows;
}
