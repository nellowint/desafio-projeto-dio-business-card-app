package br.com.wellintonvieira.businesscard.ui

import android.graphics.Color
import android.view.View
import br.com.wellintonvieira.businesscard.data.BusinessCard
import br.com.wellintonvieira.businesscard.databinding.CardViewAdapterBinding

class BusinessCardAdapter(
    items: List<BusinessCard>,
    val onClickListener: (view: View) -> Unit
) : BaseAdapter<BusinessCard>(items) {

    override fun bind(binding: CardViewAdapterBinding, item: BusinessCard) {
        binding.textViewName.text = item.name
        binding.textViewPhone.text = item.phone
        binding.textViewEmail.text = item.email
        binding.textViewCompany.text = item.company
        binding.cardView.setCardBackgroundColor(Color.parseColor(item.color))
        binding.cardView.setOnClickListener {
            onClickListener(it)
        }
    }
}