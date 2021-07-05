package com.xhf.wholeproject.viewInterface

import android.graphics.drawable.Drawable

/***
 * Date：21-7-2
 *
 * author:Xu.Mr
 *
 * content:
 */
interface MineFView {
    fun onPortrait(imageUrl: String)
    fun onPortrait(drawable: Drawable);
}