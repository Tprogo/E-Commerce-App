package com.example.e_comapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.e_comapplication.models.room_models.CartListData


@Dao
interface CartListDao {

    @Query("SELECT * FROM CartList")
    suspend fun getCartlist(): List<CartListData>


    @Delete
    suspend fun deleteData(item: CartListData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItemList(data: CartListData)

    @Insert
    suspend fun updatelist(data: CartListData)
}