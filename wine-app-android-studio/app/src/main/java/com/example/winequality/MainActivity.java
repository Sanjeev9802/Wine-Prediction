package com.example.winequality;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText fixed_acidity,
            volatile_acidity,
            citric_acid,
            residual_sugar,
            chlorides,
            free_so2,
            total_so2,
            density,ph,sulphates,alcohol;
    TextView ans;
    Button save,reset;
    String url="https://wine-quality-app.onrender.com/predict";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        fixed_acidity=findViewById(R.id.fixed_acidity);
        volatile_acidity=findViewById(R.id.volatile_acidity);
        citric_acid=findViewById(R.id.citric_acid);
        residual_sugar=findViewById(R.id.residual_sugar);
        chlorides=findViewById(R.id.chlorides);
        free_so2=findViewById(R.id.free_so2);
        total_so2=findViewById(R.id.total_so2);
        density=findViewById(R.id.density);
        ph=findViewById(R.id.ph);
        sulphates=findViewById(R.id.sulphates);
        alcohol=findViewById(R.id.alcohol);
        save=findViewById(R.id.submit);
        ans=findViewById(R.id.quality);
        reset=findViewById(R.id.reset);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fa = fixed_acidity.getText().toString();
                String va = volatile_acidity.getText().toString();
                String ca = citric_acid.getText().toString();
                String rs = residual_sugar.getText().toString();
                String cl = chlorides.getText().toString();
                String freeSo2 = free_so2.getText().toString();
                String totalso2 = total_so2.getText().toString();
                String dens = density.getText().toString();
                String p = ph.getText().toString();
                String sul = sulphates.getText().toString();
                String alc = alcohol.getText().toString();

                if (fa.isEmpty()) {
                    fixed_acidity.setError("ENTER FIXED ACIDITY");
                } else if (va.isEmpty()) {
                    volatile_acidity.setError("ENTER VOLATILE ACIDITY");
                } else if (ca.isEmpty()) {
                    citric_acid.setError("ENTER CITRIC ACID");
                } else if (rs.isEmpty()) {
                    residual_sugar.setError("Enter Residual Sugar");
                } else if (cl.isEmpty()) {
                    chlorides.setError("Enter chlorides");
                } else if (freeSo2.isEmpty()) {
                    free_so2.setError("ENTER FREE SO2 VALUES");
                } else if (totalso2.isEmpty()) {
                    total_so2.setError("Enter TOTAL SO2 VALUES");
                } else if (dens.isEmpty()) {
                    density.setError("Enter density values");
                } else if (p.isEmpty()) {
                    ph.setError("Enter PH values");
                } else if (sul.isEmpty()) {
                    sulphates.setError("Enter Sulphates values");
                } else if (alc.isEmpty()) {
                    alcohol.setError("Enter Alcohol Value");
                } else {
                    StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                            url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    JSONObject jsonObject;
                                    try {
                                        jsonObject = new JSONObject(response);
                                        String data = jsonObject.getString("quality");
                                        if (data.equals("1")) {
                                            ans.setText("GOOD QUALITY WINE");

                                        } else {
                                            ans.setText("BAD QUALITY WINE");
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }) {

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> p = new HashMap<String, String>();
                            p.put("fixed_acidity", fixed_acidity.getText().toString());
                            p.put("volatile_acidity", volatile_acidity.getText().toString());
                            p.put("citric_acid", citric_acid.getText().toString());
                            p.put("residual_sugar", residual_sugar.getText().toString());
                            p.put("chlorides", chlorides.getText().toString());
                            p.put("free_So2", free_so2.getText().toString());
                            p.put("total_So2", total_so2.getText().toString());
                            p.put("density", density.getText().toString());
                            p.put("ph", ph.getText().toString());
                            p.put("sulphates", sulphates.getText().toString());
                            p.put("alcohol", alcohol.getText().toString());

                            return p;


                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(stringRequest);


                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixed_acidity.setText("");
                volatile_acidity.setText("");
                citric_acid.setText("");
                residual_sugar.setText("");
                chlorides.setText("");
                free_so2.setText("");
                total_so2.setText("");
                density.setText("");ph.setText("");
                sulphates.setText("");
                alcohol.setText("");
                ans.setText("");

            }
        });

    }
}