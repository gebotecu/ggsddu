package com.example.ggsddu;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * Created by Tom on 2017/10/19.
 */

public class GaodeMapActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gaode_map_activity);
        MapView mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap aMap = mapView.getMap();
    }
}
