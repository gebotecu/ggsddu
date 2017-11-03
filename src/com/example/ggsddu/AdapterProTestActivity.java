package com.example.ggsddu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ggsddu.adapter.AdapterPro;
import com.example.ggsddu.bean.ViewHolderTestListViewBean;
import com.example.ggsddu.utils.BitmapCache;
import com.example.ggsddu.utils.JsonHelper;
import com.example.ggsddu.utils.ViewHolder;

public class AdapterProTestActivity extends Activity {

	private ListView mListView;
	private static final String PATH = "http://epg.newtv2.ottcn.com:8080/newtv-2.0-epg/epg/programSeries/getRelationRecommend.json10?icntvId=011234567000005&userId=&programSeriesId=2974879&prefectureId=null&pageNumber=1&pageSize=1000";
	private static final String PATH_POST = "http://epg.newtv2.ottcn.com:8080/newtv-2.0-epg/epg/programSeries/getRelationRecommend.json10";
	
	protected static final String TAG = "AdapterProTestActivity";
	private RequestQueue mRequestQueue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter_pro_test_activity);

		initView();
		initData();
	}

	private void initView() {

		mListView = (ListView) findViewById(R.id.adapter_pro_listview);
		
	}

	private void initData() {
		mRequestQueue = Volley.newRequestQueue(this);
//		ViewHolderTestAsyncTask viewHolderTestAsyncTask = new ViewHolderTestAsyncTask();
//		viewHolderTestAsyncTask.execute(PATH);
		downLoadJsonWithVolly(PATH);
	}

	class ViewHolderTestAsyncTask extends
			AsyncTask<String, Void, List<ViewHolderTestListViewBean>> {

		@Override
		protected List<ViewHolderTestListViewBean> doInBackground(
				String... params) {
			String jsonString = downLoadJson(params[0]);
			Map<String, Object> map = JsonHelper.getInstance()
					.parseViewHolderTestInfo(jsonString);
			return (List<ViewHolderTestListViewBean>) map.get("items");
		}


		@Override
		protected void onPostExecute(List<ViewHolderTestListViewBean> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result != null && result.size() > 0) {

//				mViewHolderTestAdapter = new ViewHolderTestAdapter(
//						ViewHolderTestActivity.this, result);
				mListView.setAdapter(new MyAdapter(AdapterProTestActivity.this, result, R.layout.viewholder_test_item));
			}
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}
	}

	private void downLoadJsonWithVolly(String string) {
		Log.i(TAG, "downLoadJsonWithVolly");
		StringRequest stringRequest = new StringRequest(Method.GET, string, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.i(TAG, "volly---onResponse");
				Map<String, Object> map = JsonHelper.getInstance().parseViewHolderTestInfo(response);
				List<ViewHolderTestListViewBean> list = (List<ViewHolderTestListViewBean>) map.get("items");
				mListView.setAdapter(new MyAdapter(AdapterProTestActivity.this, list, R.layout.viewholder_test_item));
			}
		},new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i(TAG, "volly---onErrorResponse---"+error.toString());
			}
		})
		//post请求
//		{
//			@Override
//			protected Map<String, String> getParams() throws AuthFailureError {
//				//icntvId=011234567000005&userId=&programSeriesId=2974879&prefectureId=null&pageNumber=1&pageSize=1000
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("icntvId", "011234567000005");
//				map.put("userId", "");
//				map.put("programSeriesId", "2974879");
//				map.put("pageNumber", "1");
//				map.put("pageSize", "1000");
//				return map;
//			}
//		}
	;
		stringRequest.setTag("aaa");
		mRequestQueue.add(stringRequest);
	}
	
	class MyAdapter extends AdapterPro<ViewHolderTestListViewBean>{
		private ImageLoader imageLoader;
		private List<ViewHolderTestListViewBean> datas;
		public MyAdapter(Context context,
				List<ViewHolderTestListViewBean> datas, int layoutId) {
			super(context, datas, layoutId);
			this.datas = datas;
			imageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
		}

		@Override
		public void convert(int position, final ViewHolder viewHolder) {
			ViewHolderTestListViewBean bean = datas.get(position);
			viewHolder.setText(R.id.viewholder_test_item_textview,this.datas.get(position).getName() );
//			ImageRequest imageRequest = new ImageRequest(bean.getV_picurl(), new Listener<Bitmap>() {
//
//				@Override
//				public void onResponse(Bitmap response) {
//					ImageView imageView = viewHolder.getView(R.id.viewholder_test_item_imageview);
//					imageView.setImageBitmap(response);
//				}
//			}, 0, 0, Config.RGB_565, new Response.ErrorListener() {
//
//				@Override
//				public void onErrorResponse(VolleyError error) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//			imageRequest.setTag("bbb");
//			mRequestQueue.add(imageRequest);
			
			ImageListener imageListener = imageLoader.getImageListener((ImageView) viewHolder.getView(R.id.viewholder_test_item_imageview), R.drawable.ic_launcher, R.drawable.ic_launcher);
		
			imageLoader.get(bean.getV_picurl(), imageListener);
		}
		
	}
	public String downLoadJson(String string) {
		String jsonString = null;
		HttpGet httpGet = new HttpGet(string);
		HttpClient httpClient = new DefaultHttpClient();

		// 发送请求
		try {

			HttpResponse response = httpClient.execute(httpGet);

			HttpEntity httpEntity = response.getEntity();

			InputStream inputStream = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while (null != (line = reader.readLine())) {
				result += line;

			}
			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mRequestQueue.cancelAll("aaa");
		mRequestQueue.cancelAll("bbb");
	}
}
