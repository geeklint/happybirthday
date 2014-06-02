package com.geeklint.happybirthday;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class SetupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
		Button setupButton = (Button) findViewById(R.id.submitButton);
		final DatePicker datePicker = (DatePicker) findViewById(R.id.pickDate);
		datePicker.getCalendarView().setDate(ScheduleNext.getDate(this));
		setupButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				long date = datePicker.getCalendarView().getDate();
				Log.d("HappyBirthday", "Birthday set to: " + date);
				ScheduleNext.setDate(getApplicationContext(), date);
				ScheduleNext.scheduleNext(getApplicationContext());
				Toast.makeText(SetupActivity.this, R.string.confirm_set, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setup, menu);
		return true;
	}

}
