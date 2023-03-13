package com.thenorthface.tnf.cartlib

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.migration.OptionalInject

@OptionalInject
@AndroidEntryPoint
public class SampleFragment : Fragment() {

    companion object {
        fun newInstance() = SampleFragment()
    }

    private val viewModel: SampleViewModel by viewModels()
     private lateinit var textView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_sample, container, false)
        textView = view.findViewById(R.id.kanyeWestQuote)
        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getQuotesViewModel()

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { value: SampleViewModel.QuotesEvent ->
                when(value){
                    is SampleViewModel.QuotesEvent.Success ->{

                        progressBar.visibility =View.GONE
                        textView.setText(value.resultText)
                    }
                    is SampleViewModel.QuotesEvent.Failure -> {
                        progressBar.visibility =View.GONE
                        textView.setText(value.errorText)
                    }
                    is SampleViewModel.QuotesEvent.Loading -> {
                        progressBar.visibility =View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }

    }

}