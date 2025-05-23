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

class MainActivity : AppCompatActivity(), DeleteListener { //interfacing being an app and handling the delete function

private lateinit var myListAdapter: MyListAdapter //Adapter grundsätzlich einführen aber erst später belegen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main) //standardzeug?
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets //frag mich nicht... bestimmt iwas mit device size compat
        }
        myListAdapter = MyListAdapter(mutableListOf(),this) //die adapterklasse für die recyclerView belegen. nimmt eine mutable list und extenden diesen deleteListener (this)
        val rvLI = findViewById<RecyclerView>(R.id.rvListItems) //zugriff auf views als variable belegen
        val aBtn = findViewById<Button>(R.id.btAdd)
        val eText = findViewById<EditText>(R.id.etInField)

        rvLI.adapter = myListAdapter //adapterklasse der recyclerView zuweisen
        rvLI.layoutManager = LinearLayoutManager(this) //same for layoutmanager

        aBtn.setOnClickListener { //addButton funktion
            val liTitle = eText.text.toString() //nehme inhalt, stringifiziere und speichere
            if(liTitle.isNotEmpty()) { //keine leeren items adden
                val liElem = ListItem(liTitle) //Inhalt zu listItem machen
                myListAdapter.addItem(liElem) //ListItem der RV hinzufügen
                eText.text.clear() //textfeld leeren
            }
        }


        }

    override fun onDeleteClick(position: Int) { //deletefunktionsweiterleitung
        myListAdapter.deleteItem(position) //löschauftrag an den Adapter geben
    }

}

