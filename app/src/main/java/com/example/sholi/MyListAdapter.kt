package com.example.sholi


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyListAdapter (
    private val items : MutableList<ListItem>,
     val listener : DeleteListener
) : RecyclerView.Adapter<MyListAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.tvItemText)
        val deleteButton : Button = itemView.findViewById<Button>(R.id.btKillItem)

        init {
            deleteButton.setOnClickListener {
                listener.onDeleteClick(adapterPosition)
            }
        }
    }

    fun addItem(listItem: ListItem) {
        items.add(listItem)
        notifyItemInserted(items.size -1)
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_items,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val curItem = items[position]
        holder.itemTextView.text = curItem.title
    }

    override fun getItemCount(): Int {
    return items.size
    }
}