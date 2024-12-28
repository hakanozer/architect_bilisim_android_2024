package com.works.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.works.R
import com.works.entities.Note

class NoteAdapter(val items: List<Note>) : RecyclerView.Adapter<NoteItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemHolder {
        val itemHolder = NoteItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_row, parent, false))
        itemHolder.itemView.setOnClickListener {
            val item = items[itemHolder.adapterPosition]
            Toast.makeText(parent.context, item.title, Toast.LENGTH_SHORT).show()
        }
        return itemHolder
    }

    override fun onBindViewHolder(holder: NoteItemHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}