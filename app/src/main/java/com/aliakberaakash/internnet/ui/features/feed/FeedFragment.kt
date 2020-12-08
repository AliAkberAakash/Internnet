package com.aliakberaakash.internnet.ui.features.feed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliakberaakash.internnet.R
import com.aliakberaakash.internnet.data.model.JobPost
import com.aliakberaakash.internnet.ui.features.feed.feed_recyclerview.PostAdapter
import com.aliakberaakash.internnet.ui.features.feed.recommended_recyclerview.RecommendedJobAdapter
import kotlinx.android.synthetic.main.feed_fragment.*

class FeedFragment : Fragment() {

    private lateinit var adapter : PostAdapter
    private lateinit var recommendedJobAdapter : RecommendedJobAdapter
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
        recommendedJobAdapter = RecommendedJobAdapter(allData)
        feed_recyclerview.adapter = adapter
        jobsForYou.adapter = recommendedJobAdapter


        viewModel.allPost.observe(viewLifecycleOwner, {
            allData.addAll(it)
            recommendedJobAdapter.notifyDataSetChanged()
            adapter.notifyDataSetChanged()
        })

    }

}