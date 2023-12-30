package cn.edu.tongji.dwbackend.Mysql.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "director")
public class director {
    private String directorID;
    private String director;
}
