package com.aliakberaakash.internnet.data.network

import com.aliakberaakash.internnet.data.model.JobPost
import com.aliakberaakash.internnet.data.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class NetworkSourceFirebaseImplementation : NetworkSource {

    private val db = Firebase.firestore
    private val firebaseUser = Firebase.auth.currentUser

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

    override suspend fun getSinglePost(id : String): DocumentSnapshot {
        try {
            return  db.collection("posts")
                .document(id).get().await()
        }catch (e : Exception){
            Timber.d(e.localizedMessage)
            throw e
        }
    }

    override suspend fun applyForJob(id: String): Boolean {
        return try{
            db.collection("posts")
                    .document(id)
                    .update("applicants", FieldValue.arrayUnion(firebaseUser!!.email))
            true
        }catch (e : Exception){
            Timber.d(e.localizedMessage)
            false
        }
    }

    override fun getUser(): User {
        return User(
            email = firebaseUser?.email ?: "",
            userName = firebaseUser?.displayName ?: "",
            image = firebaseUser?.photoUrl?.toString() ?: ""
        )
    }
}