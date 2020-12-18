package com.aliakberaakash.internnet.ui.features.post_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.aliakberaakash.internnet.R
import com.aliakberaakash.internnet.core.makeItGone
import kotlinx.android.synthetic.main.post_details_fragment.*
import kotlinx.coroutines.*

class PostDetailsFragment : Fragment() {

    val args : PostDetailsFragmentArgs by navArgs()
    private lateinit var viewModel : PostDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.post_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostDetailsViewModel::class.java)

        viewModel.post.observe(viewLifecycleOwner, {

            titleText.text = it.jobTitle
            companyNameText.text = it.user?.userName
            jobTypeText.text = it.jobType
            salaryText.text = it.endingSalary
            descriptionText.text = it.jobDescription
            requirementsText.text = it.jobRequirements
            deadlineText.text = getString(R.string.deadline_text, it.deadline)
            if(it.jobBenefits.isEmpty())
                benefitsLabel.makeItGone()
            benefitsText.text = it.jobBenefits
            

        })

        GlobalScope.launch(Dispatchers.IO) {
            viewModel.getPost(args.postId)
        }


    }


}