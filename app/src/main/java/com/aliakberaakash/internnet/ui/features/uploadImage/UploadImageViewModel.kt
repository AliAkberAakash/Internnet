package com.aliakberaakash.internnet.ui.features.uploadImage

import androidx.lifecycle.ViewModel
import com.aliakberaakash.internnet.data.Repository

class UploadImageViewModel : ViewModel() {

    private val repository = Repository()

    fun getCurrentUser() = repository.getCurrentUser()

}