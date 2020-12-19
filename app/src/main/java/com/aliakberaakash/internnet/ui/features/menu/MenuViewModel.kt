package com.aliakberaakash.internnet.ui.features.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.aliakberaakash.internnet.data.Repository
import com.aliakberaakash.internnet.data.RepositoryImpl
import com.aliakberaakash.internnet.data.model.User
import kotlinx.coroutines.Dispatchers

class MenuViewModel(context : Application) : AndroidViewModel(context) {
    private val repository : Repository = RepositoryImpl()

    val user : LiveData<User> = liveData(Dispatchers.IO) {
        val user : User = repository.getUser()
        emit(user)
    }
}