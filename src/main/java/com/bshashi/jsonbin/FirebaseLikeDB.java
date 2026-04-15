package com.bshashi.jsonbin;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.bshashi.jsonbin.server.*;

import java.util.HashMap;
import java.util.Map;

public class FirebaseLikeDB {
	
	public String binId;
	private Handler handler = new Handler(Looper.getMainLooper());
	private Map<String, String> cache = new HashMap<>();
	
	public FirebaseLikeDB(String binId) {
		this.binId = binId;
	}
	
	public DBRef child(String path) {
		return new DBRef(this, path);
	}
	
	public void addValueEventListener(final String path, final ValueListener listener) {
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				JSONBinHelper.getRaw(binId, new JSONBinHelper.ResultCallback<JsonElement>() {
					@Override
					public void onSuccess(JsonElement json) {
						try {
							JsonObject record = json.getAsJsonObject().getAsJsonObject("record");
							JsonElement value = PathUtil.get(record, path);
							String newData = value != null ? value.toString() : "null";
							String oldData = cache.get(path);
							if (oldData == null || !oldData.equals(newData)) {
								cache.put(path, newData);
								listener.onDataChange(value);
							}
						} catch (Exception ignored) {}
					}
					@Override public void onError(String error) {}
				});
				handler.postDelayed(this, 2000); // polling
			}
		}, 0);
	}
}
