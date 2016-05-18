package com.flextronics.cn.util;

import com.flextronics.cn.activity.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class ActivityUtil {

	public static void finish(final Activity activity){
		new AlertDialog.Builder(activity)
			.setTitle(R.string.warning)
			.setMessage(R.string.internal_error)
			.setCancelable(false)
			.setPositiveButton(R.string.iknown, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					activity.finish();
				}
			}).show();
	}
}
