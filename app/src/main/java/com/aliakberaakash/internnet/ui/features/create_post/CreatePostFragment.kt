package com.aliakberaakash.internnet.ui.features.create_post

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.aliakberaakash.internnet.R
import com.aliakberaakash.internnet.databinding.CreatePostLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.create_post_layout.*
import timber.log.Timber
import java.util.*


class CreatePostFragment : Fragment() {

    private lateinit var viewModel: CreatePostViewModel
    var dialog : AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(CreatePostViewModel::class.java)

        val binding = CreatePostLayoutBinding.inflate(inflater, container, false)
                .apply {
                    lifecycleOwner = viewLifecycleOwner
                    viewModel = this@CreatePostFragment.viewModel
                }

        binding.deadlineField.setOnClickListener {
            hideKeyBoard()
            DatePickerFragment(::datePickerCallback).show(childFragmentManager, "datePicker")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadingDialog = MaterialAlertDialogBuilder(requireContext())
            .setMessage(getString(R.string.posting))


        viewModel.isSuccess.observe(viewLifecycleOwner, {
            val message = if (it) "Successfully posted!" else "failed to post"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            // if the post was successful go back to homepage
            if(it) findNavController().popBackStack()
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            hideKeyBoard()
            if (it) dialog = loadingDialog.show()
            else dialog?.dismiss()
        })

    }

    private fun hideKeyBoard(){
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(postButton.windowToken, 0)
    }

    private fun datePickerCallback(date: String){
        Timber.d(date)
        viewModel.deadlineText.value=date
    }

}