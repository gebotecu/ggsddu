package com.example.ggsddu;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ZXingTestActivity extends Activity implements OnClickListener{
	
	private EditText mEditText;
	private ImageView mImageView;
	private Button mButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zxing_test_activity);
		
		mEditText = (EditText) findViewById(R.id.zxing_edittext);
		mImageView = (ImageView) findViewById(R.id.zxing_imageview);
		mButton = (Button) findViewById(R.id.zxing_botton);
		mButton.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zxing_botton:
			String textString =mEditText.getText().toString();
			if(textString!=null){
			}
			break;

		default:
			break;
		}
		
	}
}
