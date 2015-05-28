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

public class EditartexActivity extends Activity implements OnClickListener
{
	EditText editcuenta,editnombre,editprecio,editsaldo;
	ImageButton btneliminar,btneditar,btnbuscar,btnver,btnfechas;
	SQLiteDatabase db;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editartex);
        editcuenta=(EditText)findViewById(R.id.editcuenta);
        editnombre=(EditText)findViewById(R.id.editnombre);
        editprecio=(EditText)findViewById(R.id.editprecio);
        editsaldo=(EditText)findViewById(R.id.Editsaldo);
        btnfechas=(ImageButton)findViewById(R.id.imageButton5);
        btneliminar=(ImageButton)findViewById(R.id.imageButton1);
        btneditar=(ImageButton)findViewById(R.id.imageButton2);
        btnbuscar=(ImageButton)findViewById(R.id.imageButton3);
        btnver=(ImageButton)findViewById(R.id.imageButton4);
        btnfechas.setOnClickListener(this);
        btneliminar.setOnClickListener(this);
        btneditar.setOnClickListener(this);
        btnbuscar.setOnClickListener(this);
        //btnver.setOnClickListener(this);
        this.btnver=(ImageButton)findViewById(R.id.imageButton4);
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS student(cuenta VARCHAR,nombre VARCHAR,precio VARCHAR,saldo VARCHAR);");
    }
    public void onClick(View view)
    {
    	if(view==btnfechas)
    	{
    		this.verfecha();
    	}else if(view==btneliminar)
    	{
    		this.delete();
    	}else if(view==btneditar)
    	{
			this.modify();
    	}else if(view==btnbuscar)
    	{
    		this.view();
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
    private void clearText()
    {
    	editcuenta.setText("");
    	editnombre.setText("");
    	editprecio.setText("");
    	editsaldo.setText("");
    	editcuenta.requestFocus();
    }
    private void verfecha()
    {
		if(editsaldo.getText().toString().trim().length()==0)
		{
			CharSequence texto = "Introduzca la fecha correcta";
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			return;
		}
		Cursor c=db.rawQuery("SELECT * FROM student WHERE saldo='"+editsaldo.getText()+"'", null);
		if(c.moveToFirst())
		{
			editcuenta.setText(c.getString(0));
			editnombre.setText(c.getString(1));
			editprecio.setText(c.getString(2));
		}
		else
		{
			CharSequence texto = "Fecha invalida";
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			clearText();
		}
    }
    private void delete()
    {
    	if(editcuenta.getText().toString().trim().length()==0)
		{
    		CharSequence texto = "Introduce codigo para continuar";
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			return;
		}
		Cursor c=db.rawQuery("SELECT * FROM student WHERE cuenta='"+editcuenta.getText()+"'", null);
		if(c.moveToFirst())
		{
			db.execSQL("DELETE FROM student WHERE cuenta='"+editcuenta.getText()+"'");
			CharSequence texto = "Se elimino correctamente";
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
		}
		else
		{
			CharSequence texto = "No se encontro el codigo";
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
		}
		clearText();
    }
    
    private void modify()
    {
		if(editcuenta.getText().toString().trim().length()==0)
		{
			CharSequence texto = "Introduzca el codigo";
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
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
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
		}
		else
		{
			CharSequence texto = "No existe el No. de cuenta";
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
		}
		clearText();
    }
    
    private void view()
    {
		if(editcuenta.getText().toString().trim().length()==0)
		{
			CharSequence texto = "Introduzca el No. de cuenta";
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
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
            Toast toast = Toast.makeText(EditartexActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			clearText();
		}
    }
    public void btnver (View ve) {
    	if (ve==btnver){
  		 Intent registros = new Intent(getApplicationContext(), RegistrosActivity.class);
      	startActivity(registros);
    	}
  	}

}

