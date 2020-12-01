package com.aliakberaakash.internnet.ui.features.create_post

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliakberaakash.internnet.data.Repository
import timber.log.Timber

class CreatePostViewModel : ViewModel() {

    companion object{
        const val FULL_TIME = "Full time"
        const val PART_TIME = "Part time"
    }


    val titleText = MutableLiveData<String>()
    val descriptionText = MutableLiveData<String>()
    val requirementsText = MutableLiveData<String>()
    val benefitsText = MutableLiveData<String>()
    val startingSalaryText = MutableLiveData<String>()
    val maximumSalaryText = MutableLiveData<String>()
    val deadlineText = MutableLiveData("dd/mm/yyyy")

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
                || deadlineText.value.isNullOrEmpty())
    }

    fun onPostClicked(){
        Timber.d(titleText.value)


    }

    fun onJobTypeClicked(item : String){
        jobType = item
    }
}