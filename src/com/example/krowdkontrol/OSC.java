package com.example.krowdkontrol;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortOut;

public class OSC extends AsyncTask<URL, Integer, Long>{
	
	
	
	//static int port = 5001;
	static OSCPortOut oscPort;
	//static OSCPortOut oscPort2;
	
	String string;
	int value;
	
	public OSC(String string, int value) {
		this.string = string;
		this.value = value;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Long doInBackground(URL... arg0) {
		try {
			 oscPort = new OSCPortOut(InetAddress.getByName("10.15.230.116"), 8200);
			 //oscPort2 = new OSCPortOut(InetAddress.getByName("10.15.232.75 "), 8300);
			 
			 if (null == oscPort) {
				 /*Toast.makeText(MainActivity.ma, "oscPort is null",
							Toast.LENGTH_SHORT).show();*/
				}

				// send an OSC message to start the synth "pink" on node 1000.
				
				// a comma is placed after /s_new in the code
				OSCMessage msg = new OSCMessage();
				//OSCMessage msg2 = new OSCMessage();
				//OSCMessage msg2 = new OSCMessage();
				//msg.addArgument(value);
				msg.setAddress(string);
				//msg2.setAddress("/p");
				
				float val = (float)value/100;
				msg.addArgument(val);
				//msg2.addArgument(value);
				// Object[] args2 = {new Symbol("amp"), new Float(0.5)};
				// OscMessage msg2 = new OscMessage("/n_set", args2);
				//oscPort.send(msg);

				// try to use the send method of oscPort using the msg in nodeWidget
				// send an error message if this doesn't happen
				try {
					oscPort.send(msg);
					//oscPort2.send(msg2);
					Log.d("oscPort",string + ", " + value);
					/*Toast.makeText(MainActivity.ma, string + ", " + value,
							Toast.LENGTH_LONG).show();*/
					///System.out.println(msg.toString());
				} catch (Exception e) {
					Log.d("MyApp","Exception"+Log.getStackTraceString(e));
					
					/*Toast.makeText(MainActivity.ma, "Exception"+e.getStackTrace().toString(),
							Toast.LENGTH_LONG).show();*/
					//showError("Couldn't send");
				}
			
			
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (SocketException e) {
			
			e.printStackTrace();
		}
		
		/*OSCMessage msg = new OSCMessage("/pmrhack", args);
		
       // Send the message
		try {
			sender.send(msg);
		} catch (Exception e) {
			System.err.println("Couldn't send");
		}*/
		return null;
	}
	
	
	

}
