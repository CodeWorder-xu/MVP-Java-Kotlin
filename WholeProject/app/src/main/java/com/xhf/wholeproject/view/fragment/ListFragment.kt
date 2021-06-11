package com.xhf.wholeproject.view.fragment

import android.Manifest
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.OnClick
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xhf.wholeproject.R
import com.xhf.wholeproject.base.BaseFragment
import com.xhf.wholeproject.utils.ToastUtil
import com.xhf.wholeproject.view.activity.MainActivity

/***
 * Date：2021/3/23
 *
 * author:Xu.Mr
 *
 * content:
 */
class ListFragment : BaseFragment() {
    @BindView(R.id.bt_retrieve)
    lateinit var btRetrieve: Button

    @BindView(R.id.bt_manual)
    lateinit var btManual: Button

    @BindView(R.id.rlv_booklist)
    lateinit  var rlvBooklist: RecyclerView
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_list
    }

    override fun initViewsAndEvents() {
        super.initViewsAndEvents()
    }

    @OnClick(R.id.bt_retrieve, R.id.bt_manual)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_retrieve -> {


                 //文件权限
                val rxPermissions = activity?.let { RxPermissions(it) }
                if (rxPermissions != null) {
                    rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe {
                                if (it) {

                                } else {
                                    showToast("请打开文件权限")
                                }
                            }
                }

            }
            R.id.bt_manual -> {
            }
        }
    }
}