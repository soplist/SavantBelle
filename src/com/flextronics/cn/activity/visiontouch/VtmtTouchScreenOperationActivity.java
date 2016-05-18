package com.flextronics.cn.activity.visiontouch;

import com.flextronics.cn.util.Constants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class VtmtTouchScreenOperationActivity extends Activity {
	
	private int presentType;
	private Bundle bundle ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		bundle = this.getIntent().getExtras();
		presentType = bundle.getInt(Constants.VisioTouchMemoryTrainingUIParameter.PRESENT_TYPE);
		if (presentType == Constants.VisioTouchMemoryTrainingUIParameter.SAMPLE_LOCATION) {
			Intent intent = new Intent(this, VtmtTouchScreenSampleLocationOrderActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			this.finish();		
		}else if (presentType == Constants.VisioTouchMemoryTrainingUIParameter.SAMPLE_ELEMENT) {
			Intent intent = new Intent(this, VtmtTouchScreenSampleElementOrderActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			this.finish();		
		}else {
			/*new AlertDialog.Builder(this)
				.create().setMessage(message)*/
		}
	}
}
