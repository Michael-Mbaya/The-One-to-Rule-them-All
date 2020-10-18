package com.example.lotrcharacters.network;

import com.example.lotrcharacters.models.MyPreciousResponse;
import com.example.lotrcharacters.models.MyPreciousResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LotrAPI {

    @GET("character")
    Call<MyPreciousResponse> getBeers(
//            @Query("abv_gt") Double alcoholByVolumeGreaterThan
//            sort=name:asc
//            limit
    );
//
//    @GET("v2/beers")
//    Call<BrewsResponse> getLesserBeers(
////            @Query("abv_lt") Double alcoholByVolumelessThan
//    );

}
