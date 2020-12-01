package com.aliakberaakash.internnet.ui.features.create_post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aliakberaakash.internnet.databinding.CreatePostLayoutBinding
import timber.log.Timber
import java.util.*


class CreatePostFragment : Fragment() {

    private lateinit var viewModel: CreatePostViewModel

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
            DatePickerFragment(::datePickerCallback).show(childFragmentManager, "datePicker")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun datePickerCallback(date :String){
        Timber.d(date)
        viewModel.deadlineText.value=date
    }

}