package com.aliakberaakash.internnet.data

import com.aliakberaakash.internnet.data.model.JobPost
import com.aliakberaakash.internnet.data.network.NetworkSource
import com.aliakberaakash.internnet.data.network.NetworkSourceFirebaseImplementation

class RepositoryImpl : Repository{


    private val networkSource : NetworkSource = NetworkSourceFirebaseImplementation()

    override suspend fun postJob(jobPost: JobPost) : Boolean {
        return networkSource.postJob(jobPost)
    }

    override suspend fun getAllPost(): List<JobPost> {

        val snapshot = networkSource.getAllPost()
        val allPost : MutableList<JobPost> = mutableListOf()

        for(document in snapshot)
            allPost.add(document.toObject(JobPost::class.java))

        return allPost
    }

    override suspend fun getSinglePost(id: String): JobPost {
        val snapshot = networkSource.getSinglePost(id)
        return snapshot.toObject(JobPost::class.java)!!
    }

    override fun getUser() = networkSource.getUser()
}