package com.xhf.wholeproject.model.entity.bean;

import android.graphics.Bitmap;

/***
 *Date：21-6-22
 *
 *author:Xu.Mr
 *
 *content:
 */
public class BookParkBean {
    private String title;
    private Bitmap bitmap;
    private int allChapter;
    private int chapter;

    public String getTitle() {
        return title;
    }

    public BookParkBean(String title, Bitmap bitmap, int allChapter, int chapter) {
        this.title = title;
        this.bitmap = bitmap;
        this.allChapter = allChapter;
        this.chapter = chapter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getAllChapter() {
        return allChapter;
    }

    public void setAllChapter(int allChapter) {
        this.allChapter = allChapter;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }
}