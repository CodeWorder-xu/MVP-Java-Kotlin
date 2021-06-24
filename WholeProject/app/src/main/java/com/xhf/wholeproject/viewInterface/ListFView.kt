package com.xhf.wholeproject.viewInterface

import com.xhf.wholeproject.model.entity.bean.BookBean
import com.xhf.wholeproject.model.entity.bean.BookParkBean
import java.util.ArrayList

/***
 * Date：21-6-21
 *
 * author:Xu.Mr
 *
 * content:
 */
interface ListFView {
    fun onBookList(list:ArrayList<BookParkBean>)
}