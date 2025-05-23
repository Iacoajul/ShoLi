package com.example.sholi


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyListAdapter (
    private val items : MutableList<ListItem>, //i have a list and the list has a name
    val listener : DeleteListener // i also have a funny delete mechanic
) : RecyclerView.Adapter<MyListAdapter.ListViewHolder>() { //i implement adapters for recyvlerViews and take my own viewholder for that

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) { //viewholder als innere klasse implementiert viewholder
        val itemTextView: TextView = itemView.findViewById(R.id.tvItemText) //finde die views und speichere sie für zugriff
        val deleteButton : Button = itemView.findViewById<Button>(R.id.btKillItem)

        init {
            deleteButton.setOnClickListener { //der delete button hat eine onclick funktion
                listener.onDeleteClick(adapterPosition) //diese sagt der mainActivity, dass bitte das Item gelöscht werden soll
            }
        }
    }

    fun addItem(listItem: ListItem) { //addItem listfunction halt...
        items.add(listItem)
        notifyItemInserted(items.size -1) //update the view so stuff is properly displayed
    }

    fun deleteItem(position: Int) { //reverse obere funktion
        items.removeAt(position)
        notifyItemRemoved(position)
    }
//hier beginnen die viewholder interface funktionen
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder { //oncreate...
        return ListViewHolder( //be a viewholder for lists
            LayoutInflater.from(parent.context).inflate( //link to parent view
                R.layout.list_items, //display my list items
                parent, //i dont know
                false //be dynamic and not bound to root of app
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) { //what is your purpose?
        val curItem = items[position] //take list items
        holder.itemTextView.text = curItem.title //display them in the recyclerView
    }

    override fun getItemCount(): Int { //der name ist programm
    return items.size
    }
}