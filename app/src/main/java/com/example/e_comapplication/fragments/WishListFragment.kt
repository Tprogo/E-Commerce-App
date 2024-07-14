package com.example.e_comapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_comapplication.R
import com.example.e_comapplication.adapters.WishListAdapter
import com.example.e_comapplication.databinding.FragmentReviewBinding
import com.example.e_comapplication.databinding.FragmentWishListBinding
import com.example.e_comapplication.models.room_models.WishListData
import com.example.e_comapplication.utils.NetworkResult
import com.example.e_comapplication.viewmodels.DataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class WishListFragment : Fragment(), WishListAdapter.itemPosition {

    private var _binding: FragmentWishListBinding?= null
    private val binding get() = _binding

    private val sharedViewModel by activityViewModels<DataViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentWishListBinding.inflate(layoutInflater, container, false)



        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            sharedViewModel.getWishList()
        }




        CoroutineScope(Dispatchers.Main).launch {
            sharedViewModel.wishListLiveData.observe(viewLifecycleOwner, Observer {
                when(it){
                    is NetworkResult.Error -> {

                    }
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        val wishListAdapter = WishListAdapter(it.data!!)
                        if(it.data.isEmpty()){
                            binding?.noDataImgWishlist?.visibility = View.VISIBLE
                        }else{
                            binding?.noDataImgWishlist?.visibility = View.GONE
                        }
                        wishListAdapter.setItemPositionListener(this@WishListFragment)
                        binding?.wishlistRecyclervie?.layoutManager = GridLayoutManager(requireContext(), 2)
                        binding?.wishlistRecyclervie?.adapter = wishListAdapter
                    }
                }
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun deleteProduct(product: WishListData) {
       CoroutineScope(Dispatchers.IO).launch {
           sharedViewModel.deleteWishItem(product)
       }
    }


}