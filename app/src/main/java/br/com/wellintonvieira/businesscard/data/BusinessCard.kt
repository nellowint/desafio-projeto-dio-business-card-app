package br.com.wellintonvieira.businesscard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "business_card")
data class BusinessCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phone: String,
    val email: String,
    val company: String,
    val color: String
)
