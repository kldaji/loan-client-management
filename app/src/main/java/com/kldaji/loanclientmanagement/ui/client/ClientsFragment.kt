package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentClientsBinding
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.ui.client.adapter.ClientsAdapter
import com.kldaji.loanclientmanagement.ui.common.BaseFragment

class ClientsFragment : BaseFragment<FragmentClientsBinding>(R.layout.fragment_clients) {

    private val clientsAdapter by lazy { ClientsAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarIconClickListener()
        setClientsAdapter()
        // dummy data
        val clientList =
            listOf(Client(1, "123"), Client(1, "123"), Client(1, "123"), Client(1, "123"))
        clientsAdapter.submitList(clientList)
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
        binding.rvClients.adapter = clientsAdapter
    }
}
