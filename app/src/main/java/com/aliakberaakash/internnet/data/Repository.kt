package com.aliakberaakash.internnet.data

import com.aliakberaakash.internnet.data.model.JobPost

interface Repository {

    suspend fun postJob(jobPost : JobPost) : Boolean
    suspend fun getAllPost() : List<JobPost>

}