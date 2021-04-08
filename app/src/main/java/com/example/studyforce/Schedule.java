package com.example.studyforce;

//schedule 구성요소

public class Schedule {

    private String sDate;
    private String eDate;
    private String Title;
    private String Content;


    public void setSDate(String sdate){
        sDate = sdate;
    }

    public void setEDate(String edate){
        eDate = edate;
    }

    public void setTitle(String title){
        Title = title;
    }

    public void setContents(String content){
        Content = content;
    }

    public String getTitle(){
        return this.Title;
    }

    public String getContents(){
        return this.Content;
    }

    public String getSDate(){
        return this.sDate;
    }

    public String getEDate(){
        return this.eDate;
    }


}
