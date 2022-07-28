package com.bean;

public class College {

    private Long id;
    private String collegeName;
    private Integer years;

    public College() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "College [id=" + id + ", collegeName=" + collegeName + ", years=" + years + "]";
    }

}
