package com.aliakberaakash.internnet.ui.features.post_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliakberaakash.internnet.data.RepositoryImpl
import com.aliakberaakash.internnet.data.model.JobPost

class PostDetailsViewModel : ViewModel() {
    private val repository = RepositoryImpl()

    val post : MutableLiveData<JobPost> = MutableLiveData()

    suspend fun getPost(id :String){
        val result = repository.getSinglePost(id)
        post.postValue(result)
    }


}