package com.xhf.wholeproject.view.fragment

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.xhf.wholeproject.R
import com.xhf.wholeproject.base.BaseFragment
import com.xhf.wholeproject.presenter.impl.MineFPresenterImpl
import com.xhf.wholeproject.view.activity.InfoMessageActivity
import com.xhf.wholeproject.viewInterface.MineFView

/***
 * Date：2021/3/23
 *
 * author:Xu.Mr
 *
 * content:我得个人信息界面
 */
class MineFragment : BaseFragment(), MineFView {
    @BindView(R.id.iv_portrait)
    lateinit var ivPortrait: ImageView

    @BindView(R.id.tv_details)
    lateinit var tvDetails: TextView

    @BindView(R.id.tv_account)
    lateinit var tvAccount: TextView

    @BindView(R.id.tv_cache)
    lateinit var tvCache: TextView

    @BindView(R.id.tv_currency)
    lateinit var tvCurrency: TextView

    @BindView(R.id.tv_about)
    lateinit var tvAbout: TextView


    @BindView(R.id.tv_unlogin)
    lateinit var tvUnlogin: TextView

    //String 可能为空
    var sum: String? = "";


    val value: Int = 10;

//    lateinit var view: View = null
//    lateinit var popWidonw: PopupWindow

    /**
     * var 可修改
     * val 只能赋值一次
     *
     */

    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_setting
    }

    override fun initViewsAndEvents() {
        super.initViewsAndEvents()
        val mineFPresenter = activity?.let { MineFPresenterImpl(it, this) };
        mineFPresenter?.onPortraitUtil()
    }


    private fun utils() {
        sum = getSum(15, 32);
        try {
            var getSum1 = sum?.toInt();//当sum为空时,直接返回
            var getSum0 = sum!!.toInt();//当sum为空时,抛出异常
        } catch (e: Exception) {

        }
        val value: Int = 10000
        val bigVlaue: Int? = value
        val anotherBigValue: Int? = value
        Toast.makeText(activity, "===的值为=" + (bigVlaue === anotherBigValue).toString(), Toast.LENGTH_LONG).show()
        Toast.makeText(activity, "==的值为=" + (bigVlaue == anotherBigValue).toString(), Toast.LENGTH_LONG).show()

        val m = intArrayOf(1, 2, 5, 10, 99, 88, 6671, 11, 3)
        val maopao = maopao(m)
        print("我的值为：" + maopao.size)
        val a: Int = 10000


        //经过了装箱，创建了两个不同的对象
//        val boxedA: Int? = a
//        val anotherBoxedA: Int? = a
//
//        虽然经过了装箱，但是值是相等的，都是10000
//        Toast.makeText(activity, "===的值为=" + (boxedA === anotherBoxedA).toString(), Toast.LENGTH_LONG).show()
//        Toast.makeText(activity, "==的值为=" + (boxedA == anotherBoxedA).toString(), Toast.LENGTH_LONG).show()
    }

    @OnClick(R.id.tv_details, R.id.tv_unlogin, R.id.tv_about, R.id.tv_account, R.id.tv_cache, R.id.tv_currency)
    fun onClick(v: View) {
        when (v.id) {
            R.id.tv_details -> {
                //个人信息
                readyGo(InfoMessageActivity::class.java)
            }
            R.id.tv_account -> {
                //账号安全

            }
            R.id.tv_unlogin -> {
                //退出登录
//                view = View.inflate(activity, R.layout.popup_unlogin, null)
//                popWidonw = PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true)
//                var myDialog = MyDialog(activity, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, view, R.style.TextStyle)
//
//                myDialog.show();
            }
            R.id.tv_about -> {
                //关于静思
            }
            R.id.tv_cache -> {
                //清除缓存
            }
            R.id.tv_currency -> {
                //通用设置
            }

        }


    }

    fun getSum(a: Int, b: Int): String {
        var z: String?
        z = (a + b).toString()
        return z
    }

    fun getValue(name: String): Int? {
        if (name is String) {
            return name.length
        }
        return null
    }

    fun maopao(arr: IntArray): IntArray {
        var z: Int = 0
        var y: Int = 0
        var temp: Int = 0
        while (z < arr.size) {
            y = z + 1
            while (y < arr.size) {
                if (arr[z] > arr[y]) {
                    temp = arr[z]
                    arr[z] = arr[y]
                    arr[y] = temp
                }
                y++
            }
            z++
        }

        for (index in arr.indices) {
            Log.d("/////////////", "冒泡的值为" + arr[index]);
//            Toast.makeText(activity, arr[index] .toString(), Toast.LENGTH_LONG).show()
        }
        return arr
    }

    //有头像时
    override fun onPortrait(imageUrl: String) {
        activity?.let { Glide.with(it).load(imageUrl).apply(RequestOptions.bitmapTransform(CircleCrop())).into(ivPortrait) }
    }

    //默认头像
    override fun onPortrait(imageUrl: Drawable) {
        activity?.let { Glide.with(it).load(imageUrl).apply(RequestOptions.bitmapTransform(CircleCrop())).into(ivPortrait) }
    }

}
