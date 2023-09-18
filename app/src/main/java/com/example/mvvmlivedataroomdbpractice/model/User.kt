package com.example.mvvmlivedataroomdbpractice.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name : String
) : Serializable {}
