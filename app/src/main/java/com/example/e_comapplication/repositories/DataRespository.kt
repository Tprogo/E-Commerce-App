package com.example.e_comapplication.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_comapplication.AuthApiService
import com.example.e_comapplication.CartListDao
import com.example.e_comapplication.ProductApiService
import com.example.e_comapplication.WishListDao
import com.example.e_comapplication.models.auth_model.LoginResponse
import com.example.e_comapplication.models.auth_model.LoginUser
import com.example.e_comapplication.models.auth_model.RegisterResponse
import com.example.e_comapplication.models.auth_model.RegisterUser
import com.example.e_comapplication.models.product_model.ProductModel
import com.example.e_comapplication.models.review_model.ReviewModelItem
import com.example.e_comapplication.models.room_models.CartListData
import com.example.e_comapplication.models.room_models.WishListData
import com.example.e_comapplication.utils.NetworkResult
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class DataRespository @Inject constructor(private val productApiService: ProductApiService, private val authApiService: AuthApiService, private val wishDao: WishListDao, private val cartDao: CartListDao ) {


    private val _mutPopProductData = MutableLiveData<NetworkResult<List<ProductModel>>>()

    val popProdLiveData: LiveData<NetworkResult<List<ProductModel>>>
        get() = _mutPopProductData

    private val _mutTopRatedData = MutableLiveData<NetworkResult<List<ProductModel>>>()
    val topRatedLiveData: LiveData<NetworkResult<List<ProductModel>>>
        get() = _mutTopRatedData


    private val _mutDescription = MutableLiveData<String>()

    val desciptionLiveData: LiveData<String>
        get() = _mutDescription


    private val _getDetProductData = MutableLiveData<NetworkResult<ProductModel>>()

    val getDetProdLiveData: LiveData<NetworkResult<ProductModel>>
        get() = _getDetProductData


    private val _mutReviewData = MutableLiveData<NetworkResult<List<ReviewModelItem>>>()

    val prodReviewLiveData: LiveData<NetworkResult<List<ReviewModelItem>>>
        get() = _mutReviewData


    private val _mutSearchData = MutableLiveData<NetworkResult<List<ProductModel>>>()

    val searchLiveData: LiveData<NetworkResult<List<ProductModel>>>
        get() = _mutSearchData

    private val _mutRegLiveData = MutableLiveData<NetworkResult<RegisterResponse>>()

    val registerLiveData: LiveData<NetworkResult<RegisterResponse>>
        get() = _mutRegLiveData

    private val _mutLoginLiveData = MutableLiveData<NetworkResult<LoginResponse>>()

    val loginLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = _mutLoginLiveData


    suspend fun register(user: RegisterUser){
        _mutRegLiveData.postValue(NetworkResult.Loading())

        try {
            val response = authApiService.registerUser(user)

            if (response.isSuccessful){
                _mutRegLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else{
                val loginError = JSONObject(response.errorBody()!!.charStream().readText())
                _mutRegLiveData.postValue(NetworkResult.Error(loginError.getString("message")))
            }
        } catch (e: IOException){
            _mutRegLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun login(user: LoginUser){
       _mutLoginLiveData.postValue(NetworkResult.Loading())

        try {
            val response = authApiService.loginUser(user)

            if (response.isSuccessful){
                _mutLoginLiveData.postValue(NetworkResult.Success(response.body()!!))
            } else{
                val loginError = JSONObject(response.errorBody()!!.charStream().readText())
                _mutLoginLiveData.postValue(NetworkResult.Error(loginError.getString("message")))
            }
        } catch (e: IOException){
            _mutLoginLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getSearchData(key: String){
        _mutSearchData.postValue(NetworkResult.Loading())

        try {
            val response = productApiService.getSearchData(key)

            if (response.isSuccessful){
                _mutSearchData.postValue(NetworkResult.Success(response.body()!!))
            } else{
                val loginError = JSONObject(response.errorBody()!!.charStream().readText())
                _mutSearchData.postValue(NetworkResult.Error(loginError.getString("message")))
            }
        } catch (e: IOException){
            _mutSearchData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getAllProductData(){
        _mutSearchData.postValue(NetworkResult.Loading())

        try {
            val response = productApiService.getAllProducts()

            if (response.isSuccessful){
                _mutSearchData.postValue(NetworkResult.Success(response.body()!!))
            } else{
                val loginError = JSONObject(response.errorBody()!!.charStream().readText())
                _mutSearchData.postValue(NetworkResult.Error(loginError.getString("message")))
            }
        } catch (e: IOException){
            _mutSearchData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getPopularProduct(){
        _mutPopProductData.postValue(NetworkResult.Loading())

        try {
            val response = productApiService.getTopSellingPro()

            if (response.isSuccessful){
                _mutPopProductData.postValue(NetworkResult.Success(response.body()!!))
            } else{
                val loginError = JSONObject(response.errorBody()!!.charStream().readText())
                _mutPopProductData.postValue(NetworkResult.Error(loginError.getString("message")))
            }
        } catch (e: IOException){
            _mutPopProductData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getTopRatedProduct(){
        _mutTopRatedData.postValue(NetworkResult.Loading())

        try {
            val response = productApiService.getTopRatedPro()

            if (response.isSuccessful){
                _mutTopRatedData.postValue(NetworkResult.Success(response.body()!!))
            } else{
                val loginError = JSONObject(response.errorBody()!!.charStream().readText())
                _mutTopRatedData.postValue(NetworkResult.Error(loginError.getString("message")))
            }
        } catch (e: IOException){
            _mutTopRatedData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun addDescription(desc: String){
        _mutDescription.postValue(desc)
    }

    suspend fun getDetProdData(id: Long){
        _getDetProductData.postValue(NetworkResult.Loading())

        try {
            val response = productApiService.getProdDetails(id)

            if (response.isSuccessful){
                _getDetProductData.postValue(NetworkResult.Success(response.body()!!))
            } else{
                val loginError = JSONObject(response.errorBody()!!.charStream().readText())
                _getDetProductData.postValue(NetworkResult.Error(loginError.getString("message")))
            }
        } catch (e: IOException){
            _getDetProductData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


    suspend fun getReviewData(id: Long){
        _mutReviewData.postValue(NetworkResult.Loading())

        try {
            val response = productApiService.getProdReviews(id)

            if (response.isSuccessful){
               _mutReviewData.postValue(NetworkResult.Success(response.body()!!))
            } else{
                val loginError = JSONObject(response.errorBody()!!.charStream().readText())
                _mutReviewData.postValue(NetworkResult.Error(loginError.getString("message")))
            }
        } catch (e: IOException){
            _mutReviewData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


    // Room database


    private val _mutWishlist = MutableLiveData<NetworkResult<List<WishListData>>>()

    val wishListLiveData: LiveData<NetworkResult<List<WishListData>>>
        get() = _mutWishlist



    private val _mutInsertProd = MutableLiveData<String>()
     val insertProdLiveData: LiveData<String>
         get() = _mutInsertProd


    private val _mutUpdateProd = MutableLiveData<String>()
    val updateProdLiveData: LiveData<String>
        get() = _mutUpdateProd


    private val _mutRemove = MutableLiveData<String>()
    val remProdLiveData: LiveData<String>
        get() = _mutRemove

    private val _mutCartList = MutableLiveData<List<CartListData>>()
    val cartListLiveData: LiveData<List<CartListData>>
        get() = _mutCartList


    suspend fun getWishList() {

       _mutWishlist.postValue(NetworkResult.Loading())

        try {
            val response = wishDao.getWishlist()

                _mutWishlist.postValue(NetworkResult.Success(response))
            } catch (e: Exception){
            _mutWishlist.postValue(NetworkResult.Error("${e.message}"))
        }

    }

    suspend fun insertProd(product: WishListData){

        try {
            val response = wishDao.addItemList(product)
            _mutInsertProd.postValue("Product Added")
        } catch (e: Exception){
            _mutInsertProd.postValue("${e.message}")
        }



    }

    suspend fun updateProd(product: WishListData){

        try {
            val response = wishDao.updatelist(product)
            _mutUpdateProd.postValue("Successful")
        } catch (e: Exception){
            _mutUpdateProd.postValue("${e.message}")
        }



    }

    suspend fun deleteProdInRoom(item: WishListData){

        try{
            wishDao.deleteData(item)
        } catch (e: Exception){
            _mutRemove.postValue("${e.message}")
        }

    }

    suspend fun getCartList(){
        _mutCartList.postValue(cartDao.getCartlist())
    }


    suspend fun insertCartItem(item: CartListData){
        cartDao.addItemList(item)
    }

    suspend fun updateCartItem(item: CartListData){
        cartDao.updatelist(item)
    }

    suspend fun deleteCartItem(item: CartListData){
        cartDao.deleteData(item)
    }










}