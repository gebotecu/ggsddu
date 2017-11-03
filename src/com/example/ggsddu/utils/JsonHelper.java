package com.example.ggsddu.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.ggsddu.bean.ViewHolderTestListViewBean;




public class JsonHelper {
	private JsonHelper() {
	}

	private static JsonHelper mInstance;
	private static final String TAG = "JsonHelper";
	private final String SPACE_POSITION_ITEM_SET_TAG = "-1";
	private static final String CODE_SUCCESS = "0000";

	public static JsonHelper getInstance() {
		if (mInstance == null) {
			synchronized (JsonHelper.class) {
				if (mInstance == null) {
					mInstance = new JsonHelper();
				}
			}
		}
		return mInstance;
	}

	
	/**
	 * 解析详情页相关推荐信�?
	 * 
	 * @param json
	 * @return
	 */
	public Map<String, Object> parseViewHolderTestInfo(String json) {
		if(json==null || json.equals("")){
			return null;
		}
		Map<String, Object> map = null;
		String message = null;
		String statusCode = null;
		try {
			JSONObject infoJson = new JSONObject(json);
			if (map == null) {
				map = new HashMap<>();
			}

			statusCode = infoJson.optString("statusCode");
			map.put("status_code", statusCode);

			if (!CODE_SUCCESS.equals(statusCode)) {
				message = infoJson.optString("message");
				map.put("message", message);
				return map;
			}

			JSONObject responseObj = infoJson.optJSONObject("response");
			int count = responseObj.optInt("count");
			map.put("count", count);
			int totalCount = responseObj.optInt("totalCount");
			map.put("totalCount", totalCount);
			JSONArray array;
			JSONObject object;
			List<ViewHolderTestListViewBean> viewHolderTestListViewBeans= null;
			ViewHolderTestListViewBean info = null;
			
            
			array = responseObj.optJSONArray("items");
			if(array!=null){
				
				int size = array.length();
				for (int i = 0; i < size; ++i) {
					object = array.optJSONObject(i);
					info = new ViewHolderTestListViewBean();
					info.setId(object.optString("id"));
					info.setName(object.optString("name"));
					info.setH_picurl(object.optString("hPicurl"));
					info.setV_picurl(object.optString("vPicurl"));
					info.setGrade(object.optString("grade"));
					info.setObjectType(object.optString("objectType"));
					info.setPlayUrl(object.optString("playUrl"));
					
					if (viewHolderTestListViewBeans == null) {
						viewHolderTestListViewBeans = new ArrayList<ViewHolderTestListViewBean>();
					}
					
					viewHolderTestListViewBeans.add(info);
				}
				map.put("items", viewHolderTestListViewBeans);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	    return map;
	}
	
	
}
			
		
