package com.example.valueconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Dichiaro le View e l'edit con le quali si lavorerà
    EditText ToConvert ;
    TextView convertedCurrency ;
    TextView tv1 ;
    TextView tv2 ;

    boolean eurToDollar ;  //definisco una vaiabile booleana come riferimento per lo scambio in dollari o euro (al click su exchange)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Collego le Views (all'id di rifrimento)
        ToConvert = (EditText) findViewById(R.id.EdtToConvert);
        convertedCurrency= (TextView) findViewById(R.id.convertedCurrency);
        tv1 =(TextView)findViewById(R.id.txt1);
        tv2 =(TextView)findViewById(R.id.txt2);
        eurToDollar = true ;      // l'applicazione parte convertendo  da euro a dollaro


    }

    public void convertValue(View view) {
        //faccio un log button "convert"
        Log.d("convertValue", "Click ok!!");


        currencyConversion();

    }

    private void currencyConversion() {
        //isEmpity per dichiarare "se vuoto  il campo ins currency creo un avviso" (andava in cresh)
        if (!isEmpty(ToConvert)) {
            String daConvertirestr = ToConvert.getText().toString();  //leggo il valore da convertire  (creo una variabile tipo stringa)
            Log.d("convertValue", "valore da convertire =" + daConvertirestr);


            //trasformo  il valore  letto in tostring in double con Double.parseDouble
            Double daConvertireDouble = Double.parseDouble(daConvertirestr);

            //convertire le valute
            Double convertitoDoule;
            if (eurToDollar) {
                convertitoDoule = daConvertireDouble * 1.14213;
            } else {
                convertitoDoule = daConvertireDouble / 1.14213;
            } ;


            //scrivo il valore (risultato della conversione formattata  dollari/euro nella text view di riferimento)
            convertedCurrency.setText(String.format("%.2f", convertitoDoule));
        } else {
            Toast.makeText(getApplicationContext(),"\n" + "Insert a currency!!", Toast.LENGTH_SHORT).show();
        }


    }



    //metodo isEmpity per la gestione del cresh se è vuoto il campo ins currency ritorna il calcolo della lunghezza lunghezza
    // (.trim() toglie gli spazzi fra i caratteri o numeri)
    private boolean isEmpty(EditText euro) {
        String imput = ToConvert.getText().toString().trim();
        return imput.length() == 0 ;

    }
    //creo un button sulla viw change (onKlick) mi servirà successivamente
    // per convertire da dollari a euro
    public void exchange(View view) {
        Log.d("convertValue","Exchange value");

     //parto da euro a dollaro true se devo convertire cliccando il button exchange
     //cambierò le view da euro a dolla come segue
        if(eurToDollar){
            tv1.setText("Dollar $");
            tv2.setText("Eur");
            eurToDollar= false ;// impostata per perchè al click di scambio exchange torno a convertire da euro a dollari
            currencyConversion();

        }
        else{
            tv1.setText("Eur");
            tv2.setText("Dollar $");
            eurToDollar= true ;
            currencyConversion();
        }

    }
}