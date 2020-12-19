package com.aliakberaakash.internnet.ui.features.post_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.aliakberaakash.internnet.R
import com.aliakberaakash.internnet.data.RepositoryImpl
import com.aliakberaakash.internnet.data.model.JobPost
import timber.log.Timber

class PostDetailsViewModel(context: Application) : AndroidViewModel(context) {
    private val repository = RepositoryImpl()

    val post : MutableLiveData<JobPost> = MutableLiveData()
    val applyButtonEnable : MutableLiveData<Boolean> = MutableLiveData(false)
    val applyButtonText = Transformations.map(applyButtonEnable){
        if(it) context.getString(R.string.apply_now)
        else context.getString(R.string.you_applied_for_this_job)
    }


    suspend fun getPost(id :String){
        val result = repository.getSinglePost(id)
        post.postValue(result)
    }

    suspend fun applyForJob(id :String){
        if(repository.applyForJob(id)) {
            Timber.d("Successfully Applied")
            getPost(id)
        }
        else
            Timber.d("Failed to apply for the Job")
    }

    fun checkApplied(){
        if(post.value!=null) {
            val user = repository.getUser()
            applyButtonEnable.postValue(user.email !in post.value?.applicants!!)
            Timber.d(applyButtonEnable.value.toString())
        }
        else Timber.d("post.value is null")
    }

}