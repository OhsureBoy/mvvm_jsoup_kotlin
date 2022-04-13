package com.study.crawling.ui.grid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.crawling.data.model.ImageCrawling
import com.study.crawling.databinding.ItemGridViewImageBinding
import com.study.crawling.util.RecyclerScrollListener

class GridAdapter() : RecyclerView.Adapter<GridViewHolder>(){

    private var itemList: ArrayList<ImageCrawling> = ArrayList()
    private var endScrollListener: RecyclerScrollListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val itemBinding = ItemGridViewImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(itemList[position])
        
        //리사이클러뷰 스크롤 끝부분 감지
        if(itemList.size - position == 1) {
            endScrollListener?.onEndScroll()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(addItemList : ArrayList<ImageCrawling>) {
        itemList.clear()
        itemList.addAll(addItemList)
        notifyItemChanged(1)
    }

    fun setEndScroll(listener: RecyclerScrollListener) {
        endScrollListener = listener
    }
}