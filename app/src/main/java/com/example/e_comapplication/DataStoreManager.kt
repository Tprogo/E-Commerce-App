package com.example.e_comapplication

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreManager @Inject constructor (@ApplicationContext val context: Context) {



    companion object{
        val TOKEN = stringPreferencesKey("TOKEN")
        val USERID = intPreferencesKey("ID")
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "DataStore")
    }



    suspend fun saveToken(token: String) {

        context.dataStore.edit {
            it[TOKEN] = token
        }

    }

    suspend fun saveUserId(userId: Int){
        context.dataStore.edit {
            it[USERID] = userId
        }
    }


    suspend fun getToken() = context.dataStore.data.catch { exception ->

        // dataStore.data throws an IOException when an error is encountered when reading data
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }

    }.map {
        it[TOKEN]?: null

    }

    suspend fun getUserId() = context.dataStore.data.catch { exception ->

        // dataStore.data throws an IOException when an error is encountered when reading data
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }

    }.map {
        it[USERID]?: null

    }



}