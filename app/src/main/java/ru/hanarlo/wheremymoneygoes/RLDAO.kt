package ru.hanarlo.wheremymoneygoes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RLDAO {
    @Query("SELECT * FROM itemRL")
    fun getAll(): LiveData<List<itemRL>>

    @Insert
    fun insertAll(vararg babos: itemRL)

    @Delete
    suspend fun delete(babos: itemRL)

    @Query("DELETE FROM itemRL")
    suspend fun deleteAllData()


}