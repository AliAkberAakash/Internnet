package com.aliakberaakash.internnet.ui.features.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliakberaakash.internnet.R
import com.aliakberaakash.internnet.data.model.JobPost
import com.aliakberaakash.internnet.data.model.Post
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.coroutines.runBlocking

class FeedFragment : Fragment() {

    private lateinit var adapter : PostAdapter
    private var allData : MutableList<JobPost> = mutableListOf()

    private lateinit var viewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)

        adapter = PostAdapter(allData)
        feed_recyclerview.adapter = adapter

        viewModel.allPost.observe(viewLifecycleOwner, {
            allData.addAll(it)
            adapter.notifyDataSetChanged()
        })

    }

}