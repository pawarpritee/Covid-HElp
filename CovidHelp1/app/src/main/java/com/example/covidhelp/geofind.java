package com.example.covidhelp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class geofind extends AppCompatActivity {

    private Button b,yesbtn;
    private TextView t,txt,textv3;
    private EditText shopnm;
    private LocationManager locationManager;
    private LocationListener listener;
    Geocoder geocoder;
    ProgressBar progressBar;
    List<Address> addresses;
    String data="noop";
    Double latitude=18.944620;
    Double longitude=72.822278;
    DatabaseReference reference,reference1;
    private long s_id=0;
    UserInfo userInfo;
    Shopkepeer shopkepeer;
    int post;
    public String city,postal,area,country;
    TextView phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.find_society);
        progressBar=findViewById(R.id.progress);
        t = (TextView) findViewById(R.id.textView);
        b = (Button) findViewById(R.id.button);
       // shopnm=findViewById(R.id.fullName);
       // phone=findViewById(R.id.phone);
        data="nopp";
        Bundle bundle=getIntent().getExtras();
        data=bundle.get("data").toString();
        txt=findViewById(R.id.txt);
        yesbtn=findViewById(R.id.yesbtn);
        shopnm=findViewById(R.id.shopnm);
        textv3=findViewById(R.id.textView3);
        reference= FirebaseDatabase.getInstance().getReference().child("UserInfo");
        reference1= FirebaseDatabase.getInstance().getReference().child("Shopkepeer");
         shopkepeer=new Shopkepeer();
        userInfo=new UserInfo();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        geocoder=new Geocoder(this, Locale.getDefault());
        if(!data.equals("Customer")) {
             shopnm.setVisibility(View.VISIBLE);
             textv3.setVisibility(View.VISIBLE);
        }
            listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                progressBar.setVisibility(View.GONE);
                t.append("\n " + location.getLongitude() + " " + location.getLatitude());
                latitude=location.getLatitude();
                longitude=location.getLongitude();
                try {
                    addresses=geocoder.getFromLocation(latitude,longitude,1);
                    String address=addresses.get(0).getAddressLine(0);
                     area=addresses.get(0).getLocality();
                     city=addresses.get(0).getAdminArea();
                     country=addresses.get(0).getCountryName();
                     postal=addresses.get(0).getPostalCode();

                    String fulladd=address+", "+area+", "+city+", "+country+", "+postal;
                    txt.setText(fulladd);
                    yesbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!data.equals("Customer")) {
                                Intent intent = new Intent(geofind.this, Home2Actiivity.class);
                                System.out.println("Value of data is !!!!!"+data);
                                if(shopnm!=null) {
                                    shopkepeer.setShopname(shopnm.getText().toString().trim());
                                }
                                else
                                {
                                    Toast.makeText(geofind.this,"Please enter ur shopname",Toast.LENGTH_SHORT).show();
                                }
                                shopkepeer.setLatitude(latitude);
                                shopkepeer.setLogitude(longitude);
                                shopkepeer.setCity(city);
                                shopkepeer.setCountry(country);
                                shopkepeer.setArea(area);
                                post = Integer.parseInt(postal);
                                shopkepeer.setPostal(post);
                                reference1.push().setValue(shopkepeer);
                                intent.putExtra("data",data);
                                startActivity(intent);
                            }
                            else
                            {
                                Intent intent = new Intent(geofind.this, Home2Actiivity.class);
                                System.out.println("Value of data is !!!!!"+data);
                                userInfo.setLatitude(latitude);
                                userInfo.setLogitude(longitude);
                                userInfo.setCity(city);
                                userInfo.setCountry(country);
                                userInfo.setArea(area);
                                post = Integer.parseInt(postal);
                                userInfo.setPostal(post);
                                reference.push().setValue(userInfo);
                                intent.putExtra("data",data);
                                intent.putExtra("User_city",city);
                                startActivity(intent);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();

                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                progressBar.setVisibility(View.VISIBLE);
                locationManager.requestLocationUpdates("gps", 5000, 0, listener);
            }
        });
    }
}

