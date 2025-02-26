package com.example.lpg.android

import android.app.Application

class LpgApp : Application()  {
    override fun onCreate() {
        super.onCreate()
        net.sqlcipher.database.SQLiteDatabase.loadLibs(this)
    }
}