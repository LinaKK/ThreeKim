package com.example.studyforce;

//schedule 구성요소

public class Schedule {

    private String sDate;
    private String eDate;
    private String Title;
    private String Content;
    private int eDay;
    private int eMonth;
    private int eYear;
    private int sDay;
    private int sMonth;
    private int sYear;


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



    public Schedule(String Title, int sYear, int sMonth, int sDay, int eYear, int eMonth, int eDay, String Content){
        this.Title = Title;
        this.sYear = sYear;
        this.sMonth=sMonth;
        this.sDay =sDay;
        this.eYear = eYear;
        this.eMonth=eMonth;
        this.eDay = eDay;
        this.Content=Content;
    }

}
