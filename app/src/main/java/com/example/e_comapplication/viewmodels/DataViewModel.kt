package com.example.e_comapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.e_comapplication.models.auth_model.LoginResponse
import com.example.e_comapplication.models.auth_model.LoginUser
import com.example.e_comapplication.models.auth_model.RegisterResponse
import com.example.e_comapplication.models.auth_model.RegisterUser
import com.example.e_comapplication.models.product_model.ProductModel
import com.example.e_comapplication.models.review_model.ReviewModelItem
import com.example.e_comapplication.models.room_models.CartListData
import com.example.e_comapplication.models.room_models.WishListData
import com.example.e_comapplication.repositories.DataRespository
import com.example.e_comapplication.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DataViewModel @Inject constructor(private val prodRespository: DataRespository): ViewModel() {

    val popProdLiveData: LiveData<NetworkResult<List<ProductModel>>>
        get() = prodRespository.popProdLiveData

    val topRatedLiveData: LiveData<NetworkResult<List<ProductModel>>>
        get() = prodRespository.topRatedLiveData

    val descLiveData: LiveData<String>
        get() = prodRespository.desciptionLiveData


    val detProdLiveData: LiveData<NetworkResult<ProductModel>>
        get() = prodRespository.getDetProdLiveData

    val  reviewLiveData: LiveData<NetworkResult<List<ReviewModelItem>>>
        get() = prodRespository.prodReviewLiveData

    val searchLiveData: LiveData<NetworkResult<List<ProductModel>>>
        get() = prodRespository.searchLiveData

    val registerLiveData: LiveData<NetworkResult<RegisterResponse>>
        get() = prodRespository.registerLiveData

    val loginLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = prodRespository.loginLiveData

    suspend fun register(user: RegisterUser){
        prodRespository.register(user)
    }

    suspend fun login(user: LoginUser){
        prodRespository.login(user)
    }



    suspend fun getSearchData(keyword: String){
        prodRespository.getSearchData(keyword)
    }

    suspend fun getAllProductData(){
        prodRespository.getAllProductData()
    }



    suspend fun getPopularProd(){
        prodRespository.getPopularProduct()
    }

    suspend fun getTopRatedProd(){
        prodRespository.getTopRatedProduct()
    }

    suspend fun getDetProdData(id: Long){
        prodRespository.getDetProdData(id)
    }

    suspend fun getReviewData(id: Long){
        prodRespository.getReviewData(id)
    }

    suspend fun addDescription(desc: String){
        prodRespository.addDescription(desc)
    }


    val wishListLiveData: LiveData<NetworkResult<List<WishListData>>>
        get() = prodRespository.wishListLiveData


    val insertProdLiveData: LiveData<String>
        get() = prodRespository.insertProdLiveData

    val updateProdLiveData: LiveData<String>
        get() = prodRespository.updateProdLiveData

    val remProdLiveData: LiveData<String>
        get() = prodRespository.remProdLiveData

    val cartListLiveData: LiveData<List<CartListData>>
        get() = prodRespository.cartListLiveData


    suspend fun getWishList(){
        prodRespository.getWishList()
    }

    suspend fun updateWishlistData(prodData: WishListData){
        prodRespository.updateProd(prodData)
    }

    suspend fun insertWishlistData(prodData: WishListData){
        prodRespository.insertProd(prodData)
    }

    suspend fun deleteWishItem(prodData: WishListData){
        prodRespository.deleteProdInRoom(prodData)
    }

    suspend fun getCartList(){
       prodRespository.getCartList()
    }

    suspend fun insertCartlistData(prodData: CartListData){
        prodRespository.insertCartItem(prodData)
    }

    suspend fun updateCartlistData(prodData: CartListData){
        prodRespository.updateCartItem(prodData)
    }

    suspend fun deleteCartlistData(prodData: CartListData){
        prodRespository.deleteCartItem(prodData)
    }


}