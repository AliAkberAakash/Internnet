package com.aliakberaakash.internnet.ui.features.create_post

import androidx.lifecycle.ViewModel
import com.aliakberaakash.internnet.data.Repository

class CreatePostViewModel : ViewModel() {

    private val repository = Repository()

    fun getCurrentUser() = repository.getCurrentUser()

}