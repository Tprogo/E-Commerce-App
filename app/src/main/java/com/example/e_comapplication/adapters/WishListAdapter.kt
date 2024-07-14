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
import com.example.e_comapplication.fragments.WishListFragmentDirections
import com.example.e_comapplication.models.product_model.Images
import com.example.e_comapplication.models.product_model.SizeAttribute
import com.example.e_comapplication.models.room_models.WishListData

class WishListAdapter (private val wishItemList: List<WishListData>) : RecyclerView.Adapter<WishListAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wishlist_viewholder, parent, false)

        return ViewHolder(view)
    }

    interface itemPosition{
        fun deleteProduct(product: WishListData)
    }

    private var itemPositionListener: itemPosition? = null

    fun setItemPositionListener(listener: itemPosition) {
        itemPositionListener = listener
    }



    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val prodData = wishItemList[position]

        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class

        holder.prodName.text = prodData.wishName

        val context = holder.itemView.context

        Glide.with(context)
            .load(prodData.wishImage)
            .into(holder.prodImage)

        holder.itemView.setOnClickListener {
//            val prodImg = Images(prodData.)
//            val sizes = SizeAttribute(prodData.attributes)
//
            val action = WishListFragmentDirections.actionWishListFragmentToDetailInfoFragment(id = prodData.wishProductId)
            holder.itemView.findNavController().navigate(action)
        }

//
//        holder.prodImage.setImageResource(R.drawable.boy)

        holder.rating.rating = prodData.wishRating.toFloat()

        holder.regPrice.text = "₹${prodData.wishPrice}"

//        holder.salesPrice.text = "₹${prodData.}"

        holder.ratingCount.text = "(${prodData.wishRating})"

        holder.removeProd.setOnClickListener {
            itemPositionListener?.deleteProduct(prodData)
            holder.itemView.findNavController().navigate(R.id.wishListFragment)
        }

        holder.regPrice.paintFlags = holder.regPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return wishItemList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val prodImage: ImageView = itemView.findViewById(R.id.wishProdImg)
        val prodName: TextView = itemView.findViewById(R.id.wishProdTitle)
        val rating: RatingBar = itemView.findViewById(R.id.wishProdRating)
        val ratingCount: TextView = itemView.findViewById(R.id.wishRatingCount)
        var regPrice: TextView = itemView.findViewById(R.id.wishProdRegPrice)
        val removeProd: ImageView = itemView.findViewById(R.id.removeProduct)
    }
}