package com.bshashi.jsonbin.server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JSONBinHelper {
	
	public static final String API_KEY = "$2a$10$L8v.GTT.2Y4VqBDnEMDxpeb2RCWf/twU6Aiw385aVyHeMAOFh01F2";
	private static JSONBinApi api = RetrofitClient.getClient().create(JSONBinApi.class);
	private static Gson gson = new Gson();
	
	public interface ResultCallback<T> {
		void onSuccess(T data);
		void onError(String error);
	}
	
	public static void create(final Object data, final ResultCallback<JsonElement> callback) {
		Call<Object> call = api.createBin(API_KEY, data);
		call.enqueue(new Callback<Object>() {
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				if (response.isSuccessful() && response.body() != null) {
					callback.onSuccess(gson.toJsonTree(response.body()));
				} else {
					callback.onError("Error: " + response.code());
				}
			}
			
			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				callback.onError(t != null ? t.getMessage() : "Unknown error");
			}
		});
	}
	
	public static void getRaw(String binId, final ResultCallback<JsonElement> callback) {
		Call<Object> call = api.getBin(API_KEY, binId);
		call.enqueue(new Callback<Object>() {
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				if (response.isSuccessful() && response.body() != null) {
					callback.onSuccess(gson.toJsonTree(response.body()));
				} else {
					callback.onError("Error: " + response.code());
				}
			}
			
			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				callback.onError(t != null ? t.getMessage() : "Unknown error");
			}
		});
	}
	
	public static void update(String binId, final Object data, final ResultCallback<JsonElement> callback) {
		Call<Object> call = api.updateBin(API_KEY, binId, data);
		call.enqueue(new Callback<Object>() {
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				if (response.isSuccessful() && response.body() != null) {
					callback.onSuccess(gson.toJsonTree(response.body()));
				} else {
					callback.onError("Error: " + response.code());
				}
			}
			
			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				callback.onError(t != null ? t.getMessage() : "Unknown error");
			}
		});
	}
	
	public static void delete(String binId, final ResultCallback<JsonElement> callback) {
		Call<Object> call = api.deleteBin(API_KEY, binId);
		call.enqueue(new Callback<Object>() {
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				if (response.isSuccessful() && response.body() != null) {
					callback.onSuccess(gson.toJsonTree(response.body()));
				} else {
					callback.onError("Error: " + response.code());
				}
			}
			
			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				callback.onError(t != null ? t.getMessage() : "Unknown error");
			}
		});
	}
}
