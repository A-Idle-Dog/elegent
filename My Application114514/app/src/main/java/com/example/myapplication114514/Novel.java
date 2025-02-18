package com.example.myapplication114514;

public class Novel {
    private int cover;
    private String title;
    private String summary;
    private String filename;

    public Novel(int cover, String title, String summary,String filename) {
        this.filename=filename;
        this.cover = cover;
        this.title = title;
        this.summary = summary;
    }

    public int getCover() {
        return cover;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }
    public String getFilename(){return filename; }
}