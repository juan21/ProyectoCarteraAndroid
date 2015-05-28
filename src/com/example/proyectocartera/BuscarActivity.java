package com.example.proyectocartera;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuscarActivity extends Activity implements OnClickListener
{
	EditText editcuenta,editnombre,editprecio,editsaldo;
	Button btnresult;
	SQLiteDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar);
       /* editcuenta=(EditText)findViewById(R.id.editcuenta);
        editnombre=(EditText)findViewById(R.id.editnombre);
        editprecio=(EditText)findViewById(R.id.editprecio);
        editsaldo=(EditText)findViewById(R.id.Editsaldo);*/
        btnresult=(Button)findViewById(R.id.button1);
        btnresult.setOnClickListener(this);
        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS student(cuenta VARCHAR,nombre VARCHAR,precio VARCHAR,saldo VARCHAR);");
    }
    
    public void onClick(View view)
    {
    	 if(view==btnresult)
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
            Toast toast = Toast.makeText(BuscarActivity.this, texto, Toast.LENGTH_LONG);
            toast.show();
			return;
		}
		StringBuffer buffer=new StringBuffer();
		while(c.moveToNext())
		{
			buffer.append("No.: "+c.getString(0)+"\n");
			buffer.append("$: "+c.getString(2)+"\n");
		}
		showMessage("Total Contenido", buffer.toString());
    }
}
