package com.example.e_comapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_comapplication.R
import com.example.e_comapplication.adapters.ReviewAdapter
import com.example.e_comapplication.databinding.FragmentReviewBinding
import com.example.e_comapplication.utils.NetworkResult
import com.example.e_comapplication.viewmodels.DataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding?= null
    private val binding get() = _binding

    private val sharedViewModel by activityViewModels<DataViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReviewBinding.inflate(layoutInflater, container, false)










        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.reviewLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> {
                    val reviewAdapter = it.data?.let { it1 -> ReviewAdapter(it1) }
                    binding?.reviewRecyclerview?.layoutManager = LinearLayoutManager(requireContext())
                    binding?.reviewRecyclerview?.adapter = reviewAdapter
                }
            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}