package com.xhf.wholeproject.presenter.impl;

import android.content.Context;

import com.xhf.wholeproject.model.entity.bean.BookBean;
import com.xhf.wholeproject.model.entity.bean.BookParkBean;
import com.xhf.wholeproject.model.interactor.impl.BookLabInteractorImpl;
import com.xhf.wholeproject.presenter.inf.BookFPresenter;
import com.xhf.wholeproject.viewInterface.ListFView;

import java.util.ArrayList;

/***
 *Date：21-6-17
 *
 *author:Xu.Mr
 *
 *content:
 */
public class BookFPresenterImpl implements BookFPresenter {
    private Context context;
    private BookLabInteractorImpl bookLabInteractor;
    private ListFView listFView;

    public BookFPresenterImpl(Context context) {
        this.context = context;
        bookLabInteractor = new BookLabInteractorImpl(context);
    }

    public BookFPresenterImpl(Context context, ListFView listFView) {
        this.context = context;
        bookLabInteractor = new BookLabInteractorImpl(context);
        this.listFView = listFView;
    }

    @Override
    public ArrayList<BookBean> onAllBookList() {
        return bookLabInteractor.getAssetsFiles();
    }

    @Override
    public void onBookParkList() {
        listFView.onBookList(bookLabInteractor.getPartList());
    }

}