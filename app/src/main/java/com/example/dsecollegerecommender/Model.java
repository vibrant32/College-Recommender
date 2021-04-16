package com.example.dsecollegerecommender;

public class Model {
    String dtecode, collegename, location, branch, category;

    public Model(String dtecode, String collegename, String location, String branch, String category) {
        this.dtecode = dtecode;
        this.collegename = collegename;
        this.location = location;
        this.branch = branch;
        this.category = category;
    }

    public String getDtecode() {
        return dtecode;
    }

    public void setDtecode(String dtecode) {
        this.dtecode = dtecode;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) { this.branch = branch; }

    public String getCategory() { return category; }

    public void setCategory(String category) {
        this.category = category;
    }
}
