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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EdicionesActivity extends Activity implements OnClickListener
{
	EditText editcuenta,editnombre,editprecio,editsaldo;
	ImageButton btnagregar,btnlista;
	SQLiteDatabase db;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ediciones);
        editcuenta=(EditText)findViewById(R.id.editcuenta);
        editnombre=(EditText)findViewById(R.id.editnombre);
        editprecio=(EditText)findViewById(R.id.editprecio);
        editsaldo=(EditText)findViewById(R.id.Editsaldo);
        btnagregar=(ImageButton)findViewById(R.id.imageButton5);
        btnlista=(ImageButton)findViewById(R.id.imageButton4);
        this.btnlista=(ImageButton)findViewById(R.id.imageButton4);
        btnagregar.setOnClickListener(this);
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS student(cuenta VARCHAR,nombre VARCHAR,precio VARCHAR,saldo VARCHAR);");
    }
    
    public void onClick(View view)
    {
    	if(view==btnagregar)
    	{
    		this.insert();
    	}
    }
    private void clearText()
    {
    	editcuenta.setText("");
    	editnombre.setText("");
    	editprecio.setText("");
    	editsaldo.setText("");
    	editcuenta.requestFocus();
    }
    
    private void insert()
    {
		if(editcuenta.getText().toString().trim().length()==0||
	 		   editnombre.getText().toString().trim().length()==0||
	 		   editprecio.getText().toString().trim().length()==0||
	 		  editsaldo.getText().toString().trim().length()==0
	   	)
		{
			CharSequence texto = "Introduzca los valores";
            Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
 			return;
 		}
 		db.execSQL(
			"INSERT INTO student VALUES('"+
			editcuenta.getText()+"','"+
			editnombre.getText()+"','"+
			editprecio.getText()+"','"+
			editsaldo.getText()+"');"
		);
 		CharSequence texto = "Agregado con Exito";
        Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
        toast.show();
 		clearText();
    }
    
    private void delete()
    {
    	if(editcuenta.getText().toString().trim().length()==0)
		{
    		CharSequence texto = "Introduce codigo para continuar";
            Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			return;
		}
		Cursor c=db.rawQuery("SELECT * FROM student WHERE cuenta='"+editcuenta.getText()+"'", null);
		if(c.moveToFirst())
		{
			db.execSQL("DELETE FROM student WHERE cuenta='"+editcuenta.getText()+"'");
			CharSequence texto = "Se elimino correctamente";
            Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
		}
		else
		{
			CharSequence texto = "No se encontro el codigo";
            Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
		}
		clearText();
    }
    
    private void modify()
    {
		if(editcuenta.getText().toString().trim().length()==0)
		{
			CharSequence texto = "Introduzca el codigo";
            Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			return;
		}
		Cursor c=db.rawQuery("SELECT * FROM student WHERE cuenta='"+editcuenta.getText()+"'", null);
		if(c.moveToFirst())
		{
			db.execSQL(
				"UPDATE student SET nombre='"+editnombre.getText()+"',"+
				"precio='"+editprecio.getText()+"',"+
				"saldo='"+editsaldo.getText()+
				"' WHERE "+
				"cuenta='"+editcuenta.getText()+"'"
			);
			CharSequence texto = "Se modifico correctamente";
            Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
		}
		else
		{
			CharSequence texto = "No existe el No. de cuenta";
            Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
		}
		clearText();
    }
    
    private void view()
    {
		if(editcuenta.getText().toString().trim().length()==0)
		{
			CharSequence texto = "Introduzca el No. de cuenta";
            Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			return;
		}
		Cursor c=db.rawQuery("SELECT * FROM student WHERE cuenta='"+editcuenta.getText()+"'", null);
		if(c.moveToFirst())
		{
			editnombre.setText(c.getString(1));
			editprecio.setText(c.getString(2));
			editsaldo.setText(c.getString(3));
		}
		else
		{
			CharSequence texto = "No. de cuenta invalida";
            Toast toast = Toast.makeText(EdicionesActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			clearText();
		}
    }
    public void btnver (View v) {
    	if (v==btnlista){
  		 Intent registros = new Intent(getApplicationContext(), RegistrosActivity.class);
      	startActivity(registros);
    	}
  	}

}

