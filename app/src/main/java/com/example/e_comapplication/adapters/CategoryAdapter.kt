package com.example.e_comapplication.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.e_comapplication.models.CategoriesModel
import com.example.e_comapplication.R
import com.example.e_comapplication.fragments.ExploreFragment
import com.example.e_comapplication.fragments.HomeFragmentDirections

class CategoryAdapter (private val categoryList: List<CategoriesModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categoryviewholder, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val categoryData = categoryList[position]

        // sets the image to the imageview from our itemHolder class
        holder.catImage.setImageResource(categoryData.categoryImage)

        // sets the text to the textview from our itemHolder class

        holder.catText.text = categoryData.categoryName

//        val bundle = Bundle()
//        bundle.putString("key", categoryData.categoryName)
//
//        val fragExplore = ExploreFragment()
//        fragExplore.arguments = bundle

        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToExploreFragment(categoryData.categoryName)
            holder.itemView.findNavController().navigate(action)
        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return categoryList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val catImage: ImageView = itemView.findViewById(R.id.categoryImg)
        val catText: TextView = itemView.findViewById(R.id.categoryTitle)
    }
}