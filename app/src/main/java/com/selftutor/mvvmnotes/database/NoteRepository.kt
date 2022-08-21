package com.selftutor.mvvmnotes.database

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {

	val allNotes = notesDao.getAllNotes()

	suspend fun insert(note: Note) {
		notesDao.insert(note)
	}

	suspend fun delete(note: Note) {
		notesDao.delete(note)
	}

	suspend fun update(note: Note) {
		notesDao.update(note)
	}

}