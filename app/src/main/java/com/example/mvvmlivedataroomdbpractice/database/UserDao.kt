package com.example.mvvmlivedataroomdbpractice.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mvvmlivedataroomdbpractice.model.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user : User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>

    @Update
    fun updateUser(user : User)

    @Delete
    fun delteUser(user : User)
}