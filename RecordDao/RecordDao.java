package com.example.aircraftwar.JavaCode.edu.hitsz.RecordDao;

import java.util.List;

public interface RecordDao {
    void findByld(int recordId);
    List<Record>getAllRecords();
    void doAdd(Record record);

    void RecordsSort();

    void printAllRecords();
}
