package com.flextronics.cn.service.hearing;

import android.content.Context;

public interface IMusicalInstrumentsService {
	public void init(Context context);
	public void Melody(int musicalInstrumentsName);
	public void stop();
}
