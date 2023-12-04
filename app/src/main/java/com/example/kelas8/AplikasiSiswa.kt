package com.example.kelas8

import android.app.Application
import com.example.kelas8.repositori.ContainerApp
import com.example.kelas8.repositori.ContainerDataApp

class AplikasiSiswa: Application() {
    // Instance app container digunakan oleh kelas lainnya untuk dapat dependencies
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}