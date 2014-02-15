package com.example.krowdkontrol;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	public static MainActivity ma;

	private Spinner spinner1;
	private Track currentTrack;
	private Track[] allTracks;
	
	ToggleButton button1;
	ToggleButton button2;
	ToggleButton button3;
	ToggleButton button4;

	// currently displayed controlls
	// General
	public SeekBar generalMute;
	public SeekBar generalFader;
	public SeekBar generalPan;

	// Reverb
	public SeekBar reverb1;
	public SeekBar reverb2;

	// LPS
	public SeekBar lps1;

	// AM
	public SeekBar am1;

	// Delay
	public SeekBar delay1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ma = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * TextView text = new TextView(this);
		 * text.setText("Hello World, Android - mkyong.com");
		 * setContentView(text);
		 */

		allTracks = setUpAllTracks();
		currentTrack = allTracks[1];
		setUpSeekBars();

		
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();
		
		addListenerOnButtons();
		
	}
	
	void setUpSeekBars(){
		
		generalMute = (SeekBar) findViewById(R.id.generalMute);
		generalFader = (SeekBar) findViewById(R.id.generalFader);
		generalPan = (SeekBar) findViewById(R.id.generalPan);

		// Reverb
		reverb1 = (SeekBar) findViewById(R.id.reverb1);
		reverb2 = (SeekBar) findViewById(R.id.reverb2);

		// LPS
		lps1 = (SeekBar) findViewById(R.id.lps1);

		// AM
		am1 = (SeekBar) findViewById(R.id.am1);

		// Delay
		delay1 = (SeekBar) findViewById(R.id.delay1);
		
		OnSeekBarChangeListener seekbarListener = new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				int value = seekBar.getProgress();
				String name = "name";
				switch (seekBar.getId()) { // <= this is it
				case R.id.generalMute:
					//name += currentTrack.trackId;
					currentTrack.update("general.mute", value);
					break;
				case R.id.generalFader:
					//name += currentTrack.trackId;
					currentTrack.update("general.fader", value);
					// do something
					break;
				case R.id.generalPan:
					//name += currentTrack.trackId;
					currentTrack.update("general.pan", value);
					// do something
					break;
				case R.id.reverb1:
					//name += currentTrack.trackId;
					currentTrack.update("reverb.1", value);
					// do something
					break;
				case R.id.reverb2:
					//name += currentTrack.trackId;
					currentTrack.update("reverb.2", value);
					// do something
					break;
				case R.id.lps1:
					//name += currentTrack.trackId;
					currentTrack.update("lps.1", value);
					// do something
					break;
				case R.id.am1:
					//name += currentTrack.trackId;
					currentTrack.update("am.1", value);
					// do something
					break;
				case R.id.delay1:
					//name += currentTrack.trackId;
					currentTrack.update("delay.1", value);
					// do something
					break;
				}

			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				/*int value = seekBar.getProgress();
				String name = "name";
				switch (seekBar.getId()) { // <= this is it
				case R.id.generalMute:
					//name += currentTrack.trackId;
					currentTrack.update("general.mute", value);
					break;
				case R.id.generalFader:
					//name += currentTrack.trackId;
					currentTrack.update("general.fader", value);
					// do something
					break;
				case R.id.generalPan:
					//name += currentTrack.trackId;
					currentTrack.update("general.pan", value);
					// do something
					break;
				case R.id.reverb1:
					//name += currentTrack.trackId;
					currentTrack.update("reverb.1", value);
					// do something
					break;
				case R.id.reverb2:
					//name += currentTrack.trackId;
					currentTrack.update("reverb.2", value);
					// do something
					break;
				case R.id.lps1:
					//name += currentTrack.trackId;
					currentTrack.update("lps.1", value);
					// do something
					break;
				case R.id.am1:
					//name += currentTrack.trackId;
					currentTrack.update("am.1", value);
					// do something
					break;
				case R.id.delay1:
					//name += currentTrack.trackId;
					currentTrack.update("delay.1", value);
					// do something
					break;
				}*/

			
				/*Toast.makeText(MainActivity.this, R.id.delay1 + ", " + value,
						Toast.LENGTH_SHORT).show();*/
			}
		};

		generalMute.setOnSeekBarChangeListener(seekbarListener);
		generalFader.setOnSeekBarChangeListener(seekbarListener);
		generalPan.setOnSeekBarChangeListener(seekbarListener);
		reverb1.setOnSeekBarChangeListener(seekbarListener);
		reverb2.setOnSeekBarChangeListener(seekbarListener);
		lps1.setOnSeekBarChangeListener(seekbarListener);
		am1.setOnSeekBarChangeListener(seekbarListener);
		delay1.setOnSeekBarChangeListener(seekbarListener);
		

	}

	// add items into spinner dynamically

	private Track[] setUpAllTracks() {
		// TODO Auto-generated method stub
		Track[] tracks = new Track[8];
		for (int i = 0; i < 8; i++) {
			tracks[i] = (new Track(i));
		}
		return tracks;
	}

	public void addListenerOnSpinnerItemSelection() {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}

	// get the selected dropdown list value
	public void addListenerOnButton() {

		spinner1 = (Spinner) findViewById(R.id.spinner1);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void updateSliders(){
		generalMute.setProgress(currentTrack.trackSettings.get("general.mute"));
		generalFader.setProgress(currentTrack.trackSettings.get("general.fader"));
		generalPan.setProgress(currentTrack.trackSettings.get("general.pan"));
		reverb1.setProgress(currentTrack.trackSettings.get("reverb.1"));
		reverb2.setProgress(currentTrack.trackSettings.get("reverb.2"));
		lps1.setProgress(currentTrack.trackSettings.get("lps.1"));
		am1.setProgress(currentTrack.trackSettings.get("am.1"));
		delay1.setProgress(currentTrack.trackSettings.get("delay.1"));
		
	};

	public void addListenerOnButtons() {
		 
		button1 = (ToggleButton) findViewById(R.id.toggleButton1);
		button2 = (ToggleButton) findViewById(R.id.toggleButton2);
		button3 = (ToggleButton) findViewById(R.id.toggleButton3);
		button4 = (ToggleButton) findViewById(R.id.toggleButton4);
		
		button1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	OSC msg = new OSC("button1", 100);
					 msg.execute();
		        } else {
		        	OSC msg = new OSC("button1", 0);
					 msg.execute();
		        }
		    }
		});
		button2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					OSC msg = new OSC("button2", 100);
					msg.execute();
				} else {
					OSC msg = new OSC("button2", 0);
					msg.execute();
				}
			}
		});
		button3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					OSC msg = new OSC("button3", 100);
					msg.execute();
				} else {
					OSC msg = new OSC("button3", 0);
					msg.execute();
				}
			}
		});
		button4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					OSC msg = new OSC("button4", 100);
					msg.execute();
				} else {
					OSC msg = new OSC("button4", 0);
					msg.execute();
				}
			}
		});
 
	}
	/*public void setCurrentTrack(int currentTrack) {
		Toast.makeText(MainActivity.this, currentTrack,
				Toast.LENGTH_SHORT).show();
		this.currentTrack = allTracks[currentTrack];
	}*/

	public class CustomOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			Toast.makeText(parent.getContext(),
					parent.getItemAtPosition(pos).toString(),
					Toast.LENGTH_SHORT).show();
			currentTrack = allTracks[pos];
			updateSliders();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}

	}

}
