package com.example.cmpstudy.bookpedia

import android.app.Application
import com.example.cmpstudy.bookpedia.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}
