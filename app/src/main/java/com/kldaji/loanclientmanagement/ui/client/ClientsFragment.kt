package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentClientsBinding
import com.kldaji.loanclientmanagement.ui.common.BaseFragment

class ClientsFragment : BaseFragment<FragmentClientsBinding>(R.layout.fragment_clients) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarIconClickListener()
    }

    private fun setToolbarIconClickListener() {
        binding.tbClients.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    this.findNavController()
                        .navigate(R.id.action_clientsFragment_to_clientSearchFragment)
                    true
                }
                R.id.add_client -> true
                else -> false
            }
        }
    }
}
