package com.example.e_comapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_comapplication.R
import com.example.e_comapplication.models.CategoriesModel
import com.example.e_comapplication.models.product_model.Attribute

class AttributeAdapter (private val attributeList: List<String> ) : RecyclerView.Adapter<AttributeAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.attribute_view_holder, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val attriData = attributeList[position]




        // sets the text to the textview from our itemHolder class

        holder.attText.text = attriData


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return attributeList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val attText: TextView = itemView.findViewById(R.id.attributeText)
    }
}