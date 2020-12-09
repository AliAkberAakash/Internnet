package com.aliakberaakash.internnet.data

import com.aliakberaakash.internnet.data.model.JobPost
import com.aliakberaakash.internnet.data.model.User
import com.aliakberaakash.internnet.data.network.NetworkSource
import com.aliakberaakash.internnet.data.network.NetworkSourceFirebaseImplementation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import timber.log.Timber

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

    override fun getUser() = networkSource.getUser()
}