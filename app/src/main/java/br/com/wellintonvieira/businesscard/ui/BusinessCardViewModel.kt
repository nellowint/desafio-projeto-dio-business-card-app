package br.com.wellintonvieira.businesscard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.wellintonvieira.businesscard.data.BusinessCard
import br.com.wellintonvieira.businesscard.data.BusinessCardRepository

class BusinessCardViewModel(private val repository: BusinessCardRepository): ViewModel() {

    fun insert(businessCard: BusinessCard) { repository.insert(businessCard) }

    fun loadBusinessCards() = repository.loadBusinessCards()
}

class BusinessCardViewModelFactory(private val repository: BusinessCardRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusinessCardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusinessCardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}