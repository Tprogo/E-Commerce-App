package com.example.e_comapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_comapplication.R
import com.example.e_comapplication.models.product_model.Image

class ImgViewPgAdapter(val imgList: List<Image>): RecyclerView.Adapter<ImgViewPgAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.vpager_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.img_viewpager_viewholder, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImage = imgList[position]

        val context = holder.itemView.context


        Glide.with(context)
            .load(currentImage.src)
            .into(holder.image)
    }

}