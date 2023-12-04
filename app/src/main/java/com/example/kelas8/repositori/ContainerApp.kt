package com.example.kelas8.repositori

import android.content.Context
import com.example.kelas8.data.SiswaDao

interface ContainerApp{
    val repositoriSiswa : RepositoriSiswa
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(SiswaDao.DatabaseSiswa.getDatabase(context).siswaDao())
    }

}