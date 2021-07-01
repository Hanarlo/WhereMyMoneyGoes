package ru.hanarlo.wheremymoneygoes

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class itemRL(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo
    val WhereText: String,
    @ColumnInfo
    val HowMuchText: String,
    @ColumnInfo
    val calendar: String,
    @ColumnInfo
    val BGcolor: String)