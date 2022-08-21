package com.selftutor.mvvmnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.selftutor.mvvmnotes.database.Note
import com.selftutor.mvvmnotes.databinding.ActivityAddOrEditBinding
import java.text.SimpleDateFormat
import java.util.*

class AddOrEditActivity : AppCompatActivity() {

	private lateinit var viewModel : NotesViewModel
	private lateinit var binding : ActivityAddOrEditBinding

	private var noteId = -1

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAddOrEditBinding.inflate(layoutInflater)
		setContentView(binding.root)

		viewModel = ViewModelProvider(
			this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
		).get(NotesViewModel::class.java)

		val noteType = intent.getStringExtra(Note.ARG_TYPE)
		if(noteType.equals(Note.ARG_TYPE)){

			val noteDescription = intent.getStringExtra(Note.ARG_DESC)
			val noteTitle = intent.getStringExtra(Note.ARG_TITLE)

			noteId = intent.getIntExtra(Note.ARG_RAN_NUMBER, -1)
			binding.btnSave.text = getString(R.string.btn_update_note)

			binding.etNoteTitle.setText(noteTitle)
			binding.etNoteDesc.setText(noteDescription)
		}else {
			binding.btnSave.text = getString(R.string.btn_save_note)
		}

		binding.btnSave.setOnClickListener {
			val noteTitle = binding.etNoteTitle.text.toString()
			val noteDescription = binding.etNoteDesc.text.toString()

			if (noteType.equals("Edit")) {
				if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
					val sdf = SimpleDateFormat(getString(R.string.date_format))
					val currentDateAndTime: String = sdf.format(Date())
					val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
					updatedNote.id = noteId
					viewModel.updateNote(updatedNote)
					Toast.makeText(this, getString(R.string.note_updated)
						, Toast.LENGTH_LONG).show()
				}
			} else {
				if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
					val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
					val currentDateAndTime: String = sdf.format(Date())

					viewModel.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
					Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
				}
			}
			// opening the new activity on below line
			startActivity(Intent(applicationContext, MainActivity::class.java))
			this.finish()
		}
	}
}