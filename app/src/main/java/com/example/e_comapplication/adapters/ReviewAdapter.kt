package com.example.e_comapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_comapplication.R
import com.example.e_comapplication.models.CategoriesModel
import com.example.e_comapplication.models.review_model.ReviewModelItem

class ReviewAdapter (private val reviewList: List<ReviewModelItem>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_view_holder, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val reviewData = reviewList[position]

        // sets the image to the imageview from our itemHolder class
        val context = holder.itemView.context

        Glide.with(context)
            .load(reviewData.reviewer_avatar_urls.`96`)
            .into(holder.reviewImage)


        holder.reviewText.setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        // sets the text to the textview from our itemHolder class

       holder.revName.text = reviewData.reviewer
        holder.reviewText.loadDataWithBaseURL(null,
            reviewData.review, "text/html","utf-8",null)


        holder.rating.text = reviewData.rating.toString()


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return reviewList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val reviewImage: ImageView = itemView.findViewById(R.id.reviewverImg)
        val reviewText: WebView = itemView.findViewById(R.id.reviewText)
        val rating: TextView = itemView.findViewById(R.id.revRating)
        val revName: TextView = itemView.findViewById(R.id.reviewerName)
    }
}