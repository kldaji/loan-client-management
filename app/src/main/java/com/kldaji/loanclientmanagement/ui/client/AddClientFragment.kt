package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentAddClientBinding
import com.kldaji.loanclientmanagement.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddClientFragment : BaseFragment<FragmentAddClientBinding>(R.layout.fragment_add_client) {
    private val clientViewModel: ClientViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
