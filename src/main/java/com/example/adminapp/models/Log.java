package com.example.adminapp.models;

import java.util.Date;

public class Log {
    private Integer id;
    private String poruka;
    private String level;
    private Date datum;
    private String log;

    public Log() {
    }

    public Log(Integer id, String poruka, String level, Date datum, String log) {
        this.id = id;
        this.poruka = poruka;
        this.level = level;
        this.datum = datum;
        this.log = log;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", poruka='" + poruka + '\'' +
                ", level='" + level + '\'' +
                ", datum=" + datum +
                ", log='" + log + '\'' +
                '}';
    }
}
