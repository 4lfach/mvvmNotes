package com.selftutor.mvvmnotes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
class Note(@ColumnInfo(name = "title") val noteTitle: String,
		   @ColumnInfo(name = "description")val description: String,
		   @ColumnInfo(name = "timeStamp")val timeStamp : String){
	@PrimaryKey(autoGenerate = true) var id = 0

	companion object{
		const val ARG_TITLE = "title"
		const val ARG_DESC = "description"
		const val ARG_RAN_NUMBER = "ran_number"
		const val ARG_TYPE = "note_type"
		const val ARG_ID = "note_id"
 	}
}
