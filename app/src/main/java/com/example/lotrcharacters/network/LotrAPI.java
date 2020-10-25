package com.example.lotrcharacters.network;

import com.example.lotrcharacters.models.MyPreciousResponse;
import com.example.lotrcharacters.models.MyPreciousResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LotrAPI {

    @GET("character")
    Call<MyPreciousResponse> getCharacters(
            @Query("limit") String limit
//            @Query("sort") String sorting
//            sort=name:asc
//            limit
    );

}
