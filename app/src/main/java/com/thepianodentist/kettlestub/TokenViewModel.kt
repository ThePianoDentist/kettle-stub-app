package com.thepianodentist.kettlestub

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TokenViewModel : ViewModel() {
    private val mutableToken = MutableLiveData<String>()
    val token: LiveData<String> get() = mutableToken

    fun setToken(newToken: String) {
        mutableToken.value = newToken
    }

    fun getValue(): String{
        return mutableToken.value?:"missing"
    }
}
