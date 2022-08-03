package br.com.wellintonvieira.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.com.wellintonvieira.businesscard.AppContext
import br.com.wellintonvieira.businesscard.R
import br.com.wellintonvieira.businesscard.data.BusinessCard
import br.com.wellintonvieira.businesscard.databinding.ActivityBusinessCardBinding

class BusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityBusinessCardBinding.inflate(layoutInflater) }
    private val viewModel: BusinessCardViewModel by viewModels {
        BusinessCardViewModelFactory((application as AppContext).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureButtonsListener()
    }

    private fun configureButtonsListener() {
        binding.imageViewClose.setOnClickListener {
            finish()
        }

        binding.buttonSave.setOnClickListener {
            val businessCard = getNewBusinessCard()
            viewModel.insert(businessCard)
            Toast.makeText(this, R.string.text_success, Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun getNewBusinessCard() : BusinessCard {
        val name = binding.textInputName.editText?.text.toString().trim()
        val phone = binding.textInputPhone.editText?.text.toString().trim()
        val email = binding.textInputEmail.editText?.text.toString().trim()
        val company = binding.textInputCompany.editText?.text.toString().trim()
        val color = binding.textInputColor.editText?.text.toString().trim()
        return BusinessCard(
            name = name,
            phone = phone,
            email = email,
            company = company,
            color = color
        )
    }
}