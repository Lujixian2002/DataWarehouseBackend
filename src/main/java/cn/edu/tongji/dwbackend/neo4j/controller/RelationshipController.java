package cn.edu.tongji.dwbackend.neo4j.controller;

import cn.edu.tongji.dwbackend.neo4j.reponse.CooperationResponse;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 此处负责如下查询：
 *      1. 查询经常合作的演员
 *      2. 查询经常合作的演员和导演
 */
@RestController
@RequestMapping("/neo4j/relation")
public class RelationshipController {
    private final Driver driver;

    public RelationshipController() {
        driver = GraphDatabase.driver("bolt://121.199.164.213:7687",
                AuthTokens.basic("neo4j", "datawarehouse"));
    }

    /**
     * 经常合作演员和演员
     * @param rank
     * @return
     */
    @GetMapping(path = "/actorAndDirector",produces =  MediaType.APPLICATION_JSON_VALUE)
    public List<CooperationResponse> findMostCooperateActorAndDirector(int rank){
        try (Session session = driver.session()) {
            System.out.println(session);
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result res=
                    session.run(
                            "MATCH (p1:Person)-[r:`director-actor`]->(p2:Person)\n" +
                                    "WITH p1, p2, r.cooperateTimes AS cooperateTimes\n" +
                                    "ORDER BY cooperateTimes DESC\n" +
                                    "LIMIT " + rank +" "+
                                    "RETURN p1, p2, cooperateTimes;"
                    );
            System.out.println("The response is : "+res);
            // 记录结束时间
            long endTime = System.currentTimeMillis();

            List<org.neo4j.driver.Record> records =res.list();
            // 数据存储与返回
            System.out.println("Response is : "+records);
            List<CooperationResponse> cooperationResponses= new ArrayList<>();

            for (org.neo4j.driver.Record record : records) {
                CooperationResponse cooperationResponse= new CooperationResponse();

                System.out.println(record.get("cooperateTimes"));
                // 去除双引号
                int cooperateTimes = Integer.parseInt(record.get("cooperateTimes").toString().replaceAll("\"", ""));
                cooperationResponse.setTimes(cooperateTimes);

                cooperationResponse.setName1(record.get("p1").asMap().get("name").toString());
                cooperationResponse.setName2(record.get("p2").asMap().get("name").toString());
                cooperationResponses.add(cooperationResponse);

                cooperationResponse.setQueryTime(endTime-startTime);
            }

            return cooperationResponses;
        }
    }

    @GetMapping(path = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CooperationResponse> findMostCooperateActors(int rank){
        try (Session session = driver.session()) {
            System.out.println(session);
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result res=
                    session.run(
                            "MATCH (p1:Person)-[r:`actor-actor`]->(p2:Person)\n" +
                                    "WITH p1, p2, r.cooperateTimes AS cooperateTimes\n" +
                                    "ORDER BY cooperateTimes DESC\n" +
                                    "LIMIT " + rank +" "+
                                    "RETURN p1, p2, cooperateTimes;"
                    );
            System.out.println("The response is : "+res);
            // 记录结束时间
            long endTime = System.currentTimeMillis();


            List<org.neo4j.driver.Record> records =res.list();
            // 数据存储与返回
            System.out.println("Response is : "+records);
            List<CooperationResponse> cooperationResponses= new ArrayList<>();

            for (org.neo4j.driver.Record record : records) {
                CooperationResponse cooperationResponse= new CooperationResponse();

                System.out.println(record.get("cooperateTimes"));
                // 去除双引号
                int cooperateTimes = Integer.parseInt(record.get("cooperateTimes").toString().replaceAll("\"", ""));
                cooperationResponse.setTimes(cooperateTimes);

                cooperationResponse.setName1(record.get("p1").asMap().get("name").toString());
                cooperationResponse.setName2(record.get("p2").asMap().get("name").toString());
                cooperationResponses.add(cooperationResponse);

                cooperationResponse.setQueryTime(endTime-startTime);
            }

            return cooperationResponses;
        }
    }
}
