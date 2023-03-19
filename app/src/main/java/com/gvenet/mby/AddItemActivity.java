package com.gvenet.mby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gvenet.mby.DAO.ItemDAO;
import com.gvenet.mby.pojos.Item;

public class AddItemActivity extends AppCompatActivity {

    Button btnCancel;
    Button btnAdd;
    EditText titre;
    EditText description;
    EditText url;

    Spinner spinner;
    ItemDAO itemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        titre = findViewById(R.id.titre);
        description = findViewById(R.id.description);
        url = findViewById(R.id.url);
        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnAdd);
        spinner = findViewById(R.id.Spinner);

        setBtnCancel();
        setSpinner();
        setBtnAdd();
    }

    private void setSpinner(){
        String[] arraySpinner = new String[] {"Sport", "Cuisine", "Comédie", "Musique"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setBtnCancel(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setBtnAdd() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("", "CLICCCCK");
                if(titre.getText().length() <= 0 || description.getText().length() <= 0 || url.getText().length() <= 0){
                    Context context = getApplicationContext();
                    CharSequence text = "Un ou plusieurs champs est/sont vide/s";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Log.d("", "valable");
                    String titleStr = titre.getText().toString();
                    String typeStr = spinner.getSelectedItem().toString();
                    String descriptionStr = description.getText().toString();
                    String urlStr = url.getText().toString();

                    Item newItem = new Item(titleStr,typeStr,descriptionStr,urlStr);
                    itemDAO = new ItemDAO(getApplicationContext());

                    itemDAO.add(newItem);

                    Log.d("", "ajouté avec : " + titleStr + " " + typeStr + " "+ descriptionStr + " " + urlStr );
                    finish();
                }
            }
        });
    }
}