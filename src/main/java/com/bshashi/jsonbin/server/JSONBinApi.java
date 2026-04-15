package com.bshashi.jsonbin.server;

import retrofit2.Call;
import retrofit2.http.*;

public interface JSONBinApi {
	
	@POST("v3/b")
	Call<Object> createBin(@Header("X-Master-Key") String key, @Body Object body);
	
	@GET("v3/b/{id}/latest")
	Call<Object> getBin(@Header("X-Master-Key") String key, @Path("id") String id);
	
	@PUT("v3/b/{id}")
	Call<Object> updateBin(@Header("X-Master-Key") String key, @Path("id") String id, @Body Object body);
	
	@DELETE("v3/b/{id}")
	Call<Object> deleteBin(@Header("X-Master-Key") String key, @Path("id") String id);
}

