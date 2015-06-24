package org.ejemplo.mislugares;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EdicionLugar extends Activity {
	private EditText nombre;
    private Spinner  tipo;
    private EditText direccion;
    private EditText telefono;
    private EditText url;
    private EditText comentario;
    private Lugar lugar;

    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edicion_lugar);
		
		/*
		 * Para recuperar los valores de los campos de este Activity se utiliza
		 * un bloque Try-Catch ya que puede venir algún dato o no según desde donde
		 * estemos llamando al activity.
		 */
		
		//Recuperamos valor para el nombre
		nombre = (EditText) findViewById(R.id.nombre);	
		try
		{
			nombre.setText(getIntent().getExtras().getString("nombre"));
		} catch(NullPointerException e){
			// Acción a realizar si se produce NullPointerException
		}
		
				
		//Definición del Spinner
		tipo = (Spinner) findViewById(R.id.tipo);
		ArrayAdapter<String> adaptador = 
				new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, 
						                 TipoLugar.getNombres());
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tipo.setAdapter(adaptador);   
		tipo.setSelection(1);
		
		
		//Recuperamos valor para la dirección
		EditText direccion = (EditText) findViewById(R.id.direccion);
		try
		{		
			direccion.setText(getIntent().getExtras().getString("direccion"));			
		} catch(NullPointerException e){
			// Acción a realizar si se produce NullPointerException
		}
			
		
		//Recuperamos valor para el telfono
		EditText telefono = (EditText) findViewById(R.id.telefono);
		try
		{
			telefono.setText(getIntent().getExtras().getString("telefono"));			
		} catch(NullPointerException e){
			// Acción a realizar si se produce NullPointerException			
		}
		
		
		//Recuperamos valor de la web
		EditText url = (EditText) findViewById(R.id.url);
		try
		{
			url.setText(getIntent().getExtras().getString("url"));
		} catch(NullPointerException e){
			// Acción a realizar si se produce NullPointerException
		}
		
		//Recuperamos valor del comentario
		EditText comentario = (EditText) findViewById(R.id.comentario);
		try
		{
		   comentario.setText(getIntent().getExtras().getString("comentario"));
		} catch(NullPointerException e){
			// Acción a realizar si se produce NullPointerException
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// "Infla" el menu; Esto incluye items en la barra de acción si está presente.
		getMenuInflater().inflate(R.menu.edicion_lugar, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
	{
		/*
		 * Utilizamos 2 items en el menu:
		 *    - Cancelar: Termina el Activity sin guardar ningún cambio.
		 *    - Guardar : Modifica los datos del lugar y finaliza el activity.
		 */
		switch(item.getItemId()) 
		{		
		  case R.id.accion_cancelar:
			  finish();
              return true;
              
          case R.id.accion_guardar:
        	  lugar.setNombre(nombre.getText().toString());
      		  lugar.setTipo(TipoLugar.values()[tipo.getSelectedItemPosition()]);
      		  lugar.setDireccion(direccion.getText().toString());
      		  lugar.setTelefono(Integer.parseInt(telefono.getText().toString()));
      		  lugar.setUrl(url.getText().toString());
      		  lugar.setComentario(comentario.getText().toString());
      		  finish();
      		  return true;	
      		  
          default:
              return super.onOptionsItemSelected(item);          
		}
	}
}
