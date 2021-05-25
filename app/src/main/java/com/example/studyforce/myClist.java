package com.example.studyforce;

import java.io.Serializable;

public class myClist implements Serializable {

    String classname;
    String subject;
    String goal;
    int open;

    public myClist(String classname, String subject, String goal, int open){
        this.classname = classname;
        this.subject = subject;
        this.goal = goal;
        this.open = open;
    }
}
