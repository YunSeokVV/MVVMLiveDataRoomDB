package com.example.mvvmlivedataroomdbpractice.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mvvmlivedataroomdbpractice.database.ExecutorInterface
import com.example.mvvmlivedataroomdbpractice.database.UserDao
import com.example.mvvmlivedataroomdbpractice.database.UserDatabase
import com.example.mvvmlivedataroomdbpractice.model.User
import com.orhanobut.logger.Logger
import java.util.concurrent.Executors

class DataRepository(private val context : Context, private val userDao: UserDao) {

    fun insertData(user: User) {
        val execute = object : ExecutorInterface {
            override fun executerAsync(task: () -> Unit) {
                val executor = Executors.newSingleThreadExecutor()
                executor.execute{
                    task.invoke()
                }
            }

        }

        execute.executerAsync {
            // repository에서 context는 또 어케 써야하노
            val dbInstance = UserDatabase.getDatabase(context)
            dbInstance.getEventsDao().insertUser(user)
        }

        //userDao.insertUser(user)
    }

    fun updateData(user: User) {
        val execute = object : ExecutorInterface {
            override fun executerAsync(task: () -> Unit) {
                val executor = Executors.newSingleThreadExecutor()
                executor.execute{
                    task.invoke()
                }
            }
        }
        execute.executerAsync {
            val dbInstance = UserDatabase.getDatabase(context)
            dbInstance.getEventsDao().updateUser(user)
        }
    }


    fun delteData(user: User) {
        val execute = object : ExecutorInterface {
            override fun executerAsync(task: () -> Unit) {
                val executor = Executors.newSingleThreadExecutor()
                executor.execute{
                    task.invoke()
                }
            }
        }
        execute.executerAsync {
            val dbInstance = UserDatabase.getDatabase(context)
            dbInstance.getEventsDao().delteUser(user)
        }
    }

    // 내가 만든 DB에서 데이터를 불러오는 방법
    fun getAllUsers() : LiveData<List<User>>{
        val dbInstance = UserDatabase.getDatabase(context)
        val result = dbInstance.getEventsDao().getAllUsers()

        return result
    }

    // 다른 예제에서 확인했던 DB에서 데이터를 불러오는 방법. 훨씬 내가 만든 것 보다 간결하다.
    //fun getAllData(): LiveData<List<User>> = userDao.getAllUsers()


}