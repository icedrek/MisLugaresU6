package org.ejemplo.mislugares;

import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class VistaLugar extends Activity {
	final static int RESULTADO_EDITAR= 1;
	final static int RESULTADO_GALERIA= 2;
	final static int RESULTADO_FOTO= 3;
	
	private long id;
    private Lugar lugar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vista_lugar);
		
		Bundle extras = getIntent().getExtras();
        id = extras.getLong("id", -1);
        lugar = Lugares.elemento((int) id);        
        
        TextView nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        
        ImageView logo_tipo = (ImageView) findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        
        TextView tipo = (TextView) findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());
        
        TextView direccion = (TextView) findViewById(R.id.direccion);
        direccion.setText(lugar.getDireccion());
        
        /*
        TextView telefono = (TextView) findViewById(R.id.telefono);
        telefono.setText(Integer.toString(lugar.getTelefono()));
        */
        
        if (lugar.getTelefono() == 0) 
        {
            findViewById(R.id.telefono).setVisibility(View.GONE);
        } else {
            TextView telefono = (TextView) findViewById(R.id.telefono);
            telefono.setText(Integer.toString(lugar.getTelefono()));
        }
        
        TextView url = (TextView) findViewById(R.id.url);
        url.setText(lugar.getUrl());
        
        TextView comentario = (TextView) findViewById(R.id.comentario);
        comentario.setText(lugar.getComentario());
        
        TextView fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(new Date(lugar.getFecha())));
        
        TextView hora = (TextView) findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(new Date(lugar.getFecha())));
        
        
        RatingBar valoracion = (RatingBar) findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());        
        valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() 
        {
        	@Override
        	public void onRatingChanged(RatingBar ratingBar, float valor, boolean fromUser) 
        	{
        		lugar.setValoracion(valor);                
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vista_lugar, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId()) 
		{
		  
		  case R.id.accion_compartir:
			  Intent intent = new Intent(Intent.ACTION_SEND);
			  intent.setType("text/plain");
			  intent.putExtra(Intent.EXTRA_TEXT, lugar.getNombre() + " - "+ lugar.getUrl());
			  startActivity(intent);
			  return true;
			  
          case R.id.accion_llegar:
        	  verMapa(null);
        	  return true;
        	  
          case R.id.accion_editar:
        	  Intent i = new Intent(this, EdicionLugar.class);        	  	 
        	  i.putExtra("nombre", lugar.getNombre().toString());
        	  i.putExtra("direccion", lugar.getDireccion().toString());
        	  i.putExtra("telefono", lugar.getTelefono());
        	  i.putExtra("url", lugar.getUrl().toString());
        	  i.putExtra("comentario", lugar.getComentario().toString());
        	  startActivity(i);
              return true;
               
          case R.id.accion_borrar:
        	  Lugares.borrar((int) id);
              finish();
              return true;
              
          default:
                 return super.onOptionsItemSelected(item);          
		}
	}
	
	public void verMapa(View view) 
	{
		Uri uri;
        double lat = lugar.getPosicion().getLatitud();
        double lon = lugar.getPosicion().getLongitud();
        
        if (lat != 0 || lon != 0) 
        {
        	uri = Uri.parse("geo:" + lat + "," + lon);        	        	
        } else {
        	uri = Uri.parse("geo:0,0?q=" + lugar.getDireccion());
        }
        
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
	}
	
	public void llamadaTelefono(View view) 
	{		
	    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + lugar.getTelefono())));
	}
	 
	public void pgWeb(View view) 
	{		
	    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(lugar.getUrl())));
	}
	
	public void galeria(View view) 
	{
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	    intent.addCategory(Intent.CATEGORY_OPENABLE);
	    intent.setType("image/*");
	    startActivityForResult(intent, RESULTADO_GALERIA);
	}
}
