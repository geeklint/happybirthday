package com.geeklint.happybirthday;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class ScheduleNext {
	private static final String PREF_NAME = "birthday";
	private static final String PREF_KEY = "birthday";
	
	public static long getDate(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		return preferences.getLong(PREF_KEY, System.currentTimeMillis());
	}
	
	public static void setDate(Context context, long date){
		SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		preferences.edit().putLong(PREF_KEY, date).commit();
	}
	
	public static void scheduleNext(Context context){
		long date = getDate(context);
		long time = System.currentTimeMillis();
		AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		if (date < time){
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date);
			while (date < time){
				calendar.add(Calendar.YEAR, 1);
				date = calendar.getTimeInMillis();
			}
			setDate(context, date);
		}
		if (date - time < 30000){
			Intent intent = new Intent(context, FireworkActivity.class);
			PendingIntent broadcast = PendingIntent.getActivity(context, 0, intent, 0);
			manager.set(AlarmManager.RTC_WAKEUP, date, broadcast);
		} else {
			long nextCheck = ((date - time) / 4 * 3) + time;
			Log.d("HappyBirthday", "Next update at: " + nextCheck);
			Intent intent = new Intent(context, CheckTimeReciever.class);
			PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
			manager.set(AlarmManager.RTC_WAKEUP, nextCheck, broadcast);
		}
	}
}
