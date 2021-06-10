package com.xhf.wholeproject.model.interactor.impl;

import android.content.Context;

import com.xhf.wholeproject.model.interactor.inf.HomeFInteractor;

import java.util.ArrayList;

/***
 *Date：6/10/21
 *
 *author:Xu.Mr
 *
 *content:
 */
public class HomeFInteractorImpl implements HomeFInteractor {
    private Context context;
    public HomeFInteractorImpl(Context context){
        this.context=context;
    }
    @Override
    public ArrayList<String> onBannerValue() {
        ArrayList<String> list = new ArrayList<>();
//        list.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
//        list.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        list.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        return list;
    }
}