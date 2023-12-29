package cn.edu.tongji.dwbackend.Mysql.repository;

import cn.edu.tongji.dwbackend.Mysql.entity.FormatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//import javax.persistence.criteria.CriteriaBuilder;


public interface FormatRepository extends JpaRepository<FormatEntity, Integer> {
    FormatEntity findByFormatId(Integer formatId);
}
