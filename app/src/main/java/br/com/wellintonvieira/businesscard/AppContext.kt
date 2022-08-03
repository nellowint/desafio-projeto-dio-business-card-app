package br.com.wellintonvieira.businesscard

import android.app.Application
import br.com.wellintonvieira.businesscard.data.BusinessCardDatabase
import br.com.wellintonvieira.businesscard.data.BusinessCardRepository

class AppContext: Application() {

    val database: BusinessCardDatabase by lazy {
        BusinessCardDatabase.getInstance(this)
    }
    val repository: BusinessCardRepository by lazy {
        BusinessCardRepository(database.businessDAO())
    }
}