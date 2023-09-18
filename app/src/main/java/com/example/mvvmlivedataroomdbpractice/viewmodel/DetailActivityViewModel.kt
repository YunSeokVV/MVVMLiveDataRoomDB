package com.example.mvvmlivedataroomdbpractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailActivityViewModel : ViewModel() {
    private var editedUserName = MutableLiveData<String>()

    fun updateUserName(name : String) {
        val currentValue = editedUserName.value ?: ""
        editedUserName.value = currentValue + name
    }


    //기존에 내가 짰던 메소드
//    fun getUserName() : MutableLiveData<String>{
//
//        return editedUserName
//    }

    //지피티 형님이 이렇게 하면 코드를 줄일 수 있다고 해주셨다.
    fun getUserNameLiveData(): MutableLiveData<String> = editedUserName
}