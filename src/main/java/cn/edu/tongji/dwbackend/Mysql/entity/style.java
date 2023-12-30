package cn.edu.tongji.dwbackend.Mysql.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "style")
public class style {
    @TableId(value = "styleID")
    private String styleID;
    private String style;
}
