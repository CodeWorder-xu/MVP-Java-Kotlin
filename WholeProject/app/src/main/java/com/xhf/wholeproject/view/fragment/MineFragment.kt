package com.xhf.wholeproject.view.fragment

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.xhf.wholeproject.R
import com.xhf.wholeproject.base.BaseFragment
import com.xhf.wholeproject.view.activity.InfoMessage
import java.lang.NumberFormatException
import java.nio.IntBuffer
import java.util.logging.Logger

/***
 * Date：2021/3/23
 *
 * author:Xu.Mr
 *
 * content:
 */
class MineFragment : BaseFragment() {

    @BindView(R.id.tv_detalis)
    lateinit var Tvdetalis: TextView

    @BindView(R.id.tv_unlogin)
    lateinit var Tvunlogin: TextView
    //String 可能为空
    var sum: String? = "";


    val value: Int = 10;

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
//        Tvdetalis.setOnClickListener {  }
        sum = getSum(15, 32);
        try {
            var getSum1 = sum?.toInt();//当sum为空时,直接返回
            var getSum0 = sum!!.toInt();//当sum为空时,抛出异常
        } catch (e: NumberFormatException) {

        }
        val value: Int = 10000
        val bigVlaue: Int? = value
        val anotherBigValue: Int? = value
        Toast.makeText(activity, "===的值为=" + (bigVlaue === anotherBigValue).toString(), Toast.LENGTH_LONG).show()
        Toast.makeText(activity, "==的值为=" + (bigVlaue == anotherBigValue).toString(), Toast.LENGTH_LONG).show()
        Tvdetalis.setOnClickListener {
            Toast.makeText(activity, getValue("").toString(), Toast.LENGTH_LONG).show()
//            readyGo(InfoMessage::class.java)
        }
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
}
