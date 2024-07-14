package com.example.e_comapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.e_comapplication.models.room_models.WishListData

@Dao
interface WishListDao {

    @Query("SELECT * FROM Wishlist")
    suspend fun getWishlist(): List<WishListData>


   @Delete
    suspend fun deleteData(item: WishListData)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItemList(data: WishListData)

    @Insert
    suspend fun updatelist(data: WishListData)


}