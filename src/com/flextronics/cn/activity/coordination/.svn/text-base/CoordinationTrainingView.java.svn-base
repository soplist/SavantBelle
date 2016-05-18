/**
 * 
 */
package com.flextronics.cn.activity.coordination;

import android.view.View;

/**
 * 手眼协调训练的主页面
 * 
 * @author Zhaohe.Guo
 * 
 *         2011-1-10 下午02:31:24
 */
public interface CoordinationTrainingView {

	public View getWrappedView();

	/**
	 * 开始闪烁
	 */
	public void startFlash();

	/**
	 * 停止闪烁
	 */
	public void stopFlash();

	/**
	 * 监听训练时一些事件
	 * 
	 * @author Zhaohe.Guo
	 * 
	 *         2011-2-28 下午02:08:59
	 */
	public interface TrainingListener {

		/**
		 * 回答正确时回调
		 * 
		 * @param tView
		 */
		public void onRight(CoordinationTrainingView tView);

		/**
		 * 回答错误时的回调
		 * 
		 * @param tView
		 * @param x
		 * @param y
		 */
		public void onWrong(CoordinationTrainingView tView, int x, int y);

		/**
		 * 回答超时时回调
		 * 
		 * @param tView
		 */
		public void onTimeOut(CoordinationTrainingView tView);

		/**
		 * 测试结束时的回调
		 * 
		 * @param tView
		 */
		public void onEnd(CoordinationTrainingView tView);

		/**
		 * 测试开始时的回调
		 * 
		 * @param tView
		 */
		public void onStart(CoordinationTrainingView tView);
	}
}
