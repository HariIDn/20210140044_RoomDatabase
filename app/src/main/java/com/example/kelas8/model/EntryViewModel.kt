package com.example.kelas8.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kelas8.data.Siswa
import com.example.kelas8.repositori.RepositoriSiswa

class EntryViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel(){

    //Berisi Status Siswa
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    //fungsi untuk validasi input
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean{
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank() && telepon.isNotBlank()
        }
    }
    fun updateUiState(detailSiswa: DetailSiswa = uiStateSiswa.detailSiswa){
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }
    //fungsi menyimpan data di dalam entry
    suspend fun saveSiswa(){
        if (validasiInput()){
            repositoriSiswa.insertSiswa(uiStateSiswa.detailSiswa.toSiswa())
        }
    }


}

//Status Ui untuk Siswa

data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telepon: String = ""
)
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    name = nama,
    alamat = alamat,
    telepon = telepon

)
fun Siswa.toUiStateSiswa(isEntryValid: Boolean = false):UIStateSiswa = UIStateSiswa(
    detailSiswa = this.toDetailSiswa(),
    isEntryValid = isEntryValid
)
fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = name,
    alamat = alamat,
    telepon = telepon
)