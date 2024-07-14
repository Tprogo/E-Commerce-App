package com.example.e_comapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_comapplication.adapters.CartAdapter
import com.example.e_comapplication.databinding.FragmentCartBinding
import com.example.e_comapplication.models.room_models.CartListData
import com.example.e_comapplication.viewmodels.DataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CartFragment : Fragment(), CartAdapter.cartItemPosition {

    private var _binding: FragmentCartBinding? = null

    private val binding get() = _binding

    private val dataViewModel by activityViewModels<DataViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.cartReccyclerview?.layoutManager = LinearLayoutManager(requireContext())

        CoroutineScope(Dispatchers.Main).launch {
            dataViewModel.getCartList()
        }

        CoroutineScope(Dispatchers.Main).launch {


            dataViewModel.cartListLiveData.observe(viewLifecycleOwner, Observer {


                binding?.cartProgressBar?.visibility = View.GONE
                val cartAdapter = CartAdapter(it!!)
                if (it.isEmpty()){
                    binding?.noDataImg?.visibility = View.VISIBLE
                    binding?.cartConstraintLayout?.visibility = View.GONE
                }else{
                    binding?.noDataImg?.visibility = View.GONE
                }

                cartAdapter.setItemPositionListener(this@CartFragment)

                binding?.cartReccyclerview?.adapter = cartAdapter
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun deleteProduct(product: CartListData) {
       CoroutineScope(Dispatchers.IO).launch {
           dataViewModel.deleteCartlistData(product)
       }
    }


}




