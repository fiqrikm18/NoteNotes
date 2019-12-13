package com.idts.notenotes.libs;

import com.idts.notenotes.models.LoginModel;
import com.idts.notenotes.models.NoteData;
import com.idts.notenotes.models.NotesItem;
import com.idts.notenotes.models.RegisterModel;
import com.idts.notenotes.models.UserLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiRequest {
    @POST("/api/users/auth")
    Call<UserLogin> auth(@Body LoginModel login);

    @POST("api/users/register")
    Call<UserLogin> register(@Body RegisterModel registerData);

    @POST("api/notes")
    Call<NoteData> createNote(@Body NotesItem note);

    @GET("api/notes/all/users/{id}")
    Call<NoteData> getNotes(@Path("id") int userId);

    @DELETE("/api/notes/change/{note}/users/{user}")
    Call<NoteData> deleteNote(@Path("note") int noteId, @Path("user") int userId);
}
