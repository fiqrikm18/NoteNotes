package com.idts.notenotes.models;

public class NotesItem{
	private String createdAt;
	private int id;
	private String title;
	private String body;
	private int userId;
	private boolean isComplete;
	private String updatedAt;

	public NotesItem(String createdAt, int id, String title, String body, int userId, boolean isComplete, String updatedAt) {
		this.createdAt = createdAt;
		this.id = id;
		this.title = title;
		this.body = body;
		this.userId = userId;
		this.isComplete = isComplete;
		this.updatedAt = updatedAt;
	}

	public NotesItem() {
	}

	public NotesItem(String title, String body, int userId) {
		this.title = title;
		this.body = body;
		this.userId = userId;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setIsComplete(boolean isComplete){
		this.isComplete = isComplete;
	}

	public boolean isIsComplete(){
		return isComplete;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"NotesItem{" + 
			"createdAt = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",body = '" + body + '\'' + 
			",userId = '" + userId + '\'' + 
			",isComplete = '" + isComplete + '\'' + 
			",updatedAt = '" + updatedAt + '\'' + 
			"}";
		}
}
