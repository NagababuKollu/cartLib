package com.thenorthface.tnf.cartlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayQuoteActivity : AppCompatActivity() {
    private val  viewModel : SampleViewModel by viewModels()

    private lateinit var tvQuote : TextView
    private lateinit var progressBar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_quote)

        tvQuote = findViewById(R.id.tvQuote)
        progressBar = findViewById(R.id.progressBar)

        viewModel.getQuotesViewModel()

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { value: SampleViewModel.QuotesEvent ->
                when(value){
                    is SampleViewModel.QuotesEvent.Success ->{

                        progressBar.visibility = View.GONE
                        tvQuote.setText(value.resultText)
                    }
                    is SampleViewModel.QuotesEvent.Failure -> {
                        progressBar.visibility = View.GONE
                        tvQuote.setText(value.errorText)
                    }
                    is SampleViewModel.QuotesEvent.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }
    }
}