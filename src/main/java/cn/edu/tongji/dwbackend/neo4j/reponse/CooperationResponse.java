package cn.edu.tongji.dwbackend.neo4j.reponse;

public class CooperationResponse {
    int times;
    String Name1;
    String Name2;

    public int getTimes() {
        return times;
    }

    public String getName1() {
        return Name1;
    }

    public void setName1(String name1) {
        Name1 = name1;
    }

    public String getName2() {
        return Name2;
    }

    public void setName2(String name2) {
        Name2 = name2;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
