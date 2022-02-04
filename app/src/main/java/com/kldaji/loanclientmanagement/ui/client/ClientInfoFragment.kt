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
        setToolbarInfo()
        setVisibility()
        setInfoContent()
        setEnabled()
    }

    private fun setToolbarInfo() {
        with (binding.tbAddClient) {
            title = "고객 정보"
            setNavigationIcon(R.drawable.ic_delete_24)
            inflateMenu(R.menu.toolbar_info_client)
        }
    }

    private fun setVisibility() {
        with(binding) {
            groupVisibility.isVisible = false
        }
    }

    private fun setEnabled() {
        with(binding) {
            rbAddClientSecurity.isEnabled = false
            rbAddClientJeonse.isEnabled = false
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
