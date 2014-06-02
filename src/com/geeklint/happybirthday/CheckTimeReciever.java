package com.geeklint.happybirthday;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CheckTimeReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		ScheduleNext.scheduleNext(context.getApplicationContext());
	}

}
