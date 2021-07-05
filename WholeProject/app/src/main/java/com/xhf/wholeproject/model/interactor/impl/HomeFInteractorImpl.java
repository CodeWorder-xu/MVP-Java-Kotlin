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
        list.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        list.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        list.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
//        list.add("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=图片&step_word=&hs=0&pn=22&spn=0&di=4100&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1904359974%2C2894644924&os=40464801%2C111987853&simid=4274587805%2C638483034&adpicid=0&lpn=0&ln=1879&fr=&fmq=1625126052724_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=11&oriquery=&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F13580409004%2F0%26refer%3Dhttp%3A%2F%2Finews.gtimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1627718100%26t%3D931a47b48700e4f4806a50ae52009b33&fromurl=ippr_z2C%24qAzdH3FAzdH3Fetjo_z%26e3Btgjof_z%26e3Bqq_z%26e3Bv54AzdH3FodAzdH3Fdad8acdbAaAaSYaa&gsm=c&rpstart=0&rpnum=0&islist=&querylist=&nojc=undefined");
//        list.add("http://b2b.image.e-edusky.com/o_1c8ka7ble1q7g1gr61rq41u6v46td1521100238514.png");
        return list;
    }
}