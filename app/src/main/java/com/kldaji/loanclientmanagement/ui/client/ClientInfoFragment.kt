package com.kldaji.loanclientmanagement.ui.client

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kldaji.loanclientmanagement.R
import com.kldaji.loanclientmanagement.databinding.FragmentAddClientBinding
import com.kldaji.loanclientmanagement.model.data.Client
import com.kldaji.loanclientmanagement.model.data.Loan
import com.kldaji.loanclientmanagement.ui.client.adapter.DocsImageAdapter
import com.kldaji.loanclientmanagement.ui.common.BaseFragment
import com.kldaji.loanclientmanagement.utils.EventObserver

class ClientInfoFragment : BaseFragment<FragmentAddClientBinding>(R.layout.fragment_add_client) {
    private val clientsViewModel: ClientViewModel by activityViewModels()
    private val args by navArgs<ClientInfoFragmentArgs>()
    private lateinit var docsImageAdapter: DocsImageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarInfo()
        setVisibility(false)
        setDocsImageAdapter()
        setInfoContent()
        setEnabled(false)
        setToolbarIconClickListener()
        setEditableObserver()
    }

    private fun setToolbarInfo() {
        with(binding.tbAddClient) {
            title = "고객 정보"
            setNavigationIcon(R.drawable.ic_delete_24)
            menu.getItem(0).setIcon(R.drawable.ic_edit_24)
        }
    }

    private fun setVisibility(flag: Boolean) {
        with(binding) {
            tvAddClientMeetingDateIcon.isVisible = flag
            tvAddClientLoanStartDateIcon.isVisible = flag
            // always gone
            btnAddClientFragmentAdd.isVisible = false
        }
    }

    private fun setDocsImageAdapter() {
        docsImageAdapter = DocsImageAdapter()
        binding.vpAddClientFragment.adapter = docsImageAdapter
    }

    private fun setInfoContent() {
        with(args.client) {
            binding.etAddClientName.setText(name)
            binding.etAddClientRrmFront.setText(rrmFront)
            binding.etAddClientRrmBack.setText(rrmBack)
            binding.etAddClientCallMiddle.setText(callMiddle)
            binding.etAddClientCallBack.setText(callBack)
            docsImageAdapter.submitList(docs)
        }
    }

    private fun setEnabled(flag: Boolean) {
        with(binding) {
            rbAddClientSecurity.isEnabled = flag
            rbAddClientJeonse.isEnabled = flag
            etAddClientName.isEnabled = flag
            etAddClientRrmFront.isEnabled = flag
            etAddClientRrmBack.isEnabled = flag
            etAddClientCallMiddle.isEnabled = flag
            etAddClientCallBack.isEnabled = flag
        }
    }

    private fun setToolbarIconClickListener() {
        binding.tbAddClient.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.close_edit_complete -> {
                    clientsViewModel.toggleEditable()
                    true
                }
                else -> false
            }
        }
        binding.tbAddClient.setNavigationOnClickListener {
            this.findNavController().popBackStack()
        }
    }

    private fun setEditableObserver() {
        clientsViewModel.editable.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                binding.tbAddClient.menu.getItem(0).setIcon(R.drawable.ic_complete_24)
                setVisibility(true)
                setEnabled(true)
            } else {
                binding.tbAddClient.menu.getItem(0).setIcon(R.drawable.ic_edit_24)
                setVisibility(false)
                setEnabled(false)
                val loan = getLoan()
                val updatedClient = getUpdatedClient(loan)
                clientsViewModel.updateClient(args.client, updatedClient)
            }
        })
    }

    private fun getLoan(): Loan {
        return when (binding.rgAddClient.checkedRadioButtonId) {
            R.id.rb_add_client_security -> Loan.SECURITY
            else -> Loan.JEONSE
        }
    }

    private fun getUpdatedClient(loan: Loan): Client {
        return args.client.copy(
            loan = loan,
            name = binding.etAddClientName.text.toString(),
            rrmFront = binding.etAddClientRrmFront.text.toString(),
            rrmBack = binding.etAddClientRrmBack.text.toString(),
            callMiddle = binding.etAddClientCallMiddle.text.toString(),
            callBack = binding.etAddClientCallBack.text.toString(),
            meetingDate = binding.tvAddClientMeetingDate.text.toString(),
            loanStartDate = binding.tvAddClientLoanStartDate.text.toString(),
            docs = args.client.docs
        )
    }
}
