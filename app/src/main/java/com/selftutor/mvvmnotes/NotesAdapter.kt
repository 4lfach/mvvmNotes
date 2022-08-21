package com.selftutor.mvvmnotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.selftutor.mvvmnotes.database.Note
import com.selftutor.mvvmnotes.databinding.NoteItemBinding

class NotesAdapter(
	val context : Context,
	private val noteClickDeleteInterface: NoteClickDeleteInterface,
	private val noteClickInterface: NoteClickInterface
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

	private val notes = ArrayList<Note>()

	class ViewHolder(val binding : NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = NoteItemBinding.inflate(inflater, parent, false)

		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		with(holder.binding){
			tvNote.text = notes.get(position).noteTitle
			tvDate.text = "Last Updated: ${notes.get(position).timeStamp}"

			ivDelete.setOnClickListener {
				noteClickDeleteInterface.onDeleteIconClick(notes.get(position))
			}

			root.setOnClickListener{
				noteClickInterface.onNoteClick(notes.get(position))
			}
		}
	}

	override fun getItemCount(): Int = notes.size

	fun updateList(newList : List<Note>){
		notes.clear()
		notes.addAll(newList)

		notifyDataSetChanged()
	}

}

interface NoteClickDeleteInterface {
	// creating a method for click
	// action on delete image view.
	fun onDeleteIconClick(note: Note)
}

interface NoteClickInterface {
	// creating a method for click action
	// on recycler view item for updating it.
	fun onNoteClick(note: Note)
}