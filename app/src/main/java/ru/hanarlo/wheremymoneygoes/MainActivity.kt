package ru.hanarlo.wheremymoneygoes

import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.hanarlo.wheremymoneygoes.RVandDBforCOSTS.DataViewModelCosts

class MainActivity : AppCompatActivity() {
    private lateinit var mUserModel: DataViewModel
    private lateinit var mUserModel2: DataViewModelCosts
    private var mode = 0
    private lateinit var recycler_view: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view = findViewById<RecyclerView>(R.id.recyclerView)
        var adapter = RLadapter()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(false)

        mUserModel = ViewModelProvider(this).get(DataViewModel::class.java)
        mUserModel.readAllData.observe(this, Observer { data -> adapter.setData(data) })
        mUserModel2 = ViewModelProvider(this).get(DataViewModelCosts::class.java)

        supportActionBar?.title = "Убытки"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.delete_data_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_item){
            deleteItems()
            return true
        } else if(item.itemId == R.id.redo){
            switchMode()
            return true
        } else if (item.itemId == R.id.addMark){
            val intent = Intent(this, AddItem :: class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun switchMode() {
        if (mode == 0) {
            mode = 1
            supportActionBar?.setTitle("Прибыль")
            var adapter = RLadapterCosts()
            recycler_view.adapter = adapter
            recycler_view.layoutManager = LinearLayoutManager(this)
            recycler_view.setHasFixedSize(false)
            mUserModel2 = ViewModelProvider(this).get(DataViewModelCosts::class.java)
            mUserModel2.readAllData.observe(this, Observer { data -> adapter.setData(data) })


        } else if (mode == 1){
            mode = 0
            supportActionBar?.title = "Убытки"
            var adapter = RLadapter()
            recycler_view.adapter = adapter
            recycler_view.layoutManager = LinearLayoutManager(this)
            recycler_view.setHasFixedSize(false)
            mUserModel = ViewModelProvider(this).get(DataViewModel::class.java)
            mUserModel.readAllData.observe(this, Observer { data -> adapter.setData(data) })
        }
    }

    private fun deleteItems() {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Убытки"){_,_ ->
            mUserModel.deleteAllData()
            Toast.makeText(this, "Успешно удалено", Toast.LENGTH_LONG).show()

        }

        builder.setNegativeButton("Прибыль"){_,_ ->
            mUserModel2.deleteAllData()
            Toast.makeText(this, "Успешно удалено", Toast.LENGTH_LONG).show()

        }
        builder.setNeutralButton("Все"){_,_ ->
            mUserModel.deleteAllData()
            mUserModel2.deleteAllData()
        }
        builder.setTitle("Какие записи удалить?")
        builder.create().show()
    }


}