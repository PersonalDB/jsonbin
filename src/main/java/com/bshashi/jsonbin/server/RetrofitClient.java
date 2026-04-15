package com.bshashi.jsonbin.server;

import okhttp3.OkHttpClient;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;

public class RetrofitClient {
	
	private static Retrofit retrofit = null;
	
	public static Retrofit getClient() {
		if (retrofit == null) {
			OkHttpClient client = new OkHttpClient.Builder().build();
			
			retrofit = new Retrofit.Builder()
			.baseUrl("https://api.jsonbin.io/")
			.client(client)
			.addConverterFactory(GsonConverterFactory.create())
			.build();
		}
		return retrofit;
	}
}
