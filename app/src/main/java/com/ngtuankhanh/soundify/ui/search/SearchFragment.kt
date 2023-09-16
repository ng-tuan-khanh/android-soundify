package com.ngtuankhanh.soundify.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngtuankhanh.soundify.databinding.FragmentSearchBinding
import com.ngtuankhanh.soundify.ui.activities.HomeActivity
import com.ngtuankhanh.soundify.ui.adapters.SearchAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    private val searchAdapter by lazy { SearchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        val searchViewModelFactory = SearchViewModel.Factory(requireActivity() as HomeActivity)
        val searchViewModel =
            ViewModelProvider(this, searchViewModelFactory).get(SearchViewModel::class.java)

        setupRecyclerView()

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) searchViewModel.searchForItems(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.searchResults.collect { results ->
                searchAdapter.submitList(results) // CHANGED: Trực tiếp truyền results vào mà không cần qua thuộc tính items
            }
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.searchResultsRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }
}
