package com.example.e_comapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.e_comapplication.models.room_models.CartListData
import com.example.e_comapplication.models.room_models.WishListData


@Database (version = 4, entities = [WishListData::class, CartListData::class])
abstract class ProductRoomDatabase: RoomDatabase() {
    abstract fun getWishlistDao(): WishListDao
    abstract fun getCartListDao(): CartListDao


//    object Migrations {
//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Since no structural changes are needed, this migration can be empty
//                // or you can perform any necessary operations related to data migration here.
//                // For version update only, it might be just empty.
//            }
//        }
//    }


}