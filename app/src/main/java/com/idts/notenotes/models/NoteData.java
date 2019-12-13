package com.idts.notenotes.models;

import java.util.List;

public class NoteData{
	private List<NotesItem> notes;
	private String message;
	private boolean isSuccess;

	public void setNotes(List<NotesItem> notes){
		this.notes = notes;
	}

	public List<NotesItem> getNotes(){
		return notes;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setIsSuccess(boolean isSuccess){
		this.isSuccess = isSuccess;
	}

	public boolean isIsSuccess(){
		return isSuccess;
	}

	@Override
 	public String toString(){
		return 
			"NoteData{" + 
			"notes = '" + notes + '\'' + 
			",message = '" + message + '\'' + 
			",isSuccess = '" + isSuccess + '\'' + 
			"}";
		}
}