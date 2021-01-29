package com.aliakberaakash.internnet.data

import com.aliakberaakash.internnet.data.model.JobPost
import com.aliakberaakash.internnet.data.model.User

interface Repository {

    suspend fun postJob(jobPost : JobPost) : Boolean
    suspend fun getAllPost() : List<JobPost>
    suspend fun getSinglePost(id : String) : JobPost
    suspend fun applyForJob(id : String) : Boolean
    suspend fun getUser() : User

}