package cn.edu.tongji.dwbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDateResult {
    private int count;
    private int date;//可以是月份也可以是年份
}
