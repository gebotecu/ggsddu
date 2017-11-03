package com.example.ggsddu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;

public class BaiduMapTestActivity extends Activity {

	protected static final String TAG = "BaiduMapTestActivity";
	private TextureMapView mTextureMapView;
	private BaiduMap mBaiduMap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext()); 
		setContentView(R.layout.baidumap_test_activity);
		mTextureMapView = (TextureMapView) findViewById(R.id.bmapView);
		mBaiduMap = mTextureMapView.getMap();
		mBaiduMap.setTrafficEnabled(true);
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		mBaiduMap.setBaiduHeatMapEnabled(true);
		
//		addMaker();
		getMyLocation();
	}
	
	private void getMyLocation() {
//		// 开启定位图层  
//		mBaiduMap.setMyLocationEnabled(true);  
//		// 构造定位数据  
//		MyLocationData locData = new MyLocationData.Builder()  
//		    .accuracy(location.getRadius())  
//		    // 此处设置开发者获取到的方向信息，顺时针0-360  
//		    .direction(100).latitude(location.getLatitude())  
//		    .longitude(location.getLongitude()).build();  
//		// 设置定位数据  
//		mBaiduMap.setMyLocationData(locData);  
//		// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）  
//		mCurrentMarker = BitmapDescriptorFactory  
//		    .fromResource(R.drawable.icon_geo);  
//		MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);  
//		mBaiduMap.setMyLocationConfiguration();  
//		// 当不需要定位图层时关闭定位图层  
//		mBaiduMap.setMyLocationEnabled(false);
		
	}

	private void addMaker() {
		//定义Maker坐标点  
		LatLng point = new LatLng(39.963175, 116.400244);  
		//构建Marker图标  
		BitmapDescriptor bitmap = BitmapDescriptorFactory  
		    .fromResource(R.drawable.icon);  
		//构建MarkerOption，用于在地图上添加Marker  
		OverlayOptions option = new MarkerOptions()  
		    .position(point)  
		    .icon(bitmap)
		    .title("未来电视")
		    .draggable(true);  //设置手势拖拽 
		//在地图上添加Marker，并显示  
		mBaiduMap.addOverlay(option);
		
		//调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
		mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
		    public void onMarkerDrag(Marker marker) {
		    	//拖拽中
		        Log.i(TAG, "onMarkerDrag---marker"+marker.getTitle());
		    }
		    public void onMarkerDragEnd(Marker marker) {
		        //拖拽结束
		    	 Log.i(TAG, "onMarkerDragEnd---marker"+marker.getTitle());
		    }
		    public void onMarkerDragStart(Marker marker) {
		        //开始拖拽
		    	 Log.i(TAG, "onMarkerDragStart---marker"+marker.getTitle());
		    }
		});
	}

	@Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mTextureMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mTextureMapView.onResume();  
    }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
        mTextureMapView.onPause();  
    }  
    
}
