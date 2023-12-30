package cn.edu.tongji.dwbackend.Mysql.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "format")
public class format {
    @TableId(value = "formatID")
    private String formatID;
    private String format;
}
