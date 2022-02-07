package com.kldaji.loanclientmanagement.ui.client

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentAddClientBinding
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.model.data.Loan
import com.kldaji.loanclientmanagement.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddClientFragment : BaseFragment<FragmentAddClientBinding>(R.layout.fragment_add_client) {
    private val clientViewModel: ClientViewModel by activityViewModels()
    private val selectImagesLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.clipData?.let {
                    for (i in 0 until it.itemCount) {
                        println(it.getItemAt(i).uri)
                    }
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarIconClickListener()
        setAddButtonClickListener()
        setCameraClickListener()
        setClientInfoErrorObserver()
        setSuccessInAddClientObserver()
    }

    private fun setToolbarIconClickListener() {
        binding.tbAddClient.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.close_edit_complete -> {
                    this.findNavController().popBackStack()
                    true
                }
                else -> false
            }
        }
    }

    private fun setAddButtonClickListener() {
        binding.btnAddClientFragmentAdd.setOnClickListener {
            val loan = getLoan()
            val newClient = getNewClient(loan)
            clientViewModel.addClient(newClient)
        }
    }

    private fun setCameraClickListener() {
        binding.tvAddClientCamera.setOnClickListener {
            selectImagesLauncher.launch(Intent().apply {
                type = "image/*"
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                action = Intent.ACTION_GET_CONTENT
                Intent.createChooser(this, "Select Images")
            })
        }
    }

    private fun getLoan(): Loan {
        return when (binding.rgAddClient.checkedRadioButtonId) {
            R.id.rb_add_client_security -> Loan.SECURITY
            else -> Loan.JEONSE
        }
    }

    private fun getNewClient(loan: Loan): Client {
        return Client(
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
