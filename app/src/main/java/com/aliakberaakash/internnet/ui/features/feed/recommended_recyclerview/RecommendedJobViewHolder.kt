package com.aliakberaakash.internnet.ui.features.feed.recommended_recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aliakberaakash.internnet.R

class RecommendedJobViewHolder(var item : View) : RecyclerView.ViewHolder(item) {

    val titleText : TextView = item.findViewById(R.id.titleText)

}