package com.works.adapters

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.works.R
import com.works.entities.Note

class NoteItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    fun bindItem(itemModel: Note) {
        val r_title = itemView.findViewById<TextView>(R.id.nr_txtTitle)
        val r_detail = itemView.findViewById<TextView>(R.id.nr_txtDetail)
        r_title.text = itemModel.title
        r_detail.text = itemModel.content

        /*
        itemView.setOnClickListener {
            Toast.makeText(itemView.context, itemModel.title, Toast.LENGTH_SHORT).show()
        }
         */

    }

}