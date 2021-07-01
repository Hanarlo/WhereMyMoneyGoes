package ru.hanarlo.wheremymoneygoes

import androidx.lifecycle.LiveData

class itemRepository(private val rldao: RLDAO) {

    val readAllData: LiveData<List<itemRL>> = rldao.getAll()

    suspend fun addData(data : itemRL){
        rldao.insertAll(data)
    }

    suspend fun deleteData(data: itemRL){
        rldao.delete(data)
    }

    suspend fun deleteAllData(){
        rldao.deleteAllData()
    }

}