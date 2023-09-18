package com.example.mvvmlivedataroomdbpractice.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlivedataroomdbpractice.R
import com.example.mvvmlivedataroomdbpractice.model.User
import com.example.mvvmlivedataroomdbpractice.viewmodel.DetailActivityViewModel
import com.example.mvvmlivedataroomdbpractice.viewmodel.MainActivityViewModel
import com.orhanobut.logger.Logger

class DetailActivity : AppCompatActivity() {

    private lateinit var detailActivityViewModel: DetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = getIntent()
        val likedData = intent.getSerializableExtra("userObject") as User

        Logger.v(likedData.name)

        val userName : EditText = findViewById(R.id.userName)
        val editBtn : Button = findViewById(R.id.done)

        detailActivityViewModel = ViewModelProvider(this).get(DetailActivityViewModel::class.java)

        // 아래 녀석이 정확하게 어떤 역할을 하는 코드인지 명시하자.
        detailActivityViewModel.getUserNameLiveData().observe(this){name ->
            userName.text = Editable.Factory.getInstance().newEditable(name)
        }

        editBtn.setOnClickListener(View.OnClickListener {


        })

    }
}