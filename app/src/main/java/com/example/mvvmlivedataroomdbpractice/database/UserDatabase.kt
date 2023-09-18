package com.example.mvvmlivedataroomdbpractice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmlivedataroomdbpractice.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getEventsDao(): UserDao

    companion object {
//        @Volatile
//        private var instance2: UserDatabase? = null
//
//        fun getDatabase(context: Context): UserDatabase {
//            return instance2 ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    UserDatabase::class.java,
//                    "user_db"
//                ).build()
//                instance2 = instance
//                // return instance
//                instance
//            }
//        }

        fun getDatabase(context: Context): UserDatabase {
            //다른 메소드가 접근하는 것을 허용하지 않게끔 lock 시켜주고 하나의 스레드만 접근할 수 있게끔 해준다.
            synchronized(UserDatabase::class) {
                val instance = Room.databaseBuilder(
                    context, UserDatabase::class.java, "user_db"
                ).build()

                return instance
            }
        }

    }
}