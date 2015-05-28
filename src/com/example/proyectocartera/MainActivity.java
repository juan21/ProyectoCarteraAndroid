package com.example.proyectocartera;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements OnClickListener
{
	//EditText editcuenta,editnombre,editprecio,editsaldo;
	ImageButton btnagregar,btneditar,btncuentas,btnresul;
	SQLiteDatabase db;
	ListView lista;
	TextView editcuenta, editnombre,editprecio,editsaldo;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnagregar=(ImageButton)findViewById(R.id.imageButton1);
        btneditar=(ImageButton)findViewById(R.id.imageButton2);
        btncuentas=(ImageButton)findViewById(R.id.imageButton3);
        this.btnagregar=(ImageButton)findViewById(R.id.imageButton1);
        this.btneditar=(ImageButton)findViewById(R.id.imageButton2);
        this.btnresul=(ImageButton)findViewById(R.id.imageButton4);
        //this.btncuentas=(ImageButton)findViewById(R.id.imageButton3);
        btncuentas.setOnClickListener(this);
        lista = (ListView) findViewById(R.id.listView1);
        
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS student(cuenta VARCHAR,nombre VARCHAR,precio VARCHAR,saldo VARCHAR);");
    }
    public void btnagregar (View v){
    	if(v==btnagregar){
    	Intent ediciones = new Intent(getApplicationContext(), EdicionesActivity.class);
   	    startActivity(ediciones);
    	}
    }
 public void btneditar (View v){
	 if(v==btneditar){
	 Intent editartex = new Intent(getApplicationContext(), EditartexActivity.class);
	  	startActivity(editartex);
	 }
    }
 public void btnresultado (View v){
	 if(v==btnresul){
	 Intent buscar = new Intent(getApplicationContext(), BuscarActivity.class);
	  	startActivity(buscar);
	 }
    }
    public void onClick(View view)
    {
    	 if(view==btncuentas)
    	{
    		this.viewAll();
    	}
    }
    private void showMessage(String title,String message)
    {
    	Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
	}
    private void viewAll()
    {
		Cursor c=db.rawQuery("SELECT * FROM student", null);
		if(c.getCount()==0)
		{
			CharSequence texto = "No hay resultados";
            Toast toast = Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			return;
		}
		StringBuffer buffer=new StringBuffer();
		while(c.moveToNext())
		{
			buffer.append("Cuenta: "+c.getString(0)+"\n");
			/*buffer.append("Nombre: "+c.getString(1)+"\n");
			buffer.append("Precio: "+c.getString(2)+"\n");
			buffer.append("Fecha: "+c.getString(3)+"\n\n");*/
		}
		showMessage("Registros de cuentas", buffer.toString());
    }
    /*Cursor cursor1 = db.rawQuery("SELECT * FROM student", null);

    String[] from = new String[] {
            DBhelper.editcuenta,
            DBhelper.editnombre
    };
    int[] to = new int[] {
            R.id.editcuenta,
            R.id.editnombre,
            R.id.editprecio,
            R.id.editsaldo
    };

    SimpleCursorAdapter adapter = new SimpleCursorAdapter(
            MainActivity.this, R.layout.formato_fila, cursor1, from, to);

    adapter.notifyDataSetChanged();
    lista.setAdapter(adapter);

    // acción cuando hacemos click en item para poder modificarlo o eliminarlo
    lista.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            editcuenta = (TextView) view.findViewById(R.id.editcuenta);
            editnombre = (TextView) view.findViewById(R.id.editnombre);
            editprecio = (TextView) view.findViewById(R.id.editprecio);
            editsaldo = (TextView) view.findViewById(R.id.editsaldo);

            String aux_miembrocuenta = editcuenta.getText().toString();
            String aux_miembroNombre = editnombre.getText().toString();
            String aux_miembroprecio = editprecio.getText().toString();
            String aux_miembronsaldo = editsaldo.getText().toString();

            Intent modify_intent = new Intent(getApplicationContext(), ModificarMiembroActivity.class);
            modify_intent.putExtra("miembroId", aux_miembroId);
            modify_intent.putExtra("miembroNombre", aux_miembroNombre);
            startActivity(modify_intent);
        }
    });
}*///termina 
}
