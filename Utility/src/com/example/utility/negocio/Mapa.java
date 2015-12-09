package com.example.utility.negocio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


import com.example.utility.R;
import com.example.utility.R.id;
import com.example.utility.R.layout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Mapa extends FragmentActivity implements LocationListener{

     GoogleMap googleMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa);
		
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if(status!=ConnectionResult.SUCCESS){
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else { 
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            googleMap = fm.getMap();
            googleMap.setMyLocationEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);

            Location location = locationManager.getLastKnownLocation(provider);

            if(location!=null){
                onLocationChanged(location);
            }
            
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
        }
		
		
		
	}

	

	@Override
	public void onLocationChanged(Location location) {
		 double latitude = location.getLatitude();
	        double longitude = location.getLongitude();

	        LatLng latLng = new LatLng(latitude, longitude);

	        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
	        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
	    }

	    @Override
	    public void onProviderDisabled(String provider) {
	    }

	    @Override
	    public void onProviderEnabled(String provider) {
	    }

	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    }
	}


	