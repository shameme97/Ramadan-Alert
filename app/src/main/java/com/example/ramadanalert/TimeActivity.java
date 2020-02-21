package com.example.ramadanalert;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class TimeActivity extends AppCompatActivity {
    private static final String TAG = "tag";
    //url
    String url;
    // Tag used to cancel the request
    String tag_json_obj = "json_obj_req";
    //progressDialog
    ProgressDialog pDialog;

    TextView mFajrTv, mDhuhrTv, mAsrTv, mMaghribTv, mIshaTv, mLocationTv, mDateTv;
    EditText mSearchEt;
    Button mSearchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        mFajrTv    = findViewById(R.id.fajrTv);
        mDhuhrTv   = findViewById(R.id.dhuhrTv);
        mAsrTv     = findViewById(R.id.asrTv);
        mMaghribTv = findViewById(R.id.maghribTv);
        mIshaTv    = findViewById(R.id.ishaTv);
        mLocationTv= findViewById(R.id.locationTv);
        mDateTv    = findViewById(R.id.dateTv);
        mSearchEt  = findViewById(R.id.searchET);
        mSearchBtn = findViewById(R.id.searhBtn);

        //handle button click
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mLocation = mSearchEt.getText().toString().trim();
                //if it is null
                if (mLocation.isEmpty()){
                    Toast.makeText(TimeActivity.this, "Please enter location", Toast.LENGTH_SHORT).show();
                }
                else {
                    url = "https://muslimsalat.com/"+mLocation+".json?key=caddd6bdb5b61013b50c750bf53ba9e8";
                    //process to get location
                    searchLocation();
                }
            }
        });






    }

    private void searchLocation() {


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //ger location
                            String country = response.get("country").toString();
                            String state = response.get("state").toString();
                            String city = response.get("city").toString();
                            String location = country +", "+ state +", "+ city;
                            //get date
                            String date = response.getJSONArray("items").getJSONObject(0).get("date_for").toString();


                            String mFajar   = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String mDhuhr   = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String mAsr     = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String mMaghrib = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String mIsha    = response.getJSONArray("items").getJSONObject(0).get("isha").toString();
                            //see this data to textViews
                            mFajrTv.setText(mFajar);
                            mDhuhrTv.setText(mDhuhr);
                            mAsrTv.setText(mAsr);
                            mMaghribTv.setText(mMaghrib);
                            mIshaTv.setText(mIsha);
                            mLocationTv.setText(location);
                            mDateTv.setText(date);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(TimeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                pDialog.hide();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


    }
}
