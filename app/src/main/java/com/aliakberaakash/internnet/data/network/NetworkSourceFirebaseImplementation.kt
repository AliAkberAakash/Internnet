package com.aliakberaakash.internnet.data.network

import com.aliakberaakash.internnet.data.model.JobPost
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class NetworkSourceFirebaseImplementation : NetworkSource {

    private val db = Firebase.firestore

    override suspend fun postJob(jobPost: JobPost): Boolean {
        return try {
            db.collection("posts")
                    .document(jobPost.id)
                    .set(jobPost).await()
            true
        }catch (e : Exception){
            Timber.d(e.localizedMessage)
            false
        }
    }

    override suspend fun getAllPost(): QuerySnapshot {
        try {
            return db.collection("posts")
                .get().await()
        }catch (e : Exception){
            Timber.d(e.localizedMessage)
            throw e
        }
    }
}