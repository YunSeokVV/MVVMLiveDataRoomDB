package com.example.mvvmlivedataroomdbpractice.database

interface ExecutorInterface {

    fun executerAsync(task: () -> Unit)
}