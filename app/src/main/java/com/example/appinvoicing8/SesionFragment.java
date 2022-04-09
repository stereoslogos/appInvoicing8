package com.example.appinvoicing8;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener

{
    RequestQueue rq;
    JsonRequest jrq;
    EditText correo,clave;
    Button ingresar;
    TextView registrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View iniciarSesion = inflater.inflate(R.layout.fragment_sesion, container, false);
        correo = iniciarSesion.findViewById(R.id.etcorreo);
        clave = iniciarSesion.findViewById(R.id.etclave);
        ingresar = iniciarSesion.findViewById(R.id.btningresar);
        registrar = iniciarSesion.findViewById(R.id.tvregistrar);
        rq = Volley.newRequestQueue(getContext());
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion(correo.getText().toString(),clave.getText().toString());
            }
        });
        return iniciarSesion;
    }

    private void iniciarSesion(String correo, String clave) {
        //Toast.makeText(getContext(), "Correo "+correo+" clave "+clave, Toast.LENGTH_SHORT).show();
        //http://localhost:81/invoicing/searchcustomer.php?email=elpaisarest@gmail.com&passwd=1010

        //String url = "http://172.16.60.19:81/invoicing/searchcustomer.php?email="+correo+"&passwd="+clave;
        String pieichpi = "http://172.16.60.19:81/invoicing/searchcustomer.php?email="+correo+"&passwd="+clave;
        jrq = new JsonObjectRequest(Request.Method.GET,pieichpi,null,this,this);
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "El usuario con correo "+correo.getText().toString()+"NO se ha encontrado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "El usuario con correo"+correo.getText().toString()+" se ha encontrado", Toast.LENGTH_SHORT).show();
    }
}