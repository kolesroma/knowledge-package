package com.aurosks.kolesnyk.entity;

public class ContainerEntity {
    private Integer id;

    private String title;

    public ContainerEntity(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public ContainerEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
