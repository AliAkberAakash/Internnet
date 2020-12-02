package com.aliakberaakash.internnet.ui.features.winner_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliakberaakash.internnet.data.RepositoryImpl
import com.aliakberaakash.internnet.data.model.Post
import com.aliakberaakash.internnet.data.model.User
import com.google.firebase.firestore.ktx.toObject

class WinnerViewModel : ViewModel() {
    private val repository = RepositoryImpl()

    val post = MutableLiveData<Post>()
    val user = MutableLiveData<User>()

    suspend fun getPost(documentId : String){
        val snapShot = repository.getPost(documentId)?.toObject<Post>()
        post.postValue(snapShot)
        getUser(snapShot?.winner ?: "")
    }

    private suspend fun getUser(userId : String){
        user.postValue(repository.getUser(userId)?.toObject<User>())
    }

    fun checkUser(email: String) = repository.checkCurrentUser(email)
}