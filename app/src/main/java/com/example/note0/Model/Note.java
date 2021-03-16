package com.example.note0.Model;

import java.util.ArrayList;

/**
 * Created by Yassine Abou on 1/5/2021.
 */
public class Note extends ArrayList<Note> {

    private String title, time;

    public Note(String title, String time) {
        this.title = title;
        this.time = time;

    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }



}
