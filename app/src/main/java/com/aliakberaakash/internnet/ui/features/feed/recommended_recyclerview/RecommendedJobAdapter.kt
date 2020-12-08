package com.aliakberaakash.internnet.ui.features.feed.recommended_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliakberaakash.internnet.R
import com.aliakberaakash.internnet.data.model.JobPost

class RecommendedJobAdapter(var postList: List<JobPost>) : RecyclerView.Adapter<RecommendedJobViewHolder>(){
    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedJobViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recomended_job_view, parent, false)
        return RecommendedJobViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendedJobViewHolder, position: Int) {
        val item = postList[position]
        holder.titleText.text = item.jobTitle
    }

    override fun getItemCount() = postList.size

}