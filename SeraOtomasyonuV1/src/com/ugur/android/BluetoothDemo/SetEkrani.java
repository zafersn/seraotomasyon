package com.ugur.android.BluetoothDemo;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.ugur.android.BluetoothDemo.R;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class SetEkrani extends Activity {
	/*public static String sicaklikSet = "aabs";
	public static String nemSet  = "aasd";*/
	
	SeekBar seekbarsicaklik;
	SeekBar seekbarnem;
	TextView setsicaklik;
	TextView setnem;
	EditText calismaSaati;
	EditText calismaSuresi;
	Spinner spinnersoldaki;
	Spinner spinnersagdaki;
	
	int sicaklikstep = 1;
	int sicaklikmax = 30;
	int sicaklikmin = 20;
	
	int nemstep = 1;
	int nemmax = 100;
	int nemmin = 0;
	
	int calismaSaatiMax=99;
	int calismaSuresiMax=99;
	
	String degernem="";
	String degersicaklik="";
	String girilen1="";
	
	List<String> arrList1 = new ArrayList<String>();
	List<String> arrList2 = new ArrayList<String>();
	
	
	public boolean calismaSaatiKntrol(int deger) {

        AlertDialog alertDialogBuilder = new AlertDialog.Builder(this).create();
		//AlertDialog.Builder alertDialogBuilder = new  AlertDialog.Builder(this);
        // Setting Dialog Title
        alertDialogBuilder.setTitle("HATA");

        // Setting Dialog Message
        alertDialogBuilder.setMessage("GÝRÝLEN DEÐER BÜYÜK");

        // Setting Icon to Dialog
        //alertDialogBuilder.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialogBuilder.setButton("TAMAM", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,int which) 
                    {
                        // Write your code here to execute after dialog closed
                    Toast.makeText(getApplicationContext(),"TAMAM", Toast.LENGTH_SHORT).show();
                    }
                });

        // Showing Alert Message
        if(deger>calismaSaatiMax){
        	alertDialogBuilder.show();
        	return false;
        }
        	return true;
        
	}
	
	public boolean calismaSuresiKntrol(int deger1) {

        AlertDialog alertDialogBuilder1 = new AlertDialog.Builder(this).create();
		//AlertDialog.Builder alertDialogBuilder = new  AlertDialog.Builder(this);
        // Setting Dialog Title
        alertDialogBuilder1.setTitle("HATA");

        // Setting Dialog Message
        alertDialogBuilder1.setMessage("GÝRÝLEN DEÐER BÜYÜK");

        // Setting Icon to Dialog
        //alertDialogBuilder.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialogBuilder1.setButton("TAMAM", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,int which) 
                    {
                        // Write your code here to execute after dialog closed
                    Toast.makeText(getApplicationContext(),"TAMAM", Toast.LENGTH_SHORT).show();
                    }
                });

        // Showing Alert Message
        if(deger1>calismaSuresiMax){
        	alertDialogBuilder1.show();
        	return false;
        }
        	return true;
        
	}
	
	public void karsilastirma (){
		 AlertDialog alertDialogBuilder2 = new AlertDialog.Builder(this).create();
			//AlertDialog.Builder alertDialogBuilder = new  AlertDialog.Builder(this);
	        // Setting Dialog Title
	     alertDialogBuilder2.setTitle("HATA");

	        // Setting Dialog Message
	     alertDialogBuilder2.setMessage("Çalýþma süresi , çalýþma aralýðýndan büyük veya eþit olamaz.");

	        // Setting Icon to Dialog
	        //alertDialogBuilder.setIcon(R.drawable.tick);

	        // Setting OK Button
	     alertDialogBuilder2.setButton("TAMAM", new DialogInterface.OnClickListener() {

	                    public void onClick(DialogInterface dialog,int which) 
	                    {
	                        // Write your code here to execute after dialog closed
	                    Toast.makeText(getApplicationContext(),"TAMAM", Toast.LENGTH_SHORT).show();
	                    }
	                });
	     alertDialogBuilder2.show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ayar_ekrani);
		
		final SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
		final SharedPreferences.Editor spEditor = sp.edit();
		
		spEditor.putBoolean("ilkCalisma", true);
		spEditor.putBoolean("baglan", true);
		spEditor.commit();
		
		//String [] androidVersion =getResources().getStringArray(R.array.spinner_veriler);
		
		final Spinner spinnersagdaki=(Spinner) findViewById(R.id.spinner1);
		Spinner spinnersoldaki=(Spinner) findViewById(R.id.spinner2);
		
		arrList1.add( sp.getString("ilkeleman", "DAKÝKA"));
		arrList1.add(sp.getString("ikincieleman", "SAAT"));
		
		arrList2.add(sp.getString("ücüncüeleman","DAKÝKA"));
		arrList2.add(sp.getString("dördüncüeleman", null));
		
		Log.i("arrlist2 içeriði=", ""+arrList2.size());
		Log.i("hataaaa", "1");
		
		ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrList1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        final ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrList2);
        
        spinnersoldaki.setAdapter(arrayAdapter1);
        
        spinnersoldaki.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String selectedAndroid=parent.getSelectedItem().toString();
				if(selectedAndroid.equalsIgnoreCase("DAKÝKA")){
					try{
						spEditor.putString("ilkeleman", "DAKÝKA");
						spEditor.putString("ikincieleman", "SAAT");
						spEditor.commit();
						arrList2.clear();
						Log.i("tryda", "4");
						arrList2.add("DAKIKA");
						Log.i("tryda", "5");
				        spinnerlistDuzenleme();
					} catch (Exception e) {
						Log.i("excepsin", "hataaa");
					}
					
				}
				else if(selectedAndroid.equalsIgnoreCase("SAAT")){
					spEditor.putString("ilkeleman", "SAAT");
					spEditor.putString("ikincieleman", "DAKÝKA" );
					spEditor.commit();
					arrList2.clear();
					arrList2.add(0,sp.getString("ücüncüeleman","SAAT"));
					arrList2.add(1, sp.getString("dördüncüeleman","DAKÝKA"));
					spinnerlistDuzenleme();
				}
			}

			private void spinnerlistDuzenleme() {
				
			    		
			            arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			    		spinnersagdaki.setAdapter(arrayAdapter2);
			    	
				
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});

       
        Log.i("hataaaa", "2");
        spinnersagdaki.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String selectedAndroid=parent.getSelectedItem().toString();
				if(selectedAndroid.equalsIgnoreCase("DAKÝKA")){
						spEditor.putString("ücüncüeleman", "DAKÝKA");
						spEditor.putString("dördüncüeleman", "SAAT");
						spEditor.commit();
				}
				else if(selectedAndroid.equalsIgnoreCase("SAAT")){
					spEditor.putString("ücüncüeleman", "SAAT");
					spEditor.putString("dördüncüeleman", "DAKÝKA" );
					spEditor.commit();
					
				}
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
        
		Button geri=(Button) findViewById(R.id.anaekran);
		geri.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String ilkeleman=sp.getString("ilkeleman", "DAKÝKA");
				String ucuncueleman=sp.getString("ücüncüeleman","DAKÝKA");
				int gndcalismasuresi =sp.getInt("calismaSuresi", 0);
				int gndcalismasaati = sp.getInt("calismaSaati", 0);
				if((ilkeleman.equalsIgnoreCase(""+ucuncueleman))&&(gndcalismasuresi>=gndcalismasaati))
				{
					karsilastirma();
				}
				 
				else{
					Intent intent = new Intent(SetEkrani.this,SeraOtomasyonuv1.class);
				    startActivity(intent);
				}
			      
			}
		});
		Log.i("hataaaa", "3");
		calismaSuresi=(EditText)findViewById(R.id.editText2);
		calismaSuresi.setText(""+sp.getInt("calismaSuresi", 0));
		/*calismaSuresi.setText(""+sp.getString("calismaSuresi","0"));
		//calismaSuresi.setText("asd");
		String deneme=sp.getString("calismaSuresi","0");
		calismaSuresi.setText(""+deneme);*/
		Log.i("hataaaa", "4");
		calismaSuresi.setOnEditorActionListener(new OnEditorActionListener() {
			
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					try {
						//SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
						//SharedPreferences.Editor spEditor = sp.edit();
						boolean sonuc =calismaSuresiKntrol(Integer.parseInt(calismaSuresi.getText().toString()));	
						if(sonuc){
							Toast.makeText(getApplicationContext(),"sonuc true deger 6 dan küçük", Toast.LENGTH_LONG).show();
							
							spEditor.putInt("calismaSuresi",Integer.parseInt(calismaSuresi.getText().toString()));
							//spEditor.putString("calismaSuresi", calismaSuresi.getText().toString());
							//spEditor.putString("calismaSuresi", ""+calismaSuresi.getText());
							spEditor.commit();
						}
						else{
							Toast.makeText(getApplicationContext(),"sonuc true deger 5 dan büyük", Toast.LENGTH_LONG).show();
							//calismaSuresi.setText(sp.getString("calismaSuresi", "0"));
							calismaSuresi.setText(""+sp.getInt("calismaSuresi", 0));
						}
					} catch (Exception e) {
							Toast.makeText(getApplicationContext(),"Çalýþma süresi boþ býrakýlamaz.", Toast.LENGTH_LONG).show();
							//calismaSuresi.setText(sp.getString("calismaSuresi", "0"));
							calismaSuresi.setText(""+sp.getInt("calismaSuresi", 0));
					}
					
				}
				return false;
			}
		});
		
		calismaSaati=(EditText)findViewById(R.id.editText1);
		calismaSaati.setText(""+sp.getInt("calismaSaati", 0));
		//calismaSaati.setText(sp.getString("calismaSaati","0"));
		calismaSaati.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		       
				public boolean onEditorAction(TextView v, int actionId,
						KeyEvent event) {
					
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							try {
								//SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
								//SharedPreferences.Editor spEditor = sp.edit();
								boolean sonuc =calismaSaatiKntrol(Integer.parseInt(calismaSaati.getText().toString()));	
								if(sonuc){
									Toast.makeText(getApplicationContext(),"sonuc true deger 6 dan küçük", Toast.LENGTH_LONG).show();
									
									spEditor.putInt("calismaSaati",Integer.parseInt(calismaSaati.getText().toString()));
									//spEditor.putString("calismaSaati",calismaSaati.getText().toString());		
									spEditor.commit();
								}
								else{
									Toast.makeText(getApplicationContext(),"sonuc true deger 5 dan büyük", Toast.LENGTH_LONG).show();
									//calismaSaati.setText(""+sp.getInt("calismaSaati", 0));
									//calismaSaati.setText(sp.getString("calismaSaati","0"));
									calismaSaati.setText(""+sp.getInt("calismaSaati", 0));
								}
							} catch (Exception e) {
									Toast.makeText(getApplicationContext(),"Çalýþma aralýðý boþ býrakýlamaz.", Toast.LENGTH_LONG).show();
									//calismaSaati.setText(""+sp.getInt("calismaSaati", 0));
									//calismaSaati.setText(sp.getString("calismaSaati", "0"));
									calismaSaati.setText(""+sp.getInt("calismaSaati", 0));
							}
							
							
			            }
						return false;
					
					
				}
		    });
		
		setsicaklik=(TextView) findViewById(R.id.textViewsicaklik);
		seekbarsicaklik = (SeekBar) findViewById(R.id.seeksicaklik);
		
		int sicakligininseti = sp.getInt("setsicaklik", 0);
        Log.i("structr degernem 1 :",""+sicakligininseti);
        setsicaklik.setText(" "+sicakligininseti);
		
		seekbarsicaklik.setMax( (sicaklikmax - sicaklikmin) / sicaklikstep );
		seekbarsicaklik.setProgress(sicakligininseti-20);
        seekbarsicaklik.setRotation(0);
        seekbarsicaklik.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				int value = sicaklikmin + (progress * sicaklikstep);
				setsicaklik.setText(" "+value);
				SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
				SharedPreferences.Editor spEditor = sp.edit();
				spEditor.putInt("setsicaklik",value);
				spEditor.commit();
				Log.i("set ekranýnda sýcaklýk seekbar degeri:",degersicaklik);
			}
		});
        
        setnem=(TextView)findViewById(R.id.textViewnem);
        seekbarnem=(SeekBar)findViewById(R.id.seeknem);
    
        int neminseti = sp.getInt("setnem", 0);
        Log.i("structr degernem 1 :",""+neminseti);
        setnem.setText(" "+neminseti);
        
        seekbarnem.setMax( (nemmax - nemmin) / nemstep );
        seekbarnem.setProgress(neminseti);
        seekbarnem.setRotation(0);
        seekbarnem.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				try {
					int value = nemmin + (progress * nemstep);
					setnem.setText(" "+value);
					//SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
					//SharedPreferences.Editor spEditor = sp.edit();
					spEditor.putInt("setnem",value);
					spEditor.commit();
					Log.i("set ekranýnda nem seekbar degeri:",degernem);
					
					//arrList.add(0,degernem);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
	}

}
