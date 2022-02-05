package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentSearchClientBinding
import com.kldaji.loanclientmanagement.model.data.RecentSearchWord
import com.kldaji.loanclientmanagement.ui.client.adapter.ClientsAdapter
import com.kldaji.loanclientmanagement.ui.client.adapter.ItemClickListener
import com.kldaji.loanclientmanagement.ui.client.adapter.RecentSearchWordsAdapter
import com.kldaji.loanclientmanagement.ui.common.BaseFragment
import com.kldaji.loanclientmanagement.utils.hideKeyBoard
import com.kldaji.loanclientmanagement.utils.showKeyBoard
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime

@AndroidEntryPoint
class SearchClientFragment :
    BaseFragment<FragmentSearchClientBinding>(R.layout.fragment_search_client) {
    private val recentSearchWordsAdapter by lazy { RecentSearchWordsAdapter() }
    private lateinit var searchResultClientsAdapter: ClientsAdapter
    private val clientViewModel: ClientViewModel by activityViewModels()
    private val recentSearchWordViewModel: RecentSearchWordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEditTextFocus()
        setCancelClickListener()
        setEditTextSearchListener()
        setEditTextWatcher()
        setRootClickListener()
        setRecentSearchWordsAdapter()
        setSearchResultClientsAdapter()
        setRecentSearchWordListObserver()
        setResultClientListObserver()
        setRecentSearchWordInfoErrorObserver()
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

    private fun setEditTextSearchListener() {
        with(binding.tieSearchClient) {
            setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    requireContext().hideKeyBoard(view)
                    requireActivity().currentFocus?.clearFocus()
                    val newRecentSearchWord = createNewRecentSearchWord()
                    recentSearchWordViewModel.addRecentSearchWord(newRecentSearchWord)
                    true
                } else false
            }
        }
    }

    private fun setEditTextWatcher() {
        binding.tieSearchClient.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s ?: return
                clientViewModel.setSearchWord(s.toString())
            }

            override fun afterTextChanged(s: Editable?) = Unit
        })
    }

    private fun createNewRecentSearchWord(): RecentSearchWord {
        return RecentSearchWord(
            word = binding.tieSearchClient.text.toString(),
            millis = DateTime.now().millis
        )
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
        searchResultClientsAdapter = ClientsAdapter(object : ItemClickListener {
            override fun onItemClick(position: Int) {

            }
        })
        binding.rvSearchClientSearchResultClients.adapter = searchResultClientsAdapter
    }

    private fun setRecentSearchWordListObserver() {
        recentSearchWordViewModel.recentSearchWordList.observe(viewLifecycleOwner, {
            recentSearchWordsAdapter.submitList(it)
        })
    }

    private fun setResultClientListObserver() {
        clientViewModel.resultClientList.observe(viewLifecycleOwner, {
            searchResultClientsAdapter.submitList(it)
        })
    }

    private fun setRecentSearchWordInfoErrorObserver() {
        recentSearchWordViewModel.recentSearchWordInfoError.observe(viewLifecycleOwner, {
            if (it) {
                Snackbar.make(binding.root, "고객 성명을 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                recentSearchWordViewModel.doneRecentSearchWordInfoError()
            }
        })
    }
}
