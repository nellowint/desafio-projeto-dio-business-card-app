package br.com.wellintonvieira.businesscard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wellintonvieira.businesscard.databinding.CardViewAdapterBinding

abstract class BaseAdapter<T>(private var items: List<T>): RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardViewAdapterBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bind(holder.binding, items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(val binding: CardViewAdapterBinding): RecyclerView.ViewHolder(binding.root)

    abstract fun bind(binding: CardViewAdapterBinding, item: T)
}
