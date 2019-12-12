package com.idts.notenotes.models;

public class UserLogin{
	private UserData userData;
	private String message;
	private boolean isSuccess;
	private String token;

	public void setUserData(UserData userData){
		this.userData = userData;
	}

	public UserData getUserData(){
		return userData;
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

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"UserLogin{" + 
			"userData = '" + userData + '\'' + 
			",message = '" + message + '\'' + 
			",isSuccess = '" + isSuccess + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}
