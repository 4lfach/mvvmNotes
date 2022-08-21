package com.selftutor.mvvmnotes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insert(note : Note)

	@Delete
	fun delete(note: Note)

	@Query("Select * from notesTable order by id ASC")
	fun getAllNotes() : LiveData<List<Note>>

	@Update
	fun update(note: Note)
}