package com.xhf.wholeproject.model.interactor.inf;

import com.xhf.wholeproject.model.entity.bean.BookBean;
import com.xhf.wholeproject.model.entity.bean.BookParkBean;

import java.util.ArrayList;

/***
 *Date：21-6-17
 *
 *author:Xu.Mr
 *
 *content:
 */
public interface BookInteractor {
    ArrayList<BookBean> getAssetsFiles();
    void getPhoneFiles();
    ArrayList<BookParkBean>  getPartList();
    void getDefaultFiles();
}
