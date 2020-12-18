package com.aliakberaakash.internnet.data.network

import com.aliakberaakash.internnet.data.model.JobPost
import com.aliakberaakash.internnet.data.model.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface NetworkSource {

    suspend fun postJob(jobPost: JobPost) : Boolean
    suspend fun getAllPost() : QuerySnapshot
    suspend fun getSinglePost(id : String) : DocumentSnapshot
    suspend fun applyForJob(id : String) : Boolean
    fun getUser() : User

}