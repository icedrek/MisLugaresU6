package org.ejemplo.mislugares;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ListaLugares extends ListActivity {
	public BaseAdapter adaptador;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_lugares);
	        /*
	         * Se cambia el uso de adaptador basico por uno personalizado.
	        adaptador = new ArrayAdapter<String>(this,
	        	       R.layout.elemento_lista,
	        	       R.id.nombre,
	        	       Lugares.listaNombres());
	         */
	    adaptador = new MiAdaptador(this);
	    setListAdapter(adaptador);	    
	}
	
	@Override
	protected void onListItemClick(ListView listView, View vista, int posicion, long id) 
	{
		super.onListItemClick(listView, vista, posicion, id);
		Intent intent= new Intent(this, VistaLugar.class);
		intent.putExtra("id", id);
		startActivity(intent);	    
	}
	    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_lugares, menu);
		return true;
	}

}
