package br.com.wellintonvieira.businesscard.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.wellintonvieira.businesscard.AppContext
import br.com.wellintonvieira.businesscard.databinding.ActivityMainBinding
import br.com.wellintonvieira.businesscard.util.BussinesCardUtil

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: BusinessCardViewModel by viewModels {
        BusinessCardViewModelFactory((application as AppContext).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadBusinessCard()
        configureRecyclerView()
        configureFloatingButton()
    }

    private fun configureFloatingButton() {
        binding.floatingButton.setOnClickListener {
            val intent = Intent(this@MainActivity, BusinessCardActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun onClickListener(view: View) {
        BussinesCardUtil.share(this@MainActivity, view)
    }

    private fun loadBusinessCard() {
        viewModel.loadBusinessCards().observe(this) { businessCards ->
            val adapter = BusinessCardAdapter(businessCards, this::onClickListener)
            binding.recyclerView.adapter = adapter
        }
    }
}