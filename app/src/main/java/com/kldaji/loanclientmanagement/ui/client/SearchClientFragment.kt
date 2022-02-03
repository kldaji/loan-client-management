package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.view.View
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentSearchClientBinding
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord
import com.kldaji.loanclientmanagement.ui.client.adapter.ClientsAdapter
import com.kldaji.loanclientmanagement.ui.client.adapter.RecentSearchWordsAdapter
import com.kldaji.loanclientmanagement.ui.common.BaseFragment

class SearchClientFragment :
    BaseFragment<FragmentSearchClientBinding>(R.layout.fragment_search_client) {
    private val recentSearchWordsAdapter by lazy { RecentSearchWordsAdapter() }
    private val searchResultClientsAdapter by lazy { ClientsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecentSearchWordsAdapter()
        setSearchResultClientsAdapter()

        // dummy data
        val wordList = listOf(RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"),
            RecentSearchWord(1, "123"))
        recentSearchWordsAdapter.submitList(wordList)
    }

    private fun setRecentSearchWordsAdapter() {
        binding.rvSearchClientRecentSearchWords.adapter = recentSearchWordsAdapter
    }

    private fun setSearchResultClientsAdapter() {
        binding.rvSearchClientSearchResultClients.adapter = searchResultClientsAdapter
    }
}
