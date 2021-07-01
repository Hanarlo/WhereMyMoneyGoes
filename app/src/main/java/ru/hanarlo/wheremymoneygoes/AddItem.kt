package ru.hanarlo.wheremymoneygoes

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.hanarlo.wheremymoneygoes.RVandDBforCOSTS.DataViewModelCosts
import java.lang.StringBuilder


class AddItem : AppCompatActivity() {
    lateinit var mDataViewModel:DataViewModel
    lateinit var mDataViewModelCosts: DataViewModelCosts
    lateinit var color: String
    lateinit var whereET: EditText
    lateinit var WhereET: String
    lateinit var HowMuchET: EditText
    lateinit var howmuchET: String
    lateinit var whenTE: EditText
    lateinit var WhenET: String
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
                supportActionBar?.title = "Создать запись"
                val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val button = findViewById<Button>(R.id.BtnAddBabos)
        val buttonloose = findViewById<Button>(R.id.btnloose)
        whereET = findViewById<EditText>(R.id.TEWhere) as EditText
        HowMuchET = findViewById<EditText>(R.id.TeHowMuch) as EditText
        whenTE = findViewById<EditText>(R.id.TeWhen) as EditText

        mDataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        mDataViewModelCosts = ViewModelProvider(this).get(DataViewModelCosts::class.java)


        button.setOnClickListener {
            insertdataProfit()
        }
        buttonloose.setOnClickListener {
            insertdataLooses()
        }
    }

    private fun insertdataProfit(){
        WhereET = whereET.text.toString()
        howmuchET = HowMuchET.text.toString()
        WhenET = whenTE.text.toString()
        if (inputCheck(WhereET,howmuchET,WhenET)){
        val data = itemCostsRL(0,WhereET,howmuchET,WhenET,"#44944A")
        mDataViewModelCosts.addItem(data)
            val intent = Intent(this, MainActivity :: class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Добавлено!", Toast.LENGTH_LONG).show()
        }

    }
    private fun insertdataLooses(){
        WhereET = whereET.text.toString()
        howmuchET = HowMuchET.text.toString()
        WhenET = whenTE.text.toString()
        if (inputCheck(WhereET,howmuchET,WhenET)){
            val data = itemRL(0,WhereET,howmuchET,WhenET,"#FF2400")
            mDataViewModel.addItem(data)
            val intent = Intent(this, MainActivity :: class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Добавлено!", Toast.LENGTH_LONG).show()
        }

    }



    private fun inputCheck(where:String,HowMuch:String,whenTE:String): Boolean{
        return !(TextUtils.isEmpty(where) && TextUtils.isEmpty(HowMuch) && TextUtils.isEmpty(whenTE))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }





}