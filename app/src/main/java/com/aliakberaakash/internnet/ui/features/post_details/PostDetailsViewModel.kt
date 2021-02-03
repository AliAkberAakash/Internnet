package com.aliakberaakash.internnet.ui.features.post_details

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.aliakberaakash.internnet.R
import com.aliakberaakash.internnet.data.RepositoryImpl
import com.aliakberaakash.internnet.data.model.JobPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class PostDetailsViewModel(context: Application) : AndroidViewModel(context) {
    private val repository = RepositoryImpl()

    val post : MutableLiveData<JobPost> = MutableLiveData()
    val applyButtonEnable : MutableLiveData<Boolean> = MutableLiveData(false)
    val applyButtonVisibility: MutableLiveData<Int> = MutableLiveData(View.GONE)
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

    fun checkApplyButtonStatus(){
        if(post.value!=null) {
            GlobalScope.launch {
                val user = repository.getUser()

                // check if self post
                applyButtonVisibility.postValue(
                    if(user.email == post.value?.user?.email)
                        View.GONE
                    else View.VISIBLE
                )
                // check if already applied
                applyButtonEnable.postValue(user.email !in post.value?.applicants!!)

                Timber.d(applyButtonEnable.value.toString())
            }

        }
        else Timber.d("post.value is null")
    }

}