package org.ejemplo.mislugares;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Se define listener para el botón "Nuevo Lugar"
		Button btNuevo = (Button)findViewById(R.id.bt_nuevo);
		btNuevo.setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{
				//Se lanza activity "edición_lugar"				
				Intent intent = new Intent(MainActivity.this, EdicionLugar.class);
				startActivity(intent);	
			}			
		});	
		
		//Se define listener para el botón "Mostrar Lugar"
		Button btMostrar = (Button)findViewById(R.id.bt_mostrar);
		btMostrar.setOnClickListener(new OnClickListener() 				
		{
			@Override
			public void onClick(View v) 
			{
				/*
				//Se lanza activity "vista_lugar"				
				Intent vistaLugar = new Intent(MainActivity.this, VistaLugar.class);
				vistaLugar.putExtra("id", (long)0);
				startActivity(vistaLugar);
				*/
				
				/*Se modifica la llamada:
				 *   Se genera un AlertDialog que aparecerá antes de vista_lugar y 
				 *   nos deja seleccionar el indice del lugar que queramos ver
				 
				final EditText entrada = new EditText(v.getContext());
		        entrada.setText("0");
		        new AlertDialog.Builder(v.getContext())
		           .setTitle("Selección de lugar")
		           .setMessage("indica su id:")
		           .setView(entrada)
		           .setPositiveButton("Ok", new DialogInterface.OnClickListener() 
		           {
		        	   public void onClick(DialogInterface dialog, int whichButton) 
		        	   {
		                   long id = Long.parseLong(entrada.getText().toString());
		                   Intent i = new Intent(MainActivity.this, VistaLugar.class);
		                   i.putExtra("id", id);
		                   startActivity(i);            
		               }		        	   
		           })

		           .setNegativeButton("Cancelar", null)
		           .show(); 	
		           */
				
				//Se lanza activity "lista_lugares"				
				Intent listaLugares = new Intent(MainActivity.this, ListaLugares.class);
				startActivity(listaLugares);
			}	
		});
		
						
		//Se define listener para el botón "SALIR"
		Button btSalir = (Button)findViewById(R.id.bt_salir);
		btSalir.setOnClickListener(new OnClickListener() 
		{			
			@Override
			public void onClick(View v) 
			{
				//Se fuerza la salida				
				finish();
			}			
		});								
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
