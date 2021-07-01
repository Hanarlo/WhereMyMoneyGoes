package ru.hanarlo.wheremymoneygoes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(itemRL::class), version = 4
)
abstract class RLDB : RoomDatabase() {
    abstract fun babosDao(): RLDAO
companion object{
    @Volatile
    private var INSTANCE: RLDB? = null

    fun getDatabase(context: Context): RLDB{
        val teminstance = INSTANCE
        if (teminstance != null){
            return teminstance
        }
        synchronized(this){
            val instance = Room.databaseBuilder(context.applicationContext, RLDB::class.java, "itemRL").fallbackToDestructiveMigration().build()
            INSTANCE = instance
            return instance
        }
    }
}


}
