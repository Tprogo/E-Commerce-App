package com.example.e_comapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_comapplication.R
import com.example.e_comapplication.models.room_models.CartListData
import com.example.e_comapplication.models.room_models.WishListData

class CartAdapter (private val cartList: List<CartListData>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_view_holder, parent, false)

        return ViewHolder(view)
    }

    interface cartItemPosition{
        fun deleteProduct(product: CartListData)
    }

    private var itemPositionListener: cartItemPosition? = null

    fun setItemPositionListener(listener: cartItemPosition) {
        itemPositionListener = listener
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val prodData = cartList[position]





        // sets the text to the textview from our itemHolder class

        holder.prodName.text = prodData.name

        val context = holder.itemView.context

        // sets the image to the imageview from our itemHolder class

        Glide.with(context)
            .load(prodData.image)
            .into(holder.prodImage)

        holder.prodPriceForOne.text = "₹${prodData.price}"


        val cartSingleProdPrice = prodData.price.toInt()

        holder.multippleProdPrice.text = "₹${prodData.price}"







        var cartSingleCount = 1

        fun setDataForPrice(){
            val cartSingleCountText = cartSingleCount.toString()

            holder.cartSingleProdCount.text = cartSingleCountText

            val multipleProdPriceForOne = cartSingleProdPrice * cartSingleCount

            holder.multippleProdPrice.text = "₹$multipleProdPriceForOne"
        }

        holder.cartPlus.setOnClickListener {


                ++cartSingleCount
                setDataForPrice()


        }

        holder.cartMinus.setOnClickListener {
            if (cartSingleCount >= 2){
                --cartSingleCount
                setDataForPrice()
            } else {

                itemPositionListener?.deleteProduct(prodData)

                holder.itemView.findNavController().navigate(R.id.cartFragment)

            }
        }


    }



    // return the number of the items in the list
    override fun getItemCount(): Int {
        return cartList.size ?: 0
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val prodImage: ImageView = itemView.findViewById(R.id.cartImage)
        val prodName: TextView = itemView.findViewById(R.id.cartTitle)
        var prodPriceForOne: TextView = itemView.findViewById(R.id.cartPriceEach)
        val multippleProdPrice: TextView = itemView.findViewById(R.id.cartTotalForEach)
        val cartPlus: TextView = itemView.findViewById(R.id.cartPlus)
        val cartMinus: TextView = itemView.findViewById(R.id.cartMinus)
        val cartSingleProdCount: TextView = itemView.findViewById(R.id.cartSingleProdCount)
    }
}