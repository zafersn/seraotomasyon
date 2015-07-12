/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ugur.android.BluetoothDemo;

import com.ugur.android.BluetoothDemo.R;

import android.R.color;
import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ContextMenu;
//import android.util.Log;
//import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
//import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * This is the main Activity that displays the current chat session.
 */
public class SeraOtomasyonuv1 extends Activity {
    // Debugging
    //private static final String TAG = "BluetoothDemo";
    //private static final boolean D = true;

    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1; 
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    //public static String strName, strTC = null;
    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    // Layout Views
    private TextView mTitle;
    private ListView mConversationView;
    //private EditText mOutEditText;
    //private Button mSendButton;
    
   
    TextView sicaklikdeger;
    TextView nemdeger;
    TextView selsiyus;
    
    String kalanSureGelen=null;
    String sicaklikGelen=null;
    String nemGelen=null;
    ProgressDialog progress;
    
    /*SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
    
    boolean ilkCalisma = sp.getBoolean("ilkCalisma", false);*/
    // Name of the connected device
    private String mConnectedDeviceName = null;
    // Array adapter for the conversation thread
    private ArrayAdapter<String> mConversationArrayAdapter;
    // String buffer for outgoing messages
    //private StringBuffer mOutStringBuffer;
    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the chat services
    private BluetoothDemoService mChatService = null;


    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        // Set up the window layout
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //setContentView(R.layout.main);
        setContentView(R.layout.anaekran);
        Log.i("giris", "1");
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
        Log.i("giris", "2");
        // Set up the custom title
        mTitle = (TextView) findViewById(R.id.title_left_text);
        Log.i("giris", "3");
        mTitle.setText(R.string.app_name);
        Log.i("giris", "4");
        mTitle = (TextView) findViewById(R.id.title_right_text);
        Log.i("giris", "5");
        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        
    }
    
    public void btgelenAnaliz(String gelen){
    	nemdeger=(TextView) findViewById(R.id.setnem);
		sicaklikdeger=(TextView) findViewById(R.id.setsicaklik);
    	String[] ayrildi = gelen.split("/");
    	Log.i("ayrýlda gell : ", " "+ayrildi[0]+" "+ayrildi[1]+" "+ayrildi[2]);
    	sicaklikGelen=ayrildi[0];
    	nemGelen=ayrildi[1];
    	kalanSureGelen=ayrildi[2];
    	//sicaklikdeger.setTextColor(Color.parseColor("#ffffff"));
        //kalanSureGelen=readMessage.substring(readMessage.lastIndexOf("/")+1, readMessage.length());
    	sicaklikdeger.setText(sicaklikGelen);
    	sicaklikdeger.setTextColor(Color.parseColor("#f20b0b"));
    	nemdeger.setText(nemGelen);
        
    }
    
    public String rakamSayisiKontrol(int kontrol){
		String sonuc=null;
    	if(kontrol<10){
			sonuc="0"+kontrol;
		}
    	else if(kontrol>=10){
    		sonuc=""+kontrol;
    	}
		return sonuc;
   }
    
    public void cikis(){
    	new AlertDialog.Builder(this)
        .setTitle("ÇIKIÞ")
        .setMessage("TAMAM'a baasarsanýz uygulamadan çýkýlacak ve bluetooth kapatýlacaktýr.Uygulamaya geri dönmek için ÝPTAL'a basýn.")
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
            	SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        		SharedPreferences.Editor spEditor = sp.edit();
        		
        		spEditor.putBoolean("baglan", true);
        		spEditor.commit();
        		
        		mBluetoothAdapter.disable();
        		
        		Intent startMain = new Intent(Intent.ACTION_MAIN);
        		startMain.addCategory(Intent.CATEGORY_HOME);
        		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        		startMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		startActivity(startMain);
            }
         })
        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { 
                // do nothing
            }
         })
        .setIcon(android.R.drawable.ic_dialog_alert)
         .show();
    	
    }
    
    public void degerleriGonder(){
    	SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
    	
    	int sicakligininseti = sp.getInt("setsicaklik", 0);
		int neminseti = sp.getInt("setnem", 0);
		int calismasuresi=sp.getInt("calismaSuresi",0);		
		String calismasuresigonder=rakamSayisiKontrol(calismasuresi);
		int calismasaati=sp.getInt("calismaSaati",0);
		String calismasaatigonder=rakamSayisiKontrol(calismasaati);
		
		String calismasaatibirimi=sp.getString("ilkeleman", "SAAT");
		String calismasuresibirimi=sp.getString("ücüncüeleman","DAKÝKA");
		
		sendMessage(sicakligininseti+"/"+neminseti+"/"+calismasaatibirimi.substring(0, 1)+""+calismasaatigonder+
				"/"+calismasuresibirimi.substring(0, 1)+""+calismasuresigonder);
		
		Toast.makeText(getApplicationContext(), "gönderilen mesaj:"+sicakligininseti+"/"+neminseti+"/"+calismasaatibirimi.substring(0, 1)+""+calismasaatigonder+
				"/"+calismasuresibirimi.substring(0, 1)+""+calismasuresigonder, Toast.LENGTH_LONG).show();
		
		Log.i("gönderilen mesaj:",sicakligininseti+"/"+neminseti+"/"+calismasaatibirimi.substring(0, 1)+""+calismasaatigonder+
				"/"+calismasuresibirimi.substring(0, 1)+""+calismasuresigonder);
		
    }
    @SuppressLint("NewApi")
	@Override
    public void onStart() {
        super.onStart();
        //if(D) Log.e(TAG, "++ ON START ++");

        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        // Otherwise, setup the chat session
        } else {
            if (mChatService == null) setupChat();
        }
    }

    @Override
    public synchronized void onResume() {
    	super.onResume();

        try {
        	
        	  //Log.i("ugurrrrrr", "buluetooth baðlandýý");
              //if(D) Log.e(TAG, "+ ON RESUME +");

              // Performing this check in onResume() covers the case in which BT was
              // not enabled during onStart(), so we were paused to enable it...
              // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
              if (mChatService != null) {
                  // Only if the state is STATE_NONE, do we know that we haven't started already
                  if (mChatService.getState() == BluetoothDemoService.STATE_NONE) {
                    // Start the Bluetooth chat services
                	 
                    mChatService.start();
                  }
              }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
      
    }
 
    private void setupChat() {
    	try {
    		Log.i("mConnectedDeviceName=", ""+mConnectedDeviceName);
    		
    		SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
    		
    	    boolean ilkCalisma = sp.getBoolean("ilkCalisma", false);
    	    boolean baglan = sp.getBoolean("baglan", true);
    	    
    	    if(baglan){
    	    	
    	    	if(ilkCalisma){
    	    	progress = ProgressDialog.show(this, "SENKRONÝZASYON",
    	    			  "veriler sisteme yükleniyor...", true);}
    	    	
    	    	Intent serverIntent = new Intent(this, DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
    	    	SharedPreferences.Editor spEditor = sp.edit();
    			spEditor.putBoolean("baglan", false);
    			spEditor.commit();
    	    	
    	    }
    	    int sicakligininseti = sp.getInt("setsicaklik", 0);
			int neminseti = sp.getInt("setnem", 0);
			sendMessage(sicakligininseti+"/"+neminseti);
    		if(ilkCalisma){
    			//extras.getString(SetEkrani.sicaklikSet)
    			try {
    				
    				selsiyus=(TextView) findViewById(R.id.selsiyus);
							//sicaklikdeger.setText(("20") + " \u2109");;
            		    				
            		Log.i("ana ekranda sýcaklýk : ",""+sicakligininseti);
            		//btgelenAnaliz("15/12/25");            		
            		//sicaklikdeger.setText(sicakligininseti+"");            		
        			//nemdeger.setText(""+neminseti);        	    			
        			Log.i("ana ekranda nem : ",""+neminseti);
        			SharedPreferences.Editor spEditor = sp.edit();
        			spEditor.putBoolean("ilkCalisma", false);
        			spEditor.commit();
				} catch (Exception e) {
					//SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
					Log.i("yapma be olummm", "hataaaaa");
					SharedPreferences.Editor spEditor = sp.edit();
					spEditor.putBoolean("ilkCalisma", false);
					spEditor.commit();
				}
				
    		}
    		
        // Initialize the array adapter for the conversation thread
    	
        mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);
        mConversationView = (ListView) findViewById(R.id.in);
        mConversationView.setAdapter(mConversationArrayAdapter);
        
        // Initialize the BluetoothChatService to perform bluetooth connections
        mChatService = new BluetoothDemoService(this, mHandler);

        // Initialize the buffer for outgoing messages
        //mOutStringBuffer = new StringBuffer("");
    	} catch (Exception e) {
    		// TODO: handle exception
    	}
    }
    
    @Override
    public synchronized void onPause() {
        super.onPause();
        //if(D) Log.e(TAG, "- ON PAUSE -");
    }

    @Override
    public void onStop() {
        super.onStop();
        //if(D) Log.e(TAG, "-- ON STOP --");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the Bluetooth chat services
        if (mChatService != null) mChatService.stop();
        //if(D) Log.e(TAG, "--- ON DESTROY ---");
    }

    @SuppressLint("NewApi")
	private void ensureDiscoverable() {
        //if(D) Log.d(TAG, "ensure discoverable");
        if (mBluetoothAdapter.getScanMode() !=
            BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }
    
  private void sendData(byte[] send){
	  if (mChatService.getState() != BluetoothDemoService.STATE_CONNECTED) {
          Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
          return;
      }
	  
	  
	  if(send.length>0)
		  mChatService.write(send);
  }

    /**
     * Sends a message.
     * @param message  A string of text to send.
     */
    public void sendMessage(String message) {
    	try {
            // Check that we're actually connected before trying anything
    		Log.i("gönderme fonksiyonunda", "1");
            if (mChatService.getState() != BluetoothDemoService.STATE_CONNECTED) {
            	Log.i("gönderme fonksiyonunda", "2");
                Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
                return;
            }

            // Check that there's actually something to send
            if (message.length() > 0) {
            	Log.i("gönderme fonksiyonunda", "3");
                // Get the message bytes and tell the BluetoothChatService to write
                byte[] send = message.getBytes();
                mChatService.write(send);

                // Reset out string buffer to zero and clear the edit text field
                //mOutStringBuffer.setLength(0);
                //mOutEditText.setText(mOutStringBuffer);
            }
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }

  

    // The Handler that gets information back from the BluetoothChatService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_STATE_CHANGE:
                Log.i("sera otomasyonu classýnda", "MESSAGE_STATE_CHANGE: " + msg.arg1);
                switch (msg.arg1) {
                case BluetoothDemoService.STATE_CONNECTED:
                	try {
                        mTitle.setText(R.string.title_connected_to);
                        mTitle.append(mConnectedDeviceName);
                        mConversationArrayAdapter.clear();
                        progress.dismiss();
                        degerleriGonder();
					} catch (Exception e) {
						// TODO: handle exception
					}
                	
                    break;
                case BluetoothDemoService.STATE_CONNECTING:
                    mTitle.setText(R.string.title_connecting);
                    break;
                case BluetoothDemoService.STATE_LISTEN:
                case BluetoothDemoService.STATE_NONE:
                    mTitle.setText(R.string.title_not_connected);
                    break;
                } 
                break;
            /*case MESSAGE_WRITE:
                byte[] writeBuf = (byte[]) msg.obj;
                //Toast.makeText(getApplicationContext(), "Bilgi gönderildi",Toast.LENGTH_SHORT).show();
                // construct a string from the buffer
                String writeMessage = new String(writeBuf);
                mConversationArrayAdapter.add("Me:  " + writeMessage);
                break;*/
           case MESSAGE_READ:
        	  //TextView bluetoothgelen=(TextView) findViewById(R.id.btgelen);
        	   Log.i("gelen metotunda msg.arg=", ""+msg.arg1);
               byte[] readBuf = (byte[]) msg.obj;
               String readMessage = new String(readBuf,0, msg.arg1);             
               Log.i("gelen metotunda", " "+readMessage);               
               if(readMessage.length()==8){
            	   Log.i("geldi metotunda", " "+readMessage.substring(0, 2));
            	   btgelenAnaliz(readMessage); 
               }
               
               //sicaklikGelen=readMessage.
               //bluetoothgelen.setText(readMessage);//ugur
                /*byte[] readBuf = (byte[]) msg.obj;*/
                /*Led1 = (RadioButton) findViewById(R.id.LED1);
                Led2 = (RadioButton) findViewById(R.id.LED2);
                sicaklik = (ProgressBar) findViewById(R.id.progressBar1);
                durum = (ToggleButton) findViewById(R.id.toggleButton1);
                isidegeri = (TextView) findViewById(R.id.sicaklik);*/
                //Gelen verinin 8. biti 1 ise veri LED ve Buton durum bilgisi olarak deðerlendirilir.
               /* if((readBuf[0] & 0x80) == 0x80){
				//Gelen verinin 1. bitini durumuna göre LED1 göstergesi düzenlenir.
                if((readBuf[0] & 0x01) == 0x01){
                Led1.setChecked(true);
                LED1check=true;}
                else {
                Led1.setChecked(false);
                LED1check=false;}
                //Gelen verinin 2. bitinin durumuna göre LED2 göstergesi düzenlenir.
                if((readBuf[0] & 0x02)==0x02){
                Led2.setChecked(true);
                LED2check=true;}
                else{
                Led2.setChecked(false);
                LED2check=false;}
                //Gelen verinin 3. bitinin durumuna göre Buton durum göstergesi düzenlenir.
                if((readBuf[0] & 0x04)==0x04)
                	durum.setChecked(true);
                    else
                    durum.setChecked(false);}
				//Gelen verinin 8. biti sýfýr ise gelen veri sýcaklýk bilgisi olarak deðerlendirilir.
                else {
            	if(readBuf[0]>100) readBuf[0]=100;		//Sýcaklýk deðeri max 100 dereceyi aþmýcak þekilde ayarlanýr.
				//Sýcaklýk bilgisi görüntülenir.
				sicaklik.setProgress((int)readBuf[0]);
                isidegeri.setText("Sýcaklýk="+readBuf[0]+"°");}			
                break;*/
            case MESSAGE_DEVICE_NAME:
                // save the connected device's name
                mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
 /*burada patlýyo */              Toast.makeText(getApplicationContext(), "Connected to "
                               + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                break;
            case MESSAGE_TOAST:
                Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
                               Toast.LENGTH_SHORT).show();
                break;
            }
        }
    };

    @SuppressLint("NewApi")
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if(D) Log.d(TAG, "onActivityResult " + resultCode);
    	Log.i("on activity result bu neymiþ ki=", ""+resultCode);
        switch (requestCode) {
        
        case REQUEST_CONNECT_DEVICE:
        	Log.i("on activity", "1");
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
            	Log.i("result ok", "1");
                // Get the device MAC address
                String address = data.getExtras()
                                     .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                Log.i("result ok", "2");
                // Get the BLuetoothDevice object
                BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address); 
                Log.i("result ok", "3");
                // Attempt to connect to the device
                mChatService.connect(device);
                Log.i("result ok", "4");
                
            }
            break;
        case REQUEST_ENABLE_BT:
        	Log.i("on activity", "3");
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
            	Log.i("on activity", "4");
                // Bluetooth is now enabled, so set up a chat session
                setupChat();
            } else {
            	Log.i("on activity", "5");
                // User did not enable Bluetooth or an error occured
                //Log.d(TAG, "BT not enabled");
                Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.scan:
            // Launch the DeviceListActivity to see devices and do scan
            Intent serverIntent = new Intent(this, DeviceListActivity.class);
            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
            return true;
        case R.id.ayarlar:
            // Ensure this device is discoverable by others
        	mChatService.stop();
			Intent ayar= new Intent(SeraOtomasyonuv1.this,SetEkrani.class);
			startActivity(ayar);
        	//ensureDiscoverable();//görünürlük ayarý için fonksiyon
            return true;
        case R.id.cikis:
        	cikis();
        	return true;
        }
        return false;
    }

}