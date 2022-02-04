package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentAddClientBinding
import com.kldaji.loanclientmanagement.ui.common.BaseFragment

class ClientInfoFragment : BaseFragment<FragmentAddClientBinding>(R.layout.fragment_add_client) {
    private val args by navArgs<ClientInfoFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setVisibility()
        setEnabled()
        setInfoContent()
    }

    private fun setVisibility() {
        with(binding) {
            tvAddClientMeetingDateIcon.isVisible = false
            tvAddClientLoanStartDateIcon.isVisible = false
        }
    }

    private fun setEnabled() {
        with(binding) {
            rgAddClient.isEnabled = false
            etAddClientName.isEnabled = false
            etAddClientRrmFront.isEnabled = false
            etAddClientRrmBack.isEnabled = false
            etAddClientCallMiddle.isEnabled = false
            etAddClientCallBack.isEnabled = false
        }
    }

    private fun setInfoContent() {
        with(args.client) {
            binding.etAddClientName.setText(name)
            binding.etAddClientRrmFront.setText(rrmFront)
            binding.etAddClientRrmBack.setText(rrmBack)
            binding.etAddClientCallMiddle.setText(callMiddle)
            binding.etAddClientCallBack.setText(callBack)
        }
    }
}
