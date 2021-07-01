package ru.hanarlo.wheremymoneygoes.RVandDBforCOSTS

import ru.hanarlo.wheremymoneygoes.RLDAOCosts
import ru.hanarlo.wheremymoneygoes.itemCostsRL
import androidx.lifecycle.LiveData

class itemrepositurCosts(private val rldaoCosts: RLDAOCosts) {

    val readAllData: LiveData<List<itemCostsRL>> = rldaoCosts.getAll()

    suspend fun addData(data : itemCostsRL){
        rldaoCosts.insertAll(data)
    }

    suspend fun deleteData(data: itemCostsRL){
        rldaoCosts.delete(data)
    }

    suspend fun deleteAllData(){
        rldaoCosts.deleteAllData()
    }
}