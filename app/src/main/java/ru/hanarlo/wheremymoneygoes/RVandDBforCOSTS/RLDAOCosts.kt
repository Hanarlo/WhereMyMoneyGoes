package ru.hanarlo.wheremymoneygoes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RLDAOCosts {
    @Query("SELECT * FROM itemCostsRL")
    fun getAll(): LiveData<List<itemCostsRL>>

    @Insert
    fun insertAll(vararg babos: itemCostsRL)

    @Delete
    suspend fun delete(babos: itemCostsRL)

    @Query("DELETE FROM itemCostsRL")
    suspend fun deleteAllData()



}