package com.xhf.wholeproject.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xhf.wholeproject.R
import com.xhf.wholeproject.model.entity.bean.BookBean
import com.xhf.wholeproject.model.entity.bean.BookParkBean
import com.xhf.wholeproject.view.fragment.ListFragment
import kotlinx.android.synthetic.main.item_booklist.view.*

/***
 *Date：21-6-21
 *
 *author:Xu.Mr
 *
 *content:
 */
class ListFBookAdapter : RecyclerView.Adapter<ListFBookAdapter.ViewHolder> {
    private var list: ArrayList<BookParkBean>? = null
    private var context: Context? = null;

    constructor (context: Context, list: ArrayList<BookParkBean>) : super() {
        this.context = context;
        this.list = list;
    }


    class ViewHolder : RecyclerView.ViewHolder {
        var img_cover: ImageView
        var tv_title: TextView
        var tv_progress: TextView
        var card_view: CardView;

        constructor(itemView: View) : super(itemView) {
            img_cover = itemView.findViewById(R.id.img_cover);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_progress = itemView.findViewById(R.id.tv_progress);
            card_view = itemView.findViewById(R.id.card_view);
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFBookAdapter.ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_booklist, parent, false);

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return list?.size as Int;
    }

    override fun onBindViewHolder(holder: ListFBookAdapter.ViewHolder, position: Int) {
        context?.let { Glide.with(it).load(list?.get(position)?.bitmap).into(holder.img_cover) };
        holder.tv_title.setText(list?.get(position)?.title)
        holder.card_view.setOnClickListener { onItemClick(it, position) }
    }

    var onItemClick: (View, Int) -> Unit = { _, _ -> }


}



