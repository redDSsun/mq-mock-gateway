package com.example.datacenter.service.data;

public class Data {
    private Long id;
    private String name;
    private String description;
    private int num;
    private boolean validated;

    public Data(Long id, String name, String description, int num, boolean validated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.num = num;
        this.validated = validated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", num=" + num +
                ", validated=" + validated +
                '}';
    }
}
