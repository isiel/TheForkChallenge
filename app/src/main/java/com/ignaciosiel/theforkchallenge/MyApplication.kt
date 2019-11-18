package com.ignaciosiel.theforkchallenge

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}