package br.com.wellintonvieira.businesscard.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface BusinessCardDAO {

    @Insert(onConflict = REPLACE)
    suspend fun insert(businessCard: BusinessCard)

    @Query("SELECT * FROM business_card")
    fun loadBusinessCard(): LiveData<List<BusinessCard>>
}