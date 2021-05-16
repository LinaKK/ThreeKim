package com.example.studyforce;

//whole class list 구성요소

public class ClassJob {
    private String name;
    private int num;
    private String job;
    private String open;



    public void setNumber(int num){
        num = num;
    }

    public void setJobs(String job){
        job = job;
    }

    public void setTitles(String name){
        name= name;
    }

    public void setOpen(String open){open=open;}

    public String getTitle(){
        return this.name;
    }

    public int getNumber(){
        return this.num;
    }

    public String getJob(){
        return this.job;
    }

    public String getOpen(){return this.open;}

    /*
    public ClassJob(String name, int num, String job, String open){
        this.name = name;
        this.num = num;
        this.job=job;
        this.open= open;
    }
     */

}
