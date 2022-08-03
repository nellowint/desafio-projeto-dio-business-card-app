package br.com.wellintonvieira.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusinessCard::class], version = 1, exportSchema = false)
abstract class BusinessCardDatabase: RoomDatabase() {
    abstract fun businessDAO(): BusinessCardDAO

    companion object {
        @Volatile
        private var INSTANCE: BusinessCardDatabase? = null
        fun getInstance(context: Context): BusinessCardDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context,
                    BusinessCardDatabase::class.java,
                    "business_card.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}