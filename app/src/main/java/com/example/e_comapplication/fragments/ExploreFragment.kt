package com.example.e_comapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.whenCreated
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_comapplication.R
import com.example.e_comapplication.adapters.ExploreProdAdapter
import com.example.e_comapplication.databinding.FragmentExploreBinding
import com.example.e_comapplication.utils.NetworkResult
import com.example.e_comapplication.viewmodels.DataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ExploreFragment : Fragment() {
    
    private var _binding: FragmentExploreBinding? = null
    val binding get() = _binding

    val dataVewModel by activityViewModels<DataViewModel>()

    private val args by navArgs<ExploreFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentExploreBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val keyword = args.keyword
        val defKey = "women"


        when(keyword){
            defKey ->{
                CoroutineScope(Dispatchers.Main).launch{
                    dataVewModel.getAllProductData()
                }
            }
            else ->{
                CoroutineScope(Dispatchers.Main).launch{
                    dataVewModel.getSearchData(keyword!!)
                }
            }
        }


        dataVewModel.searchLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Error -> {
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()

                }
                is NetworkResult.Loading -> {
                    binding?.progressBarExplore?.visibility = View.VISIBLE

                }
                is NetworkResult.Success -> {
                    binding?.progressBarExplore?.visibility = View.GONE
                    val adapter = ExploreProdAdapter(it.data!!)
                    binding?.exploreProdRecyclerview?.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding?.exploreProdRecyclerview?.adapter = adapter
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}