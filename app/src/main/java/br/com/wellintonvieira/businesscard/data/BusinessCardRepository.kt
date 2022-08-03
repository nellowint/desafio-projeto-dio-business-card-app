package br.com.wellintonvieira.businesscard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val businessCardDAO: BusinessCardDAO) {

    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            businessCardDAO.insert(businessCard)
        }
    }

    fun loadBusinessCards() = businessCardDAO.loadBusinessCard()
}