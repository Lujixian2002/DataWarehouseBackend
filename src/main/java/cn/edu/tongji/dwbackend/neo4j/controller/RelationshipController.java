package cn.edu.tongji.dwbackend.neo4j.controller;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
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

    public RelationshipController(Driver driver){
        this.driver = driver;
    }

    // 经常合作演员和导演
    @GetMapping(path = "/actorAndDirector",produces =  MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> findMostCooperateActorAndDirector(){
        try (Session session = driver.session()) {
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result res=
                    session.run("MATCH (p:Person {name: 'George Miller'})-[:direct]->(m:Movie)"
                    +"RETURN p.name AS actor, COUNT(m) AS moviesDirected; "
                    );
            System.out.println("The response is : "+res);


            // 记录结束时间
            long endTime = System.currentTimeMillis();

            List<Record> relation = res.list();
            HashMap<String,Object> response = new HashMap<>();

            response.put("actor",relation.get(0).get(0).toString());
            response.put("number",relation.get(0).get(1).toString());
            response.put("time",endTime-startTime);

            return response;
        }
    }

    @GetMapping(path = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> findMostCooperateActors(){
        try (Session session = driver.session()) {
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result res = session.run("Match (p:Person)-[r:MainAct|Act]->(m:Movie)<-[a:MainAct|Act]-(q:Person) " +
                            "where id(p)<> id(q) return p.name,q.name,count(m) order by count(m) desc limit 1");

            // 记录结束时间
            long endTime = System.currentTimeMillis();

            List<Record> relation = res.list();
            HashMap<String,Object> response = new HashMap<>();

            List<String> actors= new ArrayList<>();
            actors.add(relation.get(0).get(0).asString());
            actors.add(relation.get(0).get(1).asString());
            response.put("actor",actors);
            response.put("number",relation.get(0).get(2).toString());
            response.put("time",endTime-startTime);

            return response;
        }
    }
}
