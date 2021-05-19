package com.example.studyforce;

public class wholeclist {
    int classnum;
    String classname;
    int num;//학번 or 사번
    String name;
    String subject;
    String goal;
    int open;
    int pw;

    public wholeclist(int classnum, String classname, int num, String name, String subject, String goal, int open, int pw){
        this.classnum = classnum;
        this.classname = classname;
        this.num = num;
        this.name = name;
        this.subject = subject;
        this.goal = goal;
        this.open = open;
        this.pw = pw;
    }

}
