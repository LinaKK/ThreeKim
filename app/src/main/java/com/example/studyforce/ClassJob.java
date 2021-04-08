package com.example.studyforce;

//whole class list 구성요소

public class ClassJob {
    private String name;
    private String num;
    private String job;
    private String open;



    public void setNumber(String number){
        num = number;
    }

    public void setJobs(String Job){
        job = Job;
    }

    public void setTitles(String title){
        name= title;
    }

    public void setOpen(String opened){open=opened;}

    public String getTitle(){
        return this.name;
    }

    public String getNumber(){
        return this.num;
    }

    public String getJob(){
        return this.job;
    }

    public String getOpen(){return this.open;}
}
