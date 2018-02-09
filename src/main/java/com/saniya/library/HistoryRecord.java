package com.saniya.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String record;

    public HistoryRecord(){
        this.record = "";
    }

    public HistoryRecord(String r){
        this.record = r;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
