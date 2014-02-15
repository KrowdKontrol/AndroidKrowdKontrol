package com.example.krowdkontrol;

import java.util.HashMap;
import java.util.Map;

import android.widget.Toast;

public class Track {
	int trackId;
	public Map<String, Integer> trackSettings;
	
	public Track(int trackId) {
		this.trackId = trackId;
		trackSettings = initialiseSettings();
	}
	
	public Map<String, Integer> initialiseSettings(){
		Map<String, Integer> trackSettings = new HashMap<String, Integer>();
		int value = 0;
		//General
		trackSettings.put("general.mute", value);
		trackSettings.put("general.fader", value);
		trackSettings.put("general.pan", value);
		//Reverb
		trackSettings.put("reverb.1", value);
		trackSettings.put("reverb.2", value);
		//LSP
		trackSettings.put("lps.1", value);
		//AM
		trackSettings.put("am.1", value);
		//Delay
		trackSettings.put("delay.1", value);
		return trackSettings;
		
	}
	
	public void update(String property, int value){
		trackSettings.put(property, value);
		OSC msg = new OSC(trackId+"."+property, value);
		msg.execute();
		
		//System.out.println(trackId+property+" "+value);
	}
	

}
