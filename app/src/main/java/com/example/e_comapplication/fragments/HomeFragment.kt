package com.example.e_comapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_comapplication.models.CategoriesModel
import com.example.e_comapplication.R
import com.example.e_comapplication.adapters.CategoryAdapter
import com.example.e_comapplication.adapters.PopularProdAdapter
import com.example.e_comapplication.adapters.TopRatedProAdapter
import com.example.e_comapplication.databinding.FragmentHomeBinding
import com.example.e_comapplication.utils.NetworkResult
import com.example.e_comapplication.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var popProdAdapter: PopularProdAdapter
    private lateinit var topRatedAdapter: TopRatedProAdapter

    val dataViewModel by activityViewModels<DataViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)




        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // set slider
        // here SlideModel is provided by the slider library

        val imgList = ArrayList<SlideModel>()

        imgList.add(SlideModel(R.drawable.banner1))
        imgList.add(SlideModel(R.drawable.banner2))
        imgList.add(SlideModel(R.drawable.banner3))
        imgList.add(SlideModel(R.drawable.banner4))

        binding?.homeSlider?.setImageList(imgList, ScaleTypes.FIT)


        // set categories recyclerview
        binding?.categoryRecyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val categoryData = ArrayList<CategoriesModel>()
        categoryData.add(CategoriesModel(R.drawable.women, "Women"))
        categoryData.add(CategoriesModel(R.drawable.men, "Men"))
        categoryData.add(CategoriesModel(R.drawable.saree, "Saree"))
        categoryData.add(CategoriesModel(R.drawable.shirt, "Shirts"))
        categoryData.add(CategoriesModel(R.drawable.girl, "Girl"))
        categoryData.add(CategoriesModel(R.drawable.boy, "Boy"))
        categoryData.add(CategoriesModel(R.drawable.kurta, "Kurta"))
        categoryData.add(CategoriesModel(R.drawable.jeans, "Jeans"))

        val catAdapter = CategoryAdapter(categoryData)

        binding?.categoryRecyclerView?.adapter = catAdapter


        // set cart item numbers

        CoroutineScope(Dispatchers.Main).launch {
            dataViewModel.getCartList()
        }



       dataViewModel.cartListLiveData.observe(viewLifecycleOwner, Observer {
           binding?.cartItemsNumberText?.text = it.size.toString()
       })


        binding?.cartIcon?.setOnClickListener {

            val action = HomeFragmentDirections.actionHomeFragmentToCartFragment()

            findNavController().navigate(action)
        }



        CoroutineScope(Dispatchers.Main).launch{
            dataViewModel.getPopularProd()
        }

//        popProdAdapter = PopularProdAdapter(emptyList())

//        popProdAdapter = PopularProdAdapter()



        dataViewModel.popProdLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Error -> {
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding?.popularProdProgressBar?.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {

                   popProdAdapter = PopularProdAdapter(it.data!!)
                    binding?.popularProductsRecyclerview?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                    binding?.popularProductsRecyclerview?.adapter = popProdAdapter

                    binding?.popularProdProgressBar?.visibility = View.GONE
                }
            }
        })



        
        
        CoroutineScope(Dispatchers.IO).launch { 
            dataViewModel.getTopRatedProd()
        }
        
        dataViewModel.topRatedLiveData.observe(viewLifecycleOwner, Observer { 
            when(it){
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding?.topRProgressBar?.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    topRatedAdapter = TopRatedProAdapter(it.data!!)
                    binding?.topRatedRecyclerview?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding?.topRatedRecyclerview?.adapter = topRatedAdapter

                    binding?.topRProgressBar?.visibility = View.GONE
                }
            }
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}