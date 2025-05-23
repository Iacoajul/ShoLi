package com.example.sholi

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sholi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DeleteListener {

private lateinit var myListAdapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myListAdapter = MyListAdapter(mutableListOf(),this)
        val rvLI = findViewById<RecyclerView>(R.id.rvListItems)
        val aBtn = findViewById<Button>(R.id.btAdd)
        val eText = findViewById<EditText>(R.id.etInField)

        rvLI.adapter = myListAdapter
        rvLI.layoutManager = LinearLayoutManager(this)

        aBtn.setOnClickListener {
            val liTitle = eText.text.toString()
            if(liTitle.isNotEmpty()) {
                val liElem = ListItem(liTitle)
                myListAdapter.addItem(liElem)
                eText.text.clear()
            }
        }


        }

    override fun onDeleteClick(position: Int) {
        myListAdapter.deleteItem(position)
    }

}

