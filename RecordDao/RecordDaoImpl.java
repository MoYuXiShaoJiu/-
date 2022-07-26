package com.example.aircraftwar.JavaCode.edu.hitsz.RecordDao;

import java.io.*;
import java.util.*;

public class RecordDaoImpl implements RecordDao {
    private List<Record> records;

    public RecordDaoImpl() {
        records = new ArrayList<Record>();
    }

    @Override
    public void doAdd(Record record) {
        records.add(record);
    }



    @Override
    public void findByld(int recordId) {
        for (Record item : records) {
            if (item.getRecordId() == recordId) {

                System.out.println("Find Record: ID [" + recordId + "], User Name [" + item.getUserName() + "],Time[" + item.getTime() + "]");
                return;
            }
        }
        System.out.println("Cannot find this record!");
    }

    @Override
    public List<Record> getAllRecords() {
        if(records==null){
            readRecords();
        }
        return records;
    }

    @Override
    public void RecordsSort() {
        records.sort(Comparator.comparing(Record::getScore));
        Collections.reverse(records);
    }

    @Override
    public void printAllRecords() {
        System.out.println("********************************");
        System.out.println("             积分榜             ");
        System.out.println("********************************");
        for (Record i : records) {
            int index = records.indexOf(i) + 1;
            i.setRecordRank(index);
            System.out.print("第" + index + "名");
            i.printRecord();
        }

    }

    public void writeRecords() {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("records.dat"));
            oos.writeObject(records);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readRecords() {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream("records.dat"));
            records = (List<Record>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public  void deleteRecord(int index){
        records.remove(index);
    }


    public void deleteAtMax(){
        if(records.size()>15){
            records.remove(records.size()-1);
        }
    }
}