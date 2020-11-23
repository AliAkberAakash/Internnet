package com.aliakberaakash.internnet.ui.features.post_details

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aliakberaakash.internnet.R


class PostDetailsViewHolder(item : View) : RecyclerView.ViewHolder(item) {

    val profileImage : ImageView = item.findViewById(R.id.profile_image)
    val userName : TextView = item.findViewById(R.id.user_name)

}