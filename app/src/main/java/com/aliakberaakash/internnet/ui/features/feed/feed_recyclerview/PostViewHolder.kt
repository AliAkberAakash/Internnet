package com.aliakberaakash.internnet.ui.features.feed.feed_recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.aliakberaakash.internnet.R

class PostViewHolder(item : View) : RecyclerView.ViewHolder(item){
    val postContainer : CardView = item.findViewById(R.id.post_container)
    val companyLogo : ImageView = item.findViewById(R.id.companyLogo)
    val jobTitle : TextView = item.findViewById(R.id.jobTitle)
    val companyName : TextView = item.findViewById(R.id.companyName)
    val salaryText : TextView = item.findViewById(R.id.salaryText)
}