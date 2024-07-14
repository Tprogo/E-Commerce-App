package com.example.e_comapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.e_comapplication.fragments.DescriptionFragment
import com.example.e_comapplication.fragments.ReturnPolicyFragment
import com.example.e_comapplication.fragments.ReviewFragment

class TabPagerAdapter(fragment: Fragment, private val navController: NavController): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
       return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){

            0 -> DescriptionFragment()
            1  -> ReviewFragment()
            2  -> ReturnPolicyFragment()

            else -> throw IllegalArgumentException("Invalid position $position")

        }



    }

}