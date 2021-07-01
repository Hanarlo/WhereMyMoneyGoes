package ru.hanarlo.wheremymoneygoes.RVandDBforCOSTS




import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.hanarlo.wheremymoneygoes.*

class DataViewModelCosts(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<itemCostsRL>>
    private val repository: itemrepositurCosts

    init {
        val itemDAO = RLDBCosts.getDatabase(application).babosDaoCosts()
        repository = itemrepositurCosts(itemDAO)
        readAllData = repository.readAllData}

    fun addItem(itemCostsRL: itemCostsRL){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addData(itemCostsRL)
        }
    }

    fun deleteData(itemCostsRL: itemCostsRL){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(itemCostsRL)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }

}