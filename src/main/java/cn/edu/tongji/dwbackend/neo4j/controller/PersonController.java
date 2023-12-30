package cn.edu.tongji.dwbackend.neo4j.controller;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


/**
 * 此处实现查询如下：
 *      1. 某导演共有多少电影
 *      2. 演员主演多少电影
 *      3. 演员参演多少电影
 *      4. 经常合作演员有哪些
 *      5. 经常合作的演员+导演有哪些
 */
@RestController
@RequestMapping("/neo4j/person")
public class PersonController {

    private final Driver driver;

    public PersonController(Driver driver){
        this.driver = driver;
    }
    @GetMapping(path = "/directNum", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> countDirectNum(@RequestParam String directName){
        try (Session session = driver.session()) {
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result res= session.run(
                    "MATCH (p:Person {name: '"+ directName +"'})-[:direct]->(m:Movie)\n" +
                        "RETURN COUNT(m);"

            );
            System.out.println("Response is : "+res);
            // 记录结束时间
            long endTime = System.currentTimeMillis();

            HashMap<String,Object> response = new HashMap<>();
            response.put("time",endTime-startTime);
            return response;
        }
    }

    /**
     * 统计演员参演数量
     * @param actorName
     * @return
     */
    @GetMapping(path = "/actNum", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> countActNum(@RequestParam String actorName){
        try (Session session = driver.session()) {
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result resA= session.run(
                    "MATCH (p:Person {name: '"+ actorName +"'})-[:act]->(m:Movie)\n" +
                            "RETURN COUNT(m);"
            );
            Result resB= session.run(
                    "MATCH (p:Person {name: '"+ actorName +"'})-[:main_act]->(m:Movie)\n" +
                            "RETURN COUNT(m);"
            );

            // 记录结束时间
            long endTime = System.currentTimeMillis();

            HashMap<String,Object> response = new HashMap<>();
            response.put("time",endTime-startTime);
            return response;
        }
    }

    @GetMapping(path = "/mainActNum", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> countMainActNum(@RequestParam String actorName){
        try (Session session = driver.session()) {
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result res= session.run(
                    "MATCH (p:Person {name: '"+ actorName +"'})-[:main_act]->(m:Movie)\n" +
                            "RETURN COUNT(m);"
            );

            // 记录结束时间
            long endTime = System.currentTimeMillis();

            HashMap<String,Object> response = new HashMap<>();
            response.put("time",endTime-startTime);
            return response;
        }
    }


}
