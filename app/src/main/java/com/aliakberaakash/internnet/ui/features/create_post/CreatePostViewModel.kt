package com.aliakberaakash.internnet.ui.features.create_post

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliakberaakash.internnet.data.Repository
import com.aliakberaakash.internnet.data.model.JobPost
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

    var jobType = FULL_TIME

    private val repository = Repository()
    fun getCurrentUser() = repository.getCurrentUser()

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

        val jobPost = JobPost(
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

    }

    fun onJobTypeClicked(item : String){
        jobType = item
    }
}