package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentAddClientBinding
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.model.data.Loan
import com.kldaji.loanclientmanagement.ui.common.BaseFragment
import com.kldaji.loanclientmanagement.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddClientFragment : BaseFragment<FragmentAddClientBinding>(R.layout.fragment_add_client) {
    private val clientViewModel: ClientViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarIconClickListener()
        setAddButtonClickListener()
        setClientInfoErrorObserver()
        setSuccessInAddClientObserver()
    }

    private fun setToolbarIconClickListener() {
        binding.tbAddClient.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.close -> {
                    this.findNavController().popBackStack()
                    true
                }
                else -> false
            }
        }
    }

    private fun setAddButtonClickListener() {
        binding.btnAddClientFragmentAdd.setOnClickListener {
            val loan = when (binding.rgAddClient.checkedRadioButtonId) {
                R.id.rb_add_client_security -> Loan.SECURITY
                else -> Loan.JEONSE
            }
            val newClient = Client(
                loan = loan,
                name = binding.etAddClientName.text.toString(),
                rrmFront = binding.etAddClientRrmFront.text.toString(),
                rrmBack = binding.etAddClientRrmBack.text.toString(),
                callMiddle = binding.etAddClientCallMiddle.text.toString(),
                callBack = binding.etAddClientCallBack.text.toString(),
                meetingDate = binding.tvAddClientMeetingDate.text.toString(),
                loanStartDate = binding.tvAddClientLoanStartDate.text.toString(),
                docs = ""
            )
            clientViewModel.addClient(newClient)
        }
    }

    private fun setClientInfoErrorObserver() {
        clientViewModel.clientInfoError.observe(viewLifecycleOwner, {
            if (it) {
                Snackbar.make(binding.root, "모든 고객 정보를 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                clientViewModel.doneClientInfoError()
            }
        })
    }

    private fun setSuccessInAddClientObserver() {
        clientViewModel.successInAddClient.observe(viewLifecycleOwner, {
            if (it) {
                this.findNavController().popBackStack()
                clientViewModel.doneSuccessInAddClient()
            }
        })
    }
}
