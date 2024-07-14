package com.example.e_comapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.e_comapplication.R
import com.example.e_comapplication.DecriptionInterface

import com.example.e_comapplication.databinding.FragmentDescriptionBinding
import com.example.e_comapplication.utils.NetworkResult
import com.example.e_comapplication.viewmodels.DataViewModel


class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null

    private val binding get() = _binding


    private val viewModel by activityViewModels<DataViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)







        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding?.progressbar?.visibility = View.VISIBLE
        binding?.description?.visibility = View.GONE

        viewModel.descLiveData.observe(viewLifecycleOwner, Observer {

            binding?.progressbar?.visibility = View.GONE
            binding?.description?.visibility = View.VISIBLE
            binding?.description?.loadDataWithBaseURL(null,
                it, "text/html","utf-8",null)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }





}