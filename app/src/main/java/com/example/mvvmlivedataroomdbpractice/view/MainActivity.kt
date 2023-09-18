package com.example.mvvmlivedataroomdbpractice.view
// 연습하기 위해 만들어본 예제이다.
// 현재 DB에서 데이터를 Create, Read 하는 것 까지는 했느데 Update 랑 Delte는 안했다.
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmlivedataroomdbpractice.R
import com.example.mvvmlivedataroomdbpractice.adpater.UserAdapter
import com.example.mvvmlivedataroomdbpractice.model.User
import com.example.mvvmlivedataroomdbpractice.viewmodel.MainActivityViewModel
import com.example.mvvmlivedataroomdbpractice.viewmodel.MainActivityViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MainActivity : AppCompatActivity() {
//    private val mainActivityViewModel by lazy{
//        //ViewModelProvider(this).get(MainActivityViewModel::class.java)
//        //val factory = MainActivityViewModel(applicationContext)
//
//    }
    private lateinit var mainActivityViewModel: MainActivityViewModel

    private val listener by lazy {
        object : UserAdapter.OnItemClickListener{
            override fun onItemClick(user: User) {
                val intent = Intent(baseContext, DetailActivity::class.java)
                intent.putExtra("userObject", user)
                startForResult.launch(intent)
            }
        }
    }

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())
        val factory = MainActivityViewModelFactory(applicationContext)
        val insertBtn : Button = findViewById(R.id.insert)
        //val userName : EditText = findViewById(R.id.userName)
        val userName : EditText = findViewById(R.id.userName)


        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        mainActivityViewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // ViewModel이 View 단에서 어떤 일을 해야하는지 명확하게 파악해야한다.
        // todo : 근데 내가지금 observe 메소드가 어떤 역할을 해주는지 잘 모른다.
        mainActivityViewModel.getUserNameLiveData().observe(this){name ->
            Logger.v("Check here2 !!!")
            userName.text = Editable.Factory.getInstance().newEditable(name)
        }


        // 아이템 추가
        insertBtn.setOnClickListener(View.OnClickListener {
            mainActivityViewModel.insertData(userName.text.toString())
        })

        mainActivityViewModel.getAllUsers().observe(this, Observer { list ->
            list?.let {
                Logger.v("Check here1 !!!")
                val tmp : List<User> = it

                //UserAdapter(tmp,listener)
                recyclerView.adapter = UserAdapter(tmp,listener)

//                tmp.forEach{i ->
//                    Logger.v("${i.name}")
//                }

            }
        })




        //todo : 다른 예제에서 봤던 데이터를 불러오는 방법
//        mainActivityViewModel.getUserLiveData().observe(this, Observer { list ->
//            list?.let {
//                val tmp : List<User> = it
//                Logger.v(tmp.get(1).name)
//            }
//        })





    }
}