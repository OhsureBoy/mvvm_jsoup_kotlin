package com.study.crawling.ui.grid.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.study.crawling.R
import com.study.crawling.data.model.ImageCrawling
import com.study.crawling.databinding.ItemGridViewImageBinding
import kotlinx.coroutines.*
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class GridViewHolder(private val itemBinding: ItemGridViewImageBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(gridImageUrl: ImageCrawling) {
        
        //라이브러리 사용
        Glide.with(itemView)
                //Url로 Image Load
            .load(gridImageUrl.ImageUrl)
            //그려지고 있는 사진이 있다면, Loading.gif를 뿌려준다
            .thumbnail(Glide.with(itemView).load(R.drawable.loading))
                //이미지 가져오기를 실패 했으면 loading_fail 이미지를 뿌려준다
            .error(R.drawable.loading_fail)
                //이미지를 item_grid_view_image 에 있는 gridImage 로 전달한다
            .into(itemBinding.gridImage)


//        라이브러리 사용 안함
//        CoroutineScope(Dispatchers.Main).launch {
//            itemBinding.gridImage.setImageResource(R.drawable.loading)
//            val bitmap = withContext(Dispatchers.IO){
//                loadImage(gridImageUrl.ImageUrl)
//            }
//            itemBinding.gridImage.setImageBitmap(bitmap)
//        }
    }

    //Glide 라이브러리 사용 안한 것
    private fun loadImage(bindingImageURL: String): Bitmap? {
        val tag = "GridViewHolder->loadImage"
        
        return try {
            val imageURL = URL(bindingImageURL)
            val stream = imageURL.openStream()

            BitmapFactory.decodeStream(stream)
        } catch (e: IOException) {
            Log.e(tag, "IOException: $e")
            //Exception 이면 Loading_fail 이미지 리턴
            BitmapFactory.decodeResource(itemView.resources,R.drawable.loading_fail)
        }
    }
}