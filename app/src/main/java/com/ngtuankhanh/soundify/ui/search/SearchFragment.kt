package com.ngtuankhanh.soundify.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ngtuankhanh.soundify.R
import com.ngtuankhanh.soundify.databinding.FragmentSearchBinding
import com.ngtuankhanh.soundify.ui.home.HomeViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        val searchViewModelFactory = SearchViewModel.Factory()
        val searchViewModel =
            ViewModelProvider(this, searchViewModelFactory).get(SearchViewModel::class.java)

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Code to handle before text is changed if needed
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // This is called when the text is changing
                // s is the updated text

                searchViewModel.searchForItems(s.toString())
            }

            override fun afterTextChanged(s: Editable) {
                // Code to handle after text has changed if needed
            }
        })

        return binding.root
    }
}