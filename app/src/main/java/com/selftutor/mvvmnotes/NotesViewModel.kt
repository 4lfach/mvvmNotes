package com.selftutor.mvvmnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.selftutor.mvvmnotes.database.Note
import com.selftutor.mvvmnotes.database.NoteDatabase
import com.selftutor.mvvmnotes.database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {

	val allNotes: LiveData<List<Note>>
	val repository: NoteRepository

	init{
		val dao = NoteDatabase.getDatabase(application.applicationContext).getNotesDao()
		repository = NoteRepository(dao)
		allNotes = repository.allNotes
	}

	fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
		repository.delete(note)
	}

	fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
		repository.insert(note)
	}

	fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
		repository.update(note)
	}
}