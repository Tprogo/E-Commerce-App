package com.example.e_comapplication.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_comapplication.R
import com.example.e_comapplication.fragments.HomeFragmentDirections
import com.example.e_comapplication.models.product_model.Images
import com.example.e_comapplication.models.product_model.ProductModel
import com.example.e_comapplication.models.product_model.SizeAttribute

class TopRatedProAdapter (private val popProdList: List<ProductModel>) : RecyclerView.Adapter<TopRatedProAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_viewholder, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val prodData = popProdList[position]

        // sets the image to the imageview from our itemHolder class




        // sets the text to the textview from our itemHolder class

        holder.prodName.text = prodData.name

        val context = holder.itemView.context

        Glide.with(context)
            .load(prodData.images[0].src)
            .into(holder.prodImage)

        holder.itemView.setOnClickListener {
            val prodImg = Images(prodData.images)
            val sizes = SizeAttribute(prodData.attributes)

            val action = HomeFragmentDirections.actionHomeFragmentToDetailInfoFragment(id = prodData.id.toLong())
            holder.itemView.findNavController().navigate(action)
        }

//
//        holder.prodImage.setImageResource(R.drawable.boy)

        holder.rating.rating = prodData.average_rating.toFloat()

        holder.regPrice.text = "₹${prodData.price}"

        holder.salesPrice.visibility = View.GONE

        holder.ratingCount.text = "(${prodData.rating_count})"




    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return popProdList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val prodImage: ImageView = itemView.findViewById(R.id.wishProdImg)
        val prodName: TextView = itemView.findViewById(R.id.wishProdTitle)
        val rating: RatingBar = itemView.findViewById(R.id.wishProdRating)
        val ratingCount: TextView = itemView.findViewById(R.id.wishRatingCount)
        var regPrice: TextView = itemView.findViewById(R.id.wishProdRegPrice)
        val salesPrice: TextView = itemView.findViewById(R.id.wishProdSalePrice)
        val reviewCount: TextView = itemView.findViewById(R.id.wishReviewCount)
    }
}