package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentSearchClientBinding
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord
import com.kldaji.loanclientmanagement.ui.client.adapter.ClientsAdapter
import com.kldaji.loanclientmanagement.ui.client.adapter.RecentSearchWordsAdapter
import com.kldaji.loanclientmanagement.ui.common.BaseFragment
import com.kldaji.loanclientmanagement.utils.hideKeyBoard
import com.kldaji.loanclientmanagement.utils.showKeyBoard

class SearchClientFragment :
    BaseFragment<FragmentSearchClientBinding>(R.layout.fragment_search_client) {
    private val recentSearchWordsAdapter by lazy { RecentSearchWordsAdapter() }
    private val searchResultClientsAdapter by lazy { ClientsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEditTextFocus()
        setCancelClickListener()
        setRootClickListener()
        setRecentSearchWordsAdapter()
        setSearchResultClientsAdapter()

        // dummy data
        val wordList = listOf(RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"))
        recentSearchWordsAdapter.submitList(wordList)
    }

    private fun setEditTextFocus() {
        with(binding.tieSearchClient) {
            requestFocus()
            requireContext().showKeyBoard(this)
        }
    }

    private fun setCancelClickListener() {
        binding.tvSearchClientCancel.setOnClickListener {
            this.findNavController().popBackStack()
        }
    }

    private fun setRootClickListener() {
        binding.root.setOnClickListener { view ->
            requireContext().hideKeyBoard(view)
            requireActivity().currentFocus?.clearFocus()
        }
    }

    private fun setRecentSearchWordsAdapter() {
        binding.rvSearchClientRecentSearchWords.adapter = recentSearchWordsAdapter
    }

    private fun setSearchResultClientsAdapter() {
        binding.rvSearchClientSearchResultClients.adapter = searchResultClientsAdapter
    }
}
