package com.example.eyal.recycleview.common;

/**
 * Created by Eyal on 11/7/2015.
 */
public class TaskItem {
    private String 	description;
    private int id;

    public TaskItem(String description) {
        super();
        this.description = description;


    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}
