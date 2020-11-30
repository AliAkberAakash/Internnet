package com.aliakberaakash.internnet.ui.features.create_post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aliakberaakash.internnet.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CreatePostFragment : Fragment() {

    val db = Firebase.firestore

    private lateinit var viewModel: CreatePostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.create_post_layout, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreatePostViewModel::class.java)


    }

}