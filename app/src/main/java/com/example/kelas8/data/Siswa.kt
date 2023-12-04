package com.example.kelas8.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblSiswa")
data class Siswa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val alamat: String,
    val telepon: String
)
