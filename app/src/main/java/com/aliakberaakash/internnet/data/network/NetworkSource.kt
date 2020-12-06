package com.aliakberaakash.internnet.data.network

import com.aliakberaakash.internnet.data.model.JobPost
import com.google.firebase.firestore.QuerySnapshot

interface NetworkSource {

    suspend fun postJob(jobPost: JobPost) : Boolean
    suspend fun getAllPost() : QuerySnapshot

}