package cn.edu.tongji.dwbackend.neo4j.controller;

import org.neo4j.driver.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.neo4j.driver.types.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 此处实现查询如下：
 *      1. 某导演共有多少电影
 *      2. 演员主演多少电影
 *      3. 演员参演多少电影
 */
@RestController
@RequestMapping("/neo4j/person")
public class PersonController {

    private final Driver driver;

    public PersonController() {
        driver = GraphDatabase.driver("bolt://121.199.164.213:7687",
                AuthTokens.basic("neo4j", "datawarehouse"));
    }


    /**
     * 1. 查询某一个导演作品数量以及其作品
     * @param directName
     * @return
     */
    @GetMapping(path = "/directNum", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> countDirectNum(@RequestParam String directName){
        try (Session session = driver.session()) {
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result res= session.run(

                    "MATCH (p:Person {name: '"+ directName +"'})-[:direct]->(m:Movie)\n" +
                        "RETURN COUNT(m),COLLECT(m);"

            );
            // 记录结束时间
            long endTime = System.currentTimeMillis();

            HashMap<String,Object> response = new HashMap<>();
            // 存储结束时间
            response.put("time",endTime-startTime);

            List<org.neo4j.driver.Record> records =res.list();
            // 数据存储与返回
            System.out.println("Response is : "+records);

            for (org.neo4j.driver.Record record : records) {
                // 提取 COUNT(m) 和 COLLECT(m) 的值
                Long count = record.get("COUNT(m)").asLong();
                System.out.println(count);
                response.put("count",count);

                // 获取 COLLECT(m) 的列表
                List<Node> nodeList = record.get("COLLECT(m)").asList(Value::asNode);
                List<String> nameList=new ArrayList<>();
                if(count>100){
                    nodeList= nodeList.subList(0,100);
                }
                // 处理每个 Node 的值
                for (Node node : nodeList) {
                    System.out.println("Node Name is"+node.asMap().get("title"));
                    String nodeName = node.asMap().get("title").toString(); //  Node 有一个名为 "name" 的属性
                    nameList.add(nodeName);
                }
                response.put("Nodes:",nameList);
            }

            return response;
        }
    }

    /**
     * 2. 统计演员参演数量
     * @param actorName
     * @return
     */
    @GetMapping(path = "/actNum", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> countActNum(@RequestParam String actorName){
        try (Session session = driver.session()) {
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result res= session.run(
                    "MATCH (p:Person {name:'"+actorName+"'})-[:act|:main_act]->(m:Movie)\n" +
                        "RETURN COUNT(m), COLLECT(m);"
            );
            // 记录结束时间
            long endTime = System.currentTimeMillis();

            HashMap<String,Object> response = new HashMap<>();
            // 存储结束时间
            response.put("time",endTime-startTime);

            List<org.neo4j.driver.Record> records =res.list();
            // 数据存储与返回
            System.out.println("Response is : "+records);

            for (org.neo4j.driver.Record record : records) {
                // 提取 COUNT(m) 和 COLLECT(m) 的值
                Long count = record.get("COUNT(m)").asLong();
                System.out.println(count);
                response.put("count",count);

                // 获取 COLLECT(m) 的列表
                List<Node> nodeList = record.get("COLLECT(m)").asList(Value::asNode);
                List<String> nameList=new ArrayList<>();
                if(count>100){
                    nodeList= nodeList.subList(0,100);
                }
                // 处理每个 Node 的值
                for (Node node : nodeList) {
                    System.out.println("Node Name is"+node.asMap().get("title"));
                    String nodeName = node.asMap().get("title").toString(); //  Node 有一个名为 "name" 的属性
                    nameList.add(nodeName);
                }
                response.put("Nodes:",nameList);
            }

            return response;
        }
    }

    /**
     * 3. 查询主演了多少电影
     * @param actorName
     * @return
     */
    @GetMapping(path = "/mainActNum", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> countMainActNum(@RequestParam String actorName){
        try (Session session = driver.session()) {
            // 记录开始时间
            long startTime = System.currentTimeMillis();
            Result res= session.run(
                    "MATCH (p:Person {name:'"+actorName+"'})-[:main_act]->(m:Movie)\n" +
                            "RETURN COUNT(m), COLLECT(m);"
            );

            // 记录结束时间
            long endTime = System.currentTimeMillis();

            HashMap<String,Object> response = new HashMap<>();
            // 存储结束时间
            response.put("time",endTime-startTime);

            List<org.neo4j.driver.Record> records =res.list();
            // 数据存储与返回
            System.out.println("Response is : "+records);

            for (org.neo4j.driver.Record record : records) {
                // 提取 COUNT(m) 和 COLLECT(m) 的值
                Long count = record.get("COUNT(m)").asLong();
                System.out.println(count);
                response.put("count",count);

                // 获取 COLLECT(m) 的列表
                List<Node> nodeList = record.get("COLLECT(m)").asList(Value::asNode);
                List<String> nameList=new ArrayList<>();
                if(count>100){
                    nodeList = nodeList.subList(0,100);
                }
                // 处理每个 Node 的值
                for (Node node : nodeList) {
                    System.out.println("Node Name is"+node.asMap().get("title"));
                    String nodeName = node.asMap().get("title").toString(); //  Node 有一个名为 "name" 的属性
                    nameList.add(nodeName);
                }
                response.put("Nodes:",nameList);
            }

            return response;

        }
    }


}
