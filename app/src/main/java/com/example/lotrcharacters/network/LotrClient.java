package com.example.lotrcharacters.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.lotrcharacters.BuildConfig.LOTR_AUTH_KEY;
import static com.example.lotrcharacters.Constants.LOTR_BASE_URL;

public class LotrClient {

    private static Retrofit retrofit = null;

    public static LotrAPI getClient() {

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", LOTR_AUTH_KEY) //from Constants(which gets from gradle Build Files)
                                    .addHeader("Access-Control-Allow-Origin","*")
                                    .addHeader("Access-Control-Allow-Headers","Access-Control-Request-Headers")
                                    .addHeader("Access-Control-Allow-Methods","Access-Control-Request-Method")
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(LOTR_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(LotrAPI.class);
    }

}
