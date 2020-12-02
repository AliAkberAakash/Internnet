package com.aliakberaakash.internnet.ui.features.feed

import androidx.lifecycle.ViewModel
import com.aliakberaakash.internnet.data.RepositoryImpl

class FeedViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    fun checkCurrentUser(email : String) = repository.checkCurrentUser(email)

    fun getCurrentUser() = repository.getCurrentUser()

    suspend fun onIWantThisClicked(documentId : String) = repository.onIWantThisClicked(documentId)

    suspend fun onCancelClaimClicked(documentId : String) = repository.onCancelClaimClicked(documentId)

}