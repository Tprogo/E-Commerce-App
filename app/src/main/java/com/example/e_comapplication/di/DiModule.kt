package com.example.e_comapplication.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_comapplication.AuthApiService
import com.example.e_comapplication.CartListDao
import com.example.e_comapplication.ProductApiService
import com.example.e_comapplication.ProductRoomDatabase
import com.example.e_comapplication.WishListDao
import com.example.e_comapplication.constants.Constants
import com.example.e_comapplication.okhttp.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn (SingletonComponent::class)
class DiModule {

    @Provides
    @Singleton
    fun getRetrofitBuilder(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constants.apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())


    }

    @Provides
    @Named("auth")
    @Singleton
    fun getAuthRetrofitBuilder(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.authBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun getAuthApiSerice(@Named("auth") retrofit: Retrofit): AuthApiService{
        return retrofit.create(AuthApiService::class.java)
    }



    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }


    @Singleton
    @Provides
    fun getApiService(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): ProductApiService{
        return retrofitBuilder
            .client(okHttpClient)
            .build()
            .create(ProductApiService::class.java)
    }


    @Provides
    @Singleton

    fun getRoomBuider(@ApplicationContext context: Context): ProductRoomDatabase{

        return Room.databaseBuilder(
            context,
            ProductRoomDatabase::class.java,
            "product_room"
        ).fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun getRoomDao(prodDatabase: ProductRoomDatabase): WishListDao{
        return prodDatabase.getWishlistDao()
    }

    @Provides
    @Singleton
    fun getCartDao(prodDatabase: ProductRoomDatabase): CartListDao{
        return prodDatabase.getCartListDao()
    }

}