package com.example.mvvmlivedataroomdbpractice.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmlivedataroomdbpractice.database.UserDatabase
import com.example.mvvmlivedataroomdbpractice.model.User
import com.example.mvvmlivedataroomdbpractice.repository.DataRepository
import com.orhanobut.logger.Logger

class MainActivityViewModel(private val context : Context) : ViewModel(){

    // 사용자가 EditText에 입력한 값을 관찰하는 LiveData이다.
    private var userNameLiveData = MutableLiveData<String>()


    // 다른 예제에서 확인했던 DB에서 데이터를 불러오는 방법
    //private lateinit var getAllUsers : LiveData<List<User>>

    // DB에 있는 모든 사용자들을 불러와서 담아주는 라이브데이터 (이건 내가 한거)
    private lateinit var getAllUsers2 : LiveData<List<User>>

    // DB에서 데이터를 갖고오는 역할을 해주는 DataRepository객체
    val repository :DataRepository



    init{
        // dao는 DB에서 해야하는 여러 역할들을 해준다. CRUD일을 전부다 해준다.
        val dao = UserDatabase.getDatabase(context).getEventsDao()
        repository = DataRepository(context, dao)

        //getAllUsers = repository.getAllData()

        //아래 녀석이 Unit 즉 void 이다. 내가 예상했기로는 LiveData<List<User>> 형태로 데이터를 받아오는건데...
        //repository.getAllData().toString()

        getAllUsers2 = repository.getAllUsers()


        //todo : 이거 꼭 as 연산자를 사용했어야 하는지 한번 다시 고민해봐바
        //userNameLiveData = repository.getAllData()
    }




    // 이 함수를 view에서 호출할거임ㅇㅇ
    fun insertData(name : String){
        val currentValue = userNameLiveData.value ?: ""
        userNameLiveData.value = currentValue + name
        val user = User(0, name)
        //DB에 데이터 저장 로직 실행
        repository.insertData(user)
        userNameLiveData.value = ""
    }

    fun getUserNameLiveData() : LiveData<String>{

        return userNameLiveData
    }

    fun getAllUsers() : LiveData<List<User>>{

        return getAllUsers2
    }
}