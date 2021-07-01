package ru.hanarlo.wheremymoneygoes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(application: Application): AndroidViewModel(application) {
     val readAllData: LiveData<List<itemRL>>
    private val repository: itemRepository

    init {
        val itemDAO = RLDB.getDatabase(application).babosDao()
        repository = itemRepository(itemDAO)
        readAllData = repository.readAllData}

    fun addItem(itemRL: itemRL){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(itemRL)
        }
    }

    fun deleteData(itemRL: itemRL){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(itemRL)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }



}