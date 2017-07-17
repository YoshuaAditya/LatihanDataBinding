package com.example.yoshua145150201111044.databinding.view;

import android.Manifest;
import android.app.Activity;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yoshua145150201111044.databinding.R;
import com.example.yoshua145150201111044.databinding.adapter.WisataAdapter;
import com.example.yoshua145150201111044.databinding.model.WisataMapsModel;
import com.example.yoshua145150201111044.databinding.model.WisataModel;
import com.example.yoshua145150201111044.databinding.retrofit.MapsInterface;
import com.example.yoshua145150201111044.databinding.retrofit.TempatInterface;
import com.example.yoshua145150201111044.databinding.viewmodel.WisataViewModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.yoshua145150201111044.databinding.view.FragmentContainer.activityContainerBinding;
import static com.example.yoshua145150201111044.databinding.view.FragmentContainer.snackbar;

//untuk maps dari new google maps, lalu lihat xml file google api key untuk divalidasi -8.269927, 111.762231 -6.926988, 113.007218
//https://developer.android.com/training/location/retrieve-current.html untuk memakai FusedLocationProviderClient
public class MapsActivity extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    MapView mMapView;
    public ArrayList<WisataMapsModel> wisataModels = new ArrayList<>();
    public ArrayList<Address> addresses = new ArrayList<>();
    private GoogleMap googleMap;
    Geocoder geocoder;
    Menu optionsMenu;
    Menu optionsMenu2;
    private GoogleApiClient googleApiClient;
    //posisi alun alun merdeka malang
    LatLng latLng = new LatLng(-7.982929, 112.631333);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //untuk memberikan opsi pada actionbar dari fragment
        setHasOptionsMenu(true);
        //mencoba proses retrofit
        ambilRetrofit();

        //set title toolbar
        activityContainerBinding.myAwesomeToolbar.setTitle("Map");

        View rootView = inflater.inflate(R.layout.activity_maps2, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return rootView;
    }

    private void ambilRetrofit() {

        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(true);
//        pDialog.setCanceledOnTouchOutside(false);
        //listener saat dialog dicancel
//        pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                Toast.makeText(activity,R.string.loading_error,Toast.LENGTH_SHORT).show();
//            }
//        });
        pDialog.show();

        snackbarInit();

        //inisialisasi retrofit dengan builder, dengan mengambil url lalu diconvert ke gson
        Retrofit retrofit = new Retrofit.Builder().baseUrl(activityContainerBinding.getRoot().getResources().getString(R.string.link_file_marker))
                .addConverterFactory(GsonConverterFactory.create()).build();
        //mengambil interface yang mempunyai @GET Call<List<WisataModel>>
        MapsInterface tempatInterface = retrofit.create(MapsInterface.class);


//        geocoder = new Geocoder(getActivity());
//        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
//                .addConnectionCallbacks(this)
////                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .addApi(Places.GEO_DATA_API)
//                .addApi(Places.PLACE_DETECTION_API)
//                .build();
//        mGoogleApiClient.connect();

        //mendapatkan call list dari method TempatInterface.get
        Call<List<WisataMapsModel>> callData = tempatInterface.getWisataModels();
        //memasukkan data ke callData dengan enqueue dari Callback
        callData.enqueue(new Callback<List<WisataMapsModel>>() {
            @Override
            public void onResponse(Call<List<WisataMapsModel>> call, Response<List<WisataMapsModel>> response) {
                if (response.isSuccessful()) {
                    int i = 0;
                    for (WisataMapsModel wisataModel : response.body()) {
                        wisataModels.add(wisataModel);
                        i++;
                    }
                }


                mMapView.onResume(); // needed to get the map to display immediately


                // Build the map.
                mMapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap mMap) {
                        googleMap = mMap;

                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        //tombol my location dan zoom
                        googleMap.setMyLocationEnabled(true);
                        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                        googleMap.getUiSettings().setZoomControlsEnabled(true);


                        for (int i=0;i<wisataModels.size();i++){
                            LatLng sydney1 = new LatLng(wisataModels.get(i).latitude
                                    ,wisataModels.get(i).longitude);

                            //membuat marker pada map
                            if(wisataModels.get(i).lokasi==1){
                                googleMap.addMarker(new MarkerOptions()
                                        .position(sydney1)
                                        .title(wisataModels.get(i).nama)
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                            }
                            else if(wisataModels.get(i).lokasi==2){
                                googleMap.addMarker(new MarkerOptions()
                                        .position(sydney1)
                                        .title(wisataModels.get(i).nama)
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                            }
                            else{
                                googleMap.addMarker(new MarkerOptions()
                                        .position(sydney1)
                                        .title(wisataModels.get(i).nama)
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                            }
                        }
                        // For zooming automatically to the location of the marker

                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(wisataModels.get(0).latitude,wisataModels.get(0).longitude)).zoom(12).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


//                        for (int i=0;i<15;i++){
//                            //mencoba menggunakan geocoder untuk mendapatkan posisi geografis dengan nama lokasi
//                            try {
//                                address1 = geocoder.getFromLocationName(wisataModels.get(i).nama + " " + wisataModels.get(i).lokasi, 1);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            if (address1 != null) {
//                                LatLng sydney1 = new LatLng(address1.get(0).getLatitude(),address1.get(0).getLongitude());
//
//                                //membuat marker pada map
//                                if(wisataModels.get(i).lokasi.contains("Batu")){
//                                    googleMap.addMarker(new MarkerOptions()
//                                            .position(sydney1)
//                                            .title(address1.get(0).getFeatureName())
//                                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
//                                }
//                                else if(wisataModels.get(i).lokasi.contains("Kab")){
//                                    googleMap.addMarker(new MarkerOptions()
//                                            .position(sydney1)
//                                            .title(address1.get(0).getFeatureName())
//                                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
//                                }
//                                else{
//                                    googleMap.addMarker(new MarkerOptions()
//                                            .position(sydney1)
//                                            .title(address1.get(0).getFeatureName())
//                                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
//                                }
//                            }
//
//                        }


                    }
                });

                //location manager untuk mendapatkan GPS location
                final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(getContext())) {
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
//                    // Setting Dialog Message
//                    alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
//
//                    // On pressing Settings button
//                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog,int which) {
//                            enableGPS();
//                        }
//                    });
//
//                    // on pressing cancel button
//                    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.cancel();
//                            Toast.makeText(getContext(), "Turn On GPS to know your location",Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                    // Showing Alert Message
//                    alertDialog.show();
                    enableGPS();
                }

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<WisataMapsModel>> call, Throwable t) {
                pDialog.dismiss();
                snackbar.show();
            }
        });

    }

    //on GPS
    private void enableGPS() {

        //inisialisasi googleapiclient
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(getContext())
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle bundle) {

                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            googleApiClient.connect();
                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {

                            Log.d("Location error","Location error " + connectionResult.getErrorCode());
                        }
                    }).build();
            googleApiClient.connect();

            //setting cara location request
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);

            //result dari mendapatkan lokasi gps
            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                //mengeluarkan dialog untuk menyalakan gps jika belum nyala
                                status.startResolutionForResult(getActivity(), 1);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                    }
                }
            });
        }

    }

//    //hasil dari ok no dari location confirm
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        switch (requestCode) {
//            case 1:
//                switch (resultCode) {
//                    case Activity.RESULT_CANCELED: {
//                        break;
//                    }
//                    default: {
//                        Toast.makeText(getContext(), "GPS on",Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                }
//                break;
//        }
//
//    }

    //tes GPS ada tidak
    private boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null)
            return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null)
            return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onConnected(Bundle connectionHint) {

    }
    /**
     * Handles suspension of the connection to the Google Play services client.
     */
    @Override
    public void onConnectionSuspended(int cause) {
        Log.d("saaa", "Play services connection suspended");
    }

    /**
     * Handles failure to connect to the Google Play services client.
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        // Refer to the reference doc for ConnectionResult to see what error codes might
        // be returned in onConnectionFailed.
        Log.d("saaa", "Play services connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //menutup snackbar saat pindah fragment
        snackbar.dismiss();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    //snackbar ngebug g bisa dishow dua kali, sehingga perlu buat snackbar baru untuk dishow
    public void snackbarInit(){
        //inisialisasi snackbar
        snackbar = Snackbar
                .make(activityContainerBinding.fragmentContainer, "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ambilRetrofit();
            }
        });
    }

    @Override //untuk menambahkan opsi pada action bar jika ada
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.map, menu);

        //memberi tanda checked pada lingkaran untuk menu dalam menu pakai submenu
        menu.getItem(0).getSubMenu().getItem(0).setChecked(true);
        menu.getItem(1).setChecked(true);
        optionsMenu=menu;
    }

    @Override //memberi event handler saat opsi dipilih
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.normal== item.getItemId()) {
            optionsMenu.getItem(1).setChecked(true);
            googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);
            return true;
        }else if (R.id.satelite== item.getItemId()) {
            optionsMenu.getItem(2).setChecked(true);
            googleMap.setMapType(googleMap.MAP_TYPE_SATELLITE);
            return true;
        }else if (R.id.terrain== item.getItemId()) {
            optionsMenu.getItem(3).setChecked(true);
            googleMap.setMapType(googleMap.MAP_TYPE_TERRAIN);
            return true;
        }else if (R.id.hybrid == item.getItemId()) {
            optionsMenu.getItem(4).setChecked(true);
            googleMap.setMapType(googleMap.MAP_TYPE_HYBRID);
            return true;
        }
        if(optionsMenu.getItem(1).isChecked()) {
            //untuk submenu
            if (R.id.map_normal == item.getItemId()) {
                optionsMenu.getItem(0).getSubMenu().getItem(0).setChecked(true);
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_map_normal));
                return true;
            } else if (R.id.map_silver == item.getItemId()) {
                optionsMenu.getItem(0).getSubMenu().getItem(1).setChecked(true);
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_map_silver));
                return true;
            } else if (R.id.map_retro == item.getItemId()) {
                optionsMenu.getItem(0).getSubMenu().getItem(2).setChecked(true);
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_map_retro));
                return true;
            } else if (R.id.map_dark == item.getItemId()) {
                optionsMenu.getItem(0).getSubMenu().getItem(3).setChecked(true);
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_map_dark));
                return true;
            } else if (R.id.map_night == item.getItemId()) {
                optionsMenu.getItem(0).getSubMenu().getItem(4).setChecked(true);
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_map_night));
                return true;
            } else if (R.id.map_aubergine == item.getItemId()) {
                optionsMenu.getItem(0).getSubMenu().getItem(5).setChecked(true);
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_map_aubergine));
                return true;
            } else if (R.id.map_pastel == item.getItemId()) {
                optionsMenu.getItem(0).getSubMenu().getItem(6).setChecked(true);
                googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_map_pastel));
                return true;
            }
        }
        else{
            Toast.makeText(getActivity(),"Styling only works in normal map type",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}

/*
A JSON style declaration consists of the following elements:

featureType (optional) - the features to select for this style modification. Features are geographic characteristics on the map, including roads, parks, bodies of water, and more. If you don't specify a feature, all features are selected.
elementType (optional) - the property of the specified feature to select. Elements are sub-parts of a feature, including labels and geometry. If you don't specify an element, all elements of the feature are selected.
stylers - the rules to apply to the selected features and elements. Stylers indicate the color, visibility, and weight of the feature. You can apply one or more stylers to a feature.
*
* contoh
[
  {
    "featureType": "all",
    "stylers": [
      { "color": "#C0C0C0" }
    ]
  },{
    "featureType": "road.arterial",
    "elementType": "geometry",
    "stylers": [
      { "color": "#CCFFFF" }
    ]
  },{
    "featureType": "landscape",
    "elementType": "labels",
    "stylers": [
      { "visibility": "off" }
    ]
  }
]
 *  */