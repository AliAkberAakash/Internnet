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

    val user = Firebase.auth.currentUser
    private val db = Firebase.firestore
    private val networkSource : NetworkSource = NetworkSourceFirebaseImplementation()

    fun checkCurrentUser(email : String) = email == user?.email


    fun getCurrentUser() = User(
        user?.email!!,
        user.displayName!!,
        ""
    )

    suspend fun getUser(userId : String) : DocumentSnapshot?{
        return try {
            db.collection("users")
                .document(userId)
                .get()
                .await()
        }catch (e : Exception){
            null
        }
    }

    suspend fun getPost(documentId: String) : DocumentSnapshot?{
        return try {
            db.collection("posts")
                    .document(documentId)
                    .get()
                    .await()
        }catch (e : Exception){
            null
        }
    }

    suspend fun onIWantThisClicked(documentId : String){
        db.collection("posts")
            .document(documentId)
            .update("claimers", FieldValue.arrayUnion(user?.email))
    }

    suspend fun onCancelClaimClicked(documentId: String) {
        db.collection("posts")
            .document(documentId)
            .update("claimers", FieldValue.arrayRemove(user?.email))
    }

    override suspend fun postJob(jobPost: JobPost) : Boolean {
        return networkSource.postJob(jobPost)
    }
}