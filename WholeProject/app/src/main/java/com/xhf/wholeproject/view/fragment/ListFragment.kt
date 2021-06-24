package com.xhf.wholeproject.view.fragment

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xhf.wholeproject.R
import com.xhf.wholeproject.base.BaseFragment
import com.xhf.wholeproject.model.entity.bean.BookBean
import com.xhf.wholeproject.model.entity.bean.BookParkBean
import com.xhf.wholeproject.presenter.impl.BookFPresenterImpl
import com.xhf.wholeproject.utils.ILog
import com.xhf.wholeproject.utils.ManagerUtil
import com.xhf.wholeproject.view.activity.BookReadActivity
import com.xhf.wholeproject.view.adapter.ListFBookAdapter
import com.xhf.wholeproject.viewInterface.ListFView
import java.util.*

/***
 * Date：2021/3/23
 *
 * author:Xu.Mr
 *
 * content:
 */
class ListFragment : BaseFragment(), ListFView {

    @BindView(R.id.bt_retrieve)
    lateinit var btRetrieve: Button

    @BindView(R.id.bt_manual)
    lateinit var btManual: Button

    @BindView(R.id.rlv_booklist)
    lateinit var rlvBooklist: RecyclerView
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_list
    }

    override fun initViewsAndEvents() {
        super.initViewsAndEvents()
       var bookFPresenterImpl = BookFPresenterImpl(activity, this);
        bookFPresenterImpl.onBookParkList()
//        setHasOptionsMenu(true);
    }

    @OnClick(R.id.bt_retrieve, R.id.bt_manual)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_retrieve -> {
                //检索本地
            }
            R.id.bt_manual -> {
                //手动导入
            }
        }
    }

    override fun onBookList(list: ArrayList<BookParkBean>) {
        rlvBooklist.layoutManager = ManagerUtil.getGridLayoutManager(activity, 3)
        val listFBookAdapter = ListFBookAdapter(this.mContext, list);
        rlvBooklist.adapter = listFBookAdapter
        listFBookAdapter.onItemClick = { _, position -> clickUtil(list.indexOf(list.get(position))) }
    }


    fun clickUtil(positioin: Int) {
        //文件权限
        val rxPermissions = activity?.let { RxPermissions(it) }
        if (rxPermissions != null) {
            rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe {
                        if (it) {
                            var book = BookReadActivity.newIntent(activity, positioin)
                            startActivity(book)
                        } else {
                            showToast("请打开文件权限")
                        }
                    }
        }
    }
}




