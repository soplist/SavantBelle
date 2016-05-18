package com.flextronics.cn.adapter;

import com.flextronics.cn.activity.R;
import com.flextronics.cn.dao.UserDao;
import com.flextronics.cn.util.Constants;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author ZhangGuoYin
 *
 */
public class LinearLayoutAdapter4UserLoadActivity extends BaseAdapter{

	private final static String TAG = "LinearLayoutAdapter4UserLoadActivity";

	private Cursor cursor;
	private LayoutInflater layoutInflater;
	private OnViewCreatedListener onViewCreatedListener;

	
	public static abstract interface OnViewCreatedListener {
		public abstract void onViewCreated(View view, int position);
	}
	
	public void setOnViewCreatedListener(OnViewCreatedListener onViewCreatedListener) {
		this.onViewCreatedListener = onViewCreatedListener;
	}
	
	public LinearLayoutAdapter4UserLoadActivity(Context context, Cursor cursor) {
		super();
		layoutInflater = LayoutInflater.from(context);
		this.cursor = cursor;
		Log.d(TAG, "LinearLayoutAdapter() -- cursor: " + cursor.getCount());
		this.onViewCreatedListener = new OnViewCreatedListener(){

			public void onViewCreated(View view, int position) {
				// TODO Auto-generated method stub				
			}			
		};
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cursor.getCount();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return cursor.getCount();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG, "getView() -- position: " + position); 
		
		cursor.moveToPosition(position);
		
		LinearLayout layout = (LinearLayout)layoutInflater.inflate(
				R.layout.user_load_gallery_item, null);			
		((TextView)layout.findViewById(R.id.TextView_userName)).setText(
				cursor.getString(cursor.getColumnIndex(UserDao.User_NAME)));
		((ImageView)layout.findViewById(R.id.ImageView_userIcon)).setImageResource(
				Constants.USER_ICON_IMAGE_RESOURCES_[cursor.getInt(cursor.getColumnIndex(UserDao.ICON))]);

		onViewCreatedListener.onViewCreated(layout, position);
		return layout;
	}		
}