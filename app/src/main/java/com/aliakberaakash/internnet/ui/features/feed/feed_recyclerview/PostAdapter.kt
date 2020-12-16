package com.aliakberaakash.internnet.ui.features.feed.feed_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aliakberaakash.internnet.R
import com.aliakberaakash.internnet.data.model.JobPost
import com.aliakberaakash.internnet.ui.features.feed.FeedFragmentDirections

class PostAdapter(var postList: List<JobPost>) : RecyclerView.Adapter<PostViewHolder>() {

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        context = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.single_post_item, parent, false)
        return PostViewHolder(view)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val item = postList[position]

        holder.jobTitle.text = item.jobTitle
        holder.companyName.text = item.user?.userName
        /*holder.postedTimeText.text = "${item.createdAt}"*/

        holder.postContainer.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToPostDetailsFragment(item.id)
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount() = postList.size
}