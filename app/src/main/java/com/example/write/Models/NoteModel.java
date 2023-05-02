package com.example.write.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoteModel {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("lastEdit")
    @Expose
    private String lastEdit;

    public NoteModel(String text, String date, String lastEdit) {
        this.text = text;
        this.date = date;
        this.lastEdit = lastEdit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public String getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit = lastEdit;
    }
}
