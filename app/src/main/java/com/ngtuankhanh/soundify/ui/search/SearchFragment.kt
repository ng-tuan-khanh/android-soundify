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
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ngtuankhanh.soundify.databinding.FragmentSearchBinding
import com.ngtuankhanh.soundify.ui.activities.HomeActivity
import com.ngtuankhanh.soundify.ui.adapters.SearchItemsAdapter
import com.ngtuankhanh.soundify.ui.models.SearchItem
import kotlinx.coroutines.launch

interface OnSearchItemClickListener {
    fun onTrackClicked(track: SearchItem)
    fun onArtistClicked(artist: SearchItem)
    fun onPlaylistClicked(playlist: SearchItem)
}

class SearchFragment : Fragment(), OnSearchItemClickListener {
    private lateinit var binding: FragmentSearchBinding

    override fun onTrackClicked(track: SearchItem) {
        // Logic khi một track được nhấp vào
    }

    override fun onArtistClicked(artist: SearchItem) {
        val action = SearchFragmentDirections.actionSearchFragmentToArtistProfileFragment(artist.id)
        view?.findNavController()?.navigate(action)
    }

    override fun onPlaylistClicked(playlist: SearchItem) {
        val action = SearchFragmentDirections.actionSearchFragmentToPlaylistDetailFragment(playlist.id)
        view?.findNavController()?.navigate(action)
    }

    private val searchItemsAdapter by lazy { SearchItemsAdapter(this) }

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

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty()) searchViewModel.searchForItems(s.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        })

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.searchResults.collect { results ->
                searchItemsAdapter.submitList(results)
            }
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.searchResultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchItemsAdapter
        }
    }
}
