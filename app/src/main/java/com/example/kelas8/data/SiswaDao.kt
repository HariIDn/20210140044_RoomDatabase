package com.example.kelas8.data

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow



@Dao
interface SiswaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(siswa: Siswa)

    @Update
    suspend fun update(siswa: Siswa)

    @Delete
    suspend fun delete(siswa: Siswa)

    @Query("SELECT * from tblSiswa WHERE Id = :id")
    fun getSiswa(id: Int): Flow<Siswa>

    @Query("SELECT * from tblSiswa ORDER BY name ASC")
    fun getAllSiswa(): Flow<List<Siswa>>

    @Database(entities = [Siswa::class], version = 1, exportSchema = false)
    abstract class DatabaseSiswa : RoomDatabase(){
        abstract fun  siswaDao() : SiswaDao

        companion object{
            @Volatile
            private var Instance: DatabaseSiswa? = null

            fun getDatabase(context: Context): DatabaseSiswa{
                return (Instance?: synchronized(this){
                    Room.databaseBuilder(context,
                        DatabaseSiswa::class.java,
                    "siswa_database")
                        .build().also{ Instance=it}
                })
            }
        }
    }
}