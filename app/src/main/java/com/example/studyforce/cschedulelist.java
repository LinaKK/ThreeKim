package com.example.studyforce;

public class cschedulelist {
    String schTitle;
    int sYear;
    int sMonth;
    int sDay;
    int eYear;
    int eMonth;
    int eDay;
    String schCont;

    public cschedulelist( String schTitle, int sYear, int sMonth, int sDay, int eYear, int eMonth, int eDay, String schCont){
        this.schTitle = schTitle;
        this.sYear = sYear;
        this.sMonth=sMonth;
        this.sDay = sDay;
        this.eYear = eYear;
        this.eMonth =eMonth;
        this.eDay = eDay;
        this.schCont = schCont;

    }
}
