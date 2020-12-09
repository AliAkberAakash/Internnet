package com.aliakberaakash.internnet.ui.features.create_post

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliakberaakash.internnet.data.RepositoryImpl
import com.aliakberaakash.internnet.data.model.JobPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class CreatePostViewModel : ViewModel() {

    companion object{
        const val FULL_TIME = "Full time"
        const val PART_TIME = "Part time"
        const val DEADLINE_HINT = "dd/mm/yyyy"
    }


    val titleText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()
    val requirementsText = MutableLiveData<String>()
    val benefitsText = MutableLiveData<String>()
    val startingSalaryText = MutableLiveData<String>()
    val maximumSalaryText = MutableLiveData<String>()
    val deadlineText = MutableLiveData(DEADLINE_HINT)
    val isLoading = MutableLiveData(false)
    val isSuccess = MutableLiveData<Boolean>()

    var jobType = FULL_TIME

    private val repository = RepositoryImpl()

    val postButtonState = MediatorLiveData<Boolean>().apply {
        addSource(titleText) {
            value = validateFields()
        }
        addSource(descriptionText) {
            value = validateFields()
        }
        addSource(requirementsText) {
            value = validateFields()
        }
        addSource(benefitsText) {
            value = validateFields()
        }
        addSource(startingSalaryText) {
            value = validateFields()
        }
        addSource(maximumSalaryText) {
            value = validateFields()
        }
        addSource(deadlineText) {
            value = validateFields()
        }
    }

    private fun validateFields(): Boolean {
        return !(titleText.value.isNullOrEmpty()
                || descriptionText.value.isNullOrEmpty()
                || requirementsText.value.isNullOrEmpty()
                || startingSalaryText.value.isNullOrEmpty()
                || maximumSalaryText.value.isNullOrEmpty()
                || deadlineText.value == DEADLINE_HINT)
    }

    fun onPostClicked(){
        val tsLong = System.currentTimeMillis() / 1000
        val ts = tsLong.toString()
        val jobPost = JobPost(
            id = ts,
            user = repository.getUser(),
            jobTitle = titleText.value!!,
            jobType = jobType,
            jobDescription = descriptionText.value!!,
            jobRequirements = requirementsText.value!!,
            jobBenefits = benefitsText.value ?: "",
            startingSalary = startingSalaryText.value!!,
            endingSalary = maximumSalaryText.value!!,
            deadline = deadlineText.value!!
        )

        Timber.d(jobPost.toString())

        viewModelScope.launch(Dispatchers.IO){
            isLoading.postValue(true)
            isSuccess.postValue(repository.postJob(jobPost))
            isLoading.postValue(false)
        }


    }

    fun onJobTypeClicked(item: String){
        jobType = item
    }
}