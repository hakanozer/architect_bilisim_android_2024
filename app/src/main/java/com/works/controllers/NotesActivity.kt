package com.works.controllers

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.works.R
import com.works.adapters.NoteAdapter
import com.works.configs.AppDatabase
import com.works.dao.NoteDao
import com.works.entities.Note

class NotesActivity : AppCompatActivity() {

    lateinit var n_txtTitle: EditText
    lateinit var n_txtDetail: EditText
    lateinit var n_btnSave: Button
    lateinit var noteList: RecyclerView

    lateinit var db: AppDatabase
    lateinit var noteDao: NoteDao
    var notes: List<Note> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        db = Room.databaseBuilder(this, AppDatabase::class.java, "product.db").allowMainThreadQueries().build()
        noteDao = db.noteDao()

        n_txtTitle = findViewById(R.id.n_txtTitle)
        n_txtDetail = findViewById(R.id.n_txtDetail)
        n_btnSave = findViewById(R.id.n_btnSave)
        noteList = findViewById(R.id.noteLis)
        noteList.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)

        getAllNote()
        n_btnSave.setOnClickListener {
            noteSave()
        }
    }


    private fun noteSave() {
        val title = n_txtTitle.text.toString().trim()
        val detail = n_txtDetail.text.toString().trim()
        if (title.isEmpty() || detail.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurunuz!", Toast.LENGTH_SHORT).show()
        }else {
            val note = Note(title = title, content = detail)
            val id = noteDao.insert(note)
            if (id > 0) {
                Toast.makeText(this, "EKLEME BASARILI", Toast.LENGTH_SHORT).show()
                n_txtTitle.setText("")
                n_txtDetail.setText("")
                n_txtTitle.requestFocus()
                getAllNote()
            } else {
                Toast.makeText(this, "EKLEME BASARISIZ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getAllNote() {
        notes = noteDao.getAll()
        val adapter = NoteAdapter(notes)
        noteList.adapter = adapter
    }


}