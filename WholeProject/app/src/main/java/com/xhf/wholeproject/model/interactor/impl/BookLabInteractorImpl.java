package com.xhf.wholeproject.model.interactor.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.xhf.wholeproject.model.entity.bean.BookBean;
import com.xhf.wholeproject.model.entity.bean.BookParkBean;
import com.xhf.wholeproject.model.interactor.inf.BookInteractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/***
 *Date：21-6-17
 *
 *author:Xu.Mr
 *
 *content:
 */
public class BookLabInteractorImpl implements BookInteractor {
    private ArrayList<BookBean> bookList = new ArrayList<>();
    private ArrayList<BookParkBean> bookParkList = new ArrayList<>();
    private Context context;
    private AssetManager mAssetManager;
    private final String IMAGE = "image";
    private final String TEXT = "text";
    //assets中的文件名清单
    private String[] mAssetsImageList;
    private String[] mAssetsTextList;

    public BookLabInteractorImpl(Context context) {
        this.context = context;
        mAssetManager = context.getAssets();

    }

    //从asset获取的值
    @Override
    public ArrayList<BookBean> getAssetsFiles() {
        if (bookList.size() != 0) {
            bookList.clear();
        }

        //获取image、text中的文件名清单
        try {
            mAssetsImageList = mAssetManager.list(IMAGE);
            mAssetsTextList = mAssetManager.list(TEXT);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < mAssetsTextList.length; i++) {
            //获取书名
            String[] nameSplit = mAssetsTextList[i].split("_");
            String nameSecond = nameSplit[nameSplit.length - 1];
            String bookTitle = nameSecond.replace(".txt", "");

//            //获取封面
            String imagePath = IMAGE + "/" + mAssetsImageList[i];
            Bitmap bookCover = loadImage(imagePath);
//
//            //获取文本
            String textPath = TEXT + "/" + mAssetsTextList[i];
            String bodyText = loadText(textPath);


            BookBean book = new BookBean(bookTitle, bookCover, bodyText);
            bookList.add(book);

        }


        return bookList;
    }

    @Override
    public void getPhoneFiles() {

    }

    @Override
    public ArrayList<BookParkBean> getPartList() {
        if (bookList.size() != 0) {
            bookList.clear();
        }

        //获取image、text中的文件名清单
        try {
            mAssetsImageList = mAssetManager.list(IMAGE);
            mAssetsTextList = mAssetManager.list(TEXT);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < mAssetsTextList.length; i++) {
            //获取书名
            String[] nameSplit = mAssetsTextList[i].split("_");
            String nameSecond = nameSplit[nameSplit.length - 1];
            String bookTitle = nameSecond.replace(".txt", "");

//            //获取封面
            String imagePath = IMAGE + "/" + mAssetsImageList[i];
            Bitmap bookCover = loadImage(imagePath);


            BookParkBean book = new BookParkBean(bookTitle, bookCover,1,1);
            bookParkList.add(book);
        }
        return  bookParkList;
    }

    @Override
    public void getDefaultFiles() {

    }


    //从assets中读取文本
    private String loadText(String path) {
        InputStream in = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            in = mAssetManager.open(path);
            reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();

    }

    //从assets中读取图片
    private Bitmap loadImage(String path) {
        Bitmap image = null;
        InputStream in = null;
        try {
            in = mAssetManager.open(path);
            image = BitmapFactory.decodeStream(in);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return image;
    }


}