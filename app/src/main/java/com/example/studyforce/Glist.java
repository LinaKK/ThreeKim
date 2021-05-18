package com.example.studyforce;

import java.io.Serializable;

public class Glist implements Serializable {
    String name;
    int done;

    public Glist(String name, int done){
        this.name =name;
        this.done=done;
    }
}

