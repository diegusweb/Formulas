package com.util.diegus.formulas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = (Button) findViewById(R.id.calcular);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText sismax = (EditText) findViewById(R.id.sismax);
                EditText sismin = (EditText) findViewById(R.id.sismin);
                EditText diasmax = (EditText) findViewById(R.id.diasmax);
                EditText diasmin = (EditText) findViewById(R.id.diasmin);

                TextView resultado  = (TextView) findViewById(R.id.resultado);

                int add=0;
                int bdd=0;
                int cdd=0;
                int ddd=0;

                if (!isValidText(sismax.getText().toString()))
                    sismax.setError("Invalid");
                else
                    add=1;

                if (!isValidText(sismin.getText().toString()))
                    sismin.setError("Invalid");
                else
                    bdd=1;

                if (!isValidText(diasmax.getText().toString()))
                    diasmax.setError("Invalid");
                else
                    cdd=1;

                if (!isValidText(diasmin.getText().toString()))
                    diasmin.setError("Invalid");
                else
                    ddd=1;

                if(add > 0 && bdd>0 && cdd>0 && ddd>0){
                    int sismaxa = Integer.parseInt(sismax.getText().toString());
                    int sismina = Integer.parseInt(sismin.getText().toString());
                    int diasmaxa = Integer.parseInt(diasmax.getText().toString());
                    int diasmina = Integer.parseInt(diasmin.getText().toString());

                    int ppmax = sismaxa - diasmaxa;
                    int ppmin = sismina - diasmina;

                    BigDecimal numeroMax = new BigDecimal(ppmax);
                    BigDecimal numeroMin = new BigDecimal(ppmin);

                    double arriba =  (100 * (ppmax - ppmin));
                    double abajo =  ((ppmax + ppmin)/2);

                    BigDecimal di100 = new BigDecimal("100");
                    BigDecimal di1neg = new BigDecimal("-1");
                    BigDecimal resta = new BigDecimal("0.0");
                    resta = numeroMax.subtract(numeroMin);
                    BigDecimal arribaD = new BigDecimal("0.0");
                    arribaD = di100.multiply(resta);
                    di1neg = arribaD.multiply(di1neg);

                    BigDecimal abajoMin = new BigDecimal("0.0");
                    abajoMin = numeroMax.add(numeroMin);

                    BigDecimal di2 = new BigDecimal("2");
                    BigDecimal abajoMinDiv = new BigDecimal("0.0");
                    abajoMinDiv = abajoMin.divide(di2);

                    BigDecimal res = new BigDecimal("0.0");
                    // res = di1neg.divide(abajoMinDiv);

                    double ar = arribaD.doubleValue();
                    double ab = abajoMinDiv.doubleValue();


                    double r = (ar / ab);

                    resultado.setText(String.valueOf(redondear(r, 2)));
                }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public double redondear( double numero, int decimales ) {
        return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
    }

    // validating type
    private boolean isValidText(String pass) {
        if (pass != null && pass.length() > 0) {
            return true;
        }
        return false;
    }
}
