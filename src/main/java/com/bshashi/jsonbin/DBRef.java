package com.bshashi.jsonbin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.bshashi.jsonbin.server.*;

import java.util.Map;
import java.util.UUID;

public class DBRef {
	
	private FirebaseLikeDB db;  
	private String path;  
	
	public DBRef(FirebaseLikeDB db, String path) {  
		this.db = db;  
		this.path = path;  
	}  
	
	public void setValue(Object value) {  
		JSONBinHelper.getRaw(db.binId, new JSONBinHelper.ResultCallback<JsonElement>() {  
			@Override  
			public void onSuccess(JsonElement res) {  
				JsonObject record = res.getAsJsonObject().getAsJsonObject("record");  
				PathUtil.set(record, path, value);  
				JSONBinHelper.update(db.binId, record, new JSONBinHelper.ResultCallback<JsonElement>() {  
					@Override public void onSuccess(JsonElement data) {}  
					@Override public void onError(String error) {}  
				});  
			}  
			@Override  
			public void onError(String error) {}  
		});  
	}  
	
	public DBRef push() {  
		String key = UUID.randomUUID().toString().replace("-", "");  
		return new DBRef(db, path + "/" + key);  
	}  
	
	public void updateChildren(final Map<String, Object> map) {  
		JSONBinHelper.getRaw(db.binId, new JSONBinHelper.ResultCallback<JsonElement>() {  
			@Override  
			public void onSuccess(JsonElement res) {  
				JsonObject record = res.getAsJsonObject().getAsJsonObject("record");  
				for (String key : map.keySet()) {  
					PathUtil.set(record, path + "/" + key, map.get(key));  
				}  
				JSONBinHelper.update(db.binId, record, new JSONBinHelper.ResultCallback<JsonElement>() {  
					@Override public void onSuccess(JsonElement data) {}  
					@Override public void onError(String error) {}  
				});  
			}  
			@Override  
			public void onError(String error) {}  
		});  
	}  
	
	public void removeValue() {  
		JSONBinHelper.getRaw(db.binId, new JSONBinHelper.ResultCallback<JsonElement>() {  
			@Override  
			public void onSuccess(JsonElement res) {  
				JsonObject record = res.getAsJsonObject().getAsJsonObject("record");  
				PathUtil.remove(record, path);  
				JSONBinHelper.update(db.binId, record, new JSONBinHelper.ResultCallback<JsonElement>() {  
					@Override public void onSuccess(JsonElement data) {}  
					@Override public void onError(String error) {}  
				});  
			}  
			@Override  
			public void onError(String error) {}  
		});  
	}
	
}

