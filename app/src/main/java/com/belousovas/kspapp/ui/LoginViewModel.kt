package com.belousovas.kspapp.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belousovas.kspapp.data.Repository

class LoginViewModel : ViewModel() {
    private val repository = Repository()
    var isLoginSuccess = MutableLiveData<Boolean>()


    fun login(user: String, password: String) {
        repository.login(user, password, isLoginSuccess)
    }
}