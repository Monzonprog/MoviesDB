package com.jmonzon.offlinemovies.app

import android.app.Application
import android.content.Context

abstract class MyApp : Application() {
    companion object {
        private lateinit var instance: Context
        fun setContext(context: Context) {
            instance = context}
        fun getContext () :Context {return instance}

    }

    override fun onCreate() {
        instance = this;
        super.onCreate()
    }




}