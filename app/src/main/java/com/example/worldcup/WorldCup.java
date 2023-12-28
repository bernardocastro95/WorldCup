package com.example.worldcup;

public class WorldCup {
    private String cup_id, host, year, champion, runnerup, striker;

    public WorldCup(){

    }

    public WorldCup(String cup_id, String host, String year, String champion, String runnerup, String striker) {
        this.cup_id = cup_id;
        this.host = host;
        this.year = year;
        this.champion = champion;
        this.runnerup = runnerup;
        this.striker = striker;
    }

    public String getCup_id() {
        return cup_id;
    }

    public void setCup_id(String cup_id) {
        this.cup_id = cup_id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public String getRunnerup() {
        return runnerup;
    }

    public void setRunnerup(String runnerup) {
        this.runnerup = runnerup;
    }

    public String getStriker() {
        return striker;
    }

    public void setStriker(String striker) {
        this.striker = striker;
    }
}
