package com.aliakberaakash.internnet.data.network

import com.aliakberaakash.internnet.data.model.JobPost

interface NetworkSource {

    suspend fun postJob(jobPost: JobPost) : Boolean

}