package com.sample

import android.app.Application
import com.sample.room.AppRoomDatabase

/*
* This is the application class of NoteDlite
* */
class MyApplication : Application() {


    companion object {
        private lateinit var myInstance: MyApplication
        fun getInstance(): MyApplication = myInstance
        private lateinit var appRoomDB: AppRoomDatabase
        fun getAppRoomDB(): AppRoomDatabase = appRoomDB
    }

    override fun onCreate() {
        super.onCreate()

        myInstance = this

        appRoomDB = AppRoomDatabase.getInstance(myInstance)
    }


}