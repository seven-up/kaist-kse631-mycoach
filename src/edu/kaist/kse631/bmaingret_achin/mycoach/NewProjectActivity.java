package edu.kaist.kse631.bmaingret_achin.mycoach;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewProjectActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		
//		if(customTitleSupported){
//			setTitle("New Project");
//		}
//		
//		
//		
		setContentView(R.layout.activity_new_project);
		
		Button btnSubmit = (Button) findViewById(R.id.newProject_infoSubmitButton);
		btnSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String current_act = ((EditText) findViewById(R.id.newProject_numActLastWeek)).getText().toString();
				String current_avg = ((EditText) findViewById(R.id.newProject_current_avgDuration)).getText().toString();
				String goal_act = ((EditText) findViewById(R.id.newProject_goalNumAct)).getText().toString();
				String goal_avg = ((EditText) findViewById(R.id.newProject_goalAvgDuration)).getText().toString();
				String weight = ((EditText) findViewById(R.id.newProject_weight)).getText().toString();
				
				if(current_act.length() != 0 && current_avg.length() != 0 && goal_act.length() != 0
						&& goal_avg.length() != 0 && weight.length() != 0){
					
					Intent intent = new Intent(NewProjectActivity.this, ProjectSettingsActivity.class);
//					Bundle bundle = new Bundle();
//					bundle.putInt("current_activity", Integer.parseInt(current_act));
//					bundle.putInt("current_avg", Integer.parseInt(current_avg));
//					bundle.putInt("goal_activity", Integer.parseInt(goal_act));
//					bundle.putInt("goal_avg", Integer.parseInt(goal_avg));
//					intent.putExtras(bundle);
					SharedPreferences prefs = getApplicationContext().getSharedPreferences(C.PREF, Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putInt(C.P_CURRENT_ACTIVITY, Integer.parseInt(current_act));
					editor.putInt(C.P_CURRENT_AVERAGE, Integer.parseInt(current_avg));
					editor.putInt(C.P_GOAL_ACTIVITY, Integer.parseInt(goal_act));
					editor.putInt(C.P_GOAL_AVERAGE, Integer.parseInt(goal_avg));
					editor.putInt(C.P_WEIGHT, Integer.parseInt(weight));
					editor.putString(C.P_PROJECT_STATE, C.P_CREATED);
					editor.commit();
					startActivity(intent);
				}else{
					Toast.makeText(getApplicationContext(), "All the fields are required to create a new project", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_project, menu);
		return true;
	}

}
