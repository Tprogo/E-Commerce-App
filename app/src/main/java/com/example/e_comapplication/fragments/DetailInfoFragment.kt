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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.e_comapplication.DecriptionInterface
import com.example.e_comapplication.R
import com.example.e_comapplication.adapters.AttributeAdapter
import com.example.e_comapplication.adapters.ImgViewPgAdapter
import com.example.e_comapplication.adapters.TabPagerAdapter
import com.example.e_comapplication.databinding.FragmentDetailInfoBinding
import com.example.e_comapplication.models.room_models.CartListData
import com.example.e_comapplication.models.room_models.WishListData
import com.example.e_comapplication.utils.NetworkResult
import com.example.e_comapplication.viewmodels.DataViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailInfoFragment : Fragment() {

    private var _binding: FragmentDetailInfoBinding? = null
    private val binding get() = _binding

    val args by navArgs<DetailInfoFragmentArgs>()
    private val viewModel by activityViewModels<DataViewModel>()

    private lateinit var dataInterface: DecriptionInterface



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDetailInfoBinding.inflate(layoutInflater, container, false)


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getReviewData(args.id)
            viewModel.getDetProdData(id = args.id)
        }


        // add description data


        viewModel.detProdLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> {

                    val imgViewPgAdapter = ImgViewPgAdapter(it.data!!.images)
                    binding?.imgViewPager?.adapter = imgViewPgAdapter

                    val id = it.data.id.toLong()
                    val title = it.data.name
                    val thumbnail = it.data.images[0].src
                    val price = it.data.price
                    val rating = it.data.average_rating.toDoubleOrNull()

                    binding?.prodDetaiTitle?.text = title ?: ""
                    binding?.prodDetaiPrice?.text = "₹${price}" ?: ""
                    binding?.ratingBar?.rating = rating!!.toFloat() ?: 0f

                    if (rating <= 1) {
                        binding?.detRatingCount?.text = "${rating} rating" ?: ""
                    } else {
                        binding?.detRatingCount?.text = "${rating} ratings" ?: ""
                    }

                    val optionsList: List<String> = it.data.attributes.flatMap { it.options }

                    val attributeAdapter = AttributeAdapter(optionsList)
                    binding?.sizeRecyclerview?.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding?.sizeRecyclerview?.adapter = attributeAdapter

                    CoroutineScope(Dispatchers.Main).launch {
                        viewModel.addDescription(it.data.description)
                    }



                    // add to cart

                    binding?.detailAddCartBtn?.setOnClickListener {
                        CoroutineScope(Dispatchers.IO).launch {
                            val product = CartListData(
                                id,
                                title,
                                thumbnail,
                                price,
                                rating.toString()
                            )
                            viewModel.insertCartlistData(product)
                        }

                        Toast.makeText(requireContext(), "Added to Cart", Toast.LENGTH_SHORT).show()

                        val action = DetailInfoFragmentDirections.actionDetailInfoFragmentToCartFragment()

                        findNavController().navigate(action)


                    }


                    // add to wishlist

                    binding?.wishListCheckBox?.setOnCheckedChangeListener { buttonView, isChecked ->

                        if (isChecked) {

                            val data = WishListData(
                                id,
                                title,
                                thumbnail,
                                price,
                                rating.toString()
                            )
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.insertWishlistData(data)
                            }
                        } else {

                        }
                    }

                }
            }
        })










        // set viewPager










        // set reviewViewPager
        val navController = findNavController()
        val tabPagerAdapter = TabPagerAdapter(this, navController)

        binding?.detTabLayout?.addTab(binding?.detTabLayout?.newTab()!!.setText("Description"))
        binding?.detTabLayout?.addTab(binding?.detTabLayout?.newTab()!!.setText("Reviews"))
        binding?.detTabLayout?.addTab(
            binding?.detTabLayout?.newTab()!!.setText("Return Policy")
        )

        binding?.reviewViewPager?.adapter = tabPagerAdapter

        binding?.detTabLayout?.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding?.reviewViewPager?.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding?.reviewViewPager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding?.detTabLayout?.selectTab(binding?.detTabLayout?.getTabAt(position))
            }
        })








//        binding?.wishListCheckBox?.setOnClickListener {
//            findNavController().navigate(R.id.wishListFragment)
//        }









    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}