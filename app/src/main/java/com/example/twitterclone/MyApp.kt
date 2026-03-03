package com.example.twitterclone

import android.app.Application
import com.example.twitterclone.api.AppwriteConfig

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicializar Appwrite
        AppwriteConfig.init(this)
    }
}
