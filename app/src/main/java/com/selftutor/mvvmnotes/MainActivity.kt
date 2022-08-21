package com.selftutor.mvvmnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.selftutor.mvvmnotes.database.Note
import com.selftutor.mvvmnotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface, NoteClickInterface {
	private lateinit var viewModel: NotesViewModel
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val notesAdapter = NotesAdapter(this, this, this)
		binding.rvNotes.layoutManager = LinearLayoutManager(this)
		binding.rvNotes.adapter = notesAdapter

		viewModel = ViewModelProvider(
			this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
		).get(NotesViewModel::class.java)

		viewModel.allNotes.observe(this, Observer{
			it.let {
				notesAdapter.updateList(it)
			}
		})

		binding.fab.setOnClickListener{
			val intent = Intent(this@MainActivity, AddOrEditActivity::class.java )
			startActivity(intent)
			this.finish()
		}
	}

	override fun onDeleteIconClick(note: Note) {
		viewModel.deleteNote(note)

		Toast.makeText(this, "Note ${note.noteTitle} was deleted", Toast.LENGTH_SHORT).show()
	}

	override fun onNoteClick(note: Note) {
		val intent = Intent(this@MainActivity, AddOrEditActivity::class.java)
		intent.putExtra(Note.ARG_TYPE, "Edit")
		intent.putExtra(Note.ARG_TITLE, note.noteTitle)
		intent.putExtra(Note.ARG_DESC, note.description)
		startActivity(intent)

		this.finish()
	}
}