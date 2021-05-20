package com.example.studyforce;

import java.io.Serializable;

public class Glist implements Serializable {
    String title;
    String name;
    int done;

    public Glist(String title, String name, int done){
        this.title=title;
        this.name =name;
        this.done=done;
    }
}

