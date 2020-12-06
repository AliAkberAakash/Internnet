package com.aliakberaakash.internnet.ui.features.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.aliakberaakash.internnet.data.RepositoryImpl
import com.aliakberaakash.internnet.data.model.JobPost

class FeedViewModel : ViewModel() {

    private val repository = RepositoryImpl()
    val allPost : LiveData<List<JobPost>> = liveData{
        val data = getAllPost()
        emit(data)
    }

    private suspend fun getAllPost() : List<JobPost>{
        return repository.getAllPost()
    }

}