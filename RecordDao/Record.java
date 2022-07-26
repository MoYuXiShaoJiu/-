package com.example.aircraftwar.JavaCode.edu.hitsz.RecordDao;


import java.io.Serializable;

public class Record implements Serializable {
    private int recordRank;
    private String userName;
    private String time;
    private int score;
    private int recordId;



    public Record(String userName, int score){
        this.userName = userName;
        this.score = score;
    }

    public void setRecordRank(int recordRank)
    {
        this.recordRank = recordRank;
    }
    public void setRecordId(int recordId)
    {
        this.recordId = recordId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public void setTime(String time){
        this.time = time;
    }


    public int getScore(){
        return score;
    }
    public int getRecordRank(){
        return recordRank;
    }
    public int getRecordId(){
        return recordId;
    }
    public String getUserName(){
        return userName;
    }
    public String getTime(){
        return time.substring(0,19);
    }



    public void printRecord(){
        System.out.println(": "+userName+","+score+","+time.substring(0,19));
    }


}
