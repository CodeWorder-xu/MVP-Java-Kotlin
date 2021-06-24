package com.xhf.wholeproject.presenter.inf;

import com.xhf.wholeproject.model.entity.bean.BookBean;

import java.util.ArrayList;

/***
 *Date：21-6-17
 *
 *author:Xu.Mr
 *
 *content:
 */
public interface BookFPresenter {
    //这个是获取assets下所有信息的内容
    ArrayList<BookBean> onAllBookList();
    //只获取头像和书名的信息
    void onBookParkList();
}
