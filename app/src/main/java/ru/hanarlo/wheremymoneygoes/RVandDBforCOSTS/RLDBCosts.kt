package ru.hanarlo.wheremymoneygoes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(itemCostsRL::class), version = 1)
abstract class RLDBCosts : RoomDatabase() {
    abstract fun babosDaoCosts(): RLDAOCosts
    companion object{
        @Volatile
        private var INSTANCE: RLDBCosts? = null

        fun getDatabase(context: Context): RLDBCosts{
            val teminstance = INSTANCE
            if (teminstance != null){
                return teminstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, RLDBCosts::class.java, "itemCostsRL").build()
                INSTANCE = instance
                return instance
            }
        }
    }


}