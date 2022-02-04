package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentClientsBinding
import com.kldaji.loanclientmanagement.ui.client.adapter.ClientsAdapter
import com.kldaji.loanclientmanagement.ui.client.adapter.ItemClickListener
import com.kldaji.loanclientmanagement.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientsFragment : BaseFragment<FragmentClientsBinding>(R.layout.fragment_clients) {
    private val clientViewModel: ClientViewModel by activityViewModels()
    private lateinit var clientsAdapter: ClientsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarIconClickListener()
        setClientsAdapter()
        setClientListObserver()
    }

    private fun setToolbarIconClickListener() {
        binding.tbClients.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    this.findNavController()
                        .navigate(R.id.action_clientsFragment_to_searchClientFragment)
                    true
                }
                R.id.add_client -> {
                    this.findNavController()
                        .navigate(R.id.action_clientsFragment_to_addClientFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setClientsAdapter() {
        clientsAdapter = ClientsAdapter(object : ItemClickListener {
            override fun onItemClick(position: Int) {
                this@ClientsFragment.findNavController()
                    .navigate(ClientsFragmentDirections.actionClientsFragmentToClientInfoFragment(
                        clientsAdapter.currentList[position]))
            }
        })
        binding.rvClients.adapter = clientsAdapter
    }

    private fun setClientListObserver() {
        clientViewModel.clientList.observe(viewLifecycleOwner, { clientList ->
            clientsAdapter.submitList(clientList)
        })
    }
}
