package com.example.cruditemapp

import android.app.Application
import com.google.firebase.FirebaseApp

class MyFirebaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}