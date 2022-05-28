package ru.mirea.gribkova.mireaproject1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Maps extends Fragment {






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       View view =  inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment supportMapFragment =(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                LatLng Stromynka = new LatLng(55.793259, 37.701448);
                googleMap.addMarker(new MarkerOptions().position(Stromynka).title("РТУ МИРЭА, 1947г, ул.Стромынка, 20, Москва, 55.793259, 37.701448 "));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Stromynka,10));

                LatLng Vernadka1 = new LatLng(55.669986, 37.480409);
                googleMap.addMarker(new MarkerOptions().position(Vernadka1).title("РТУ МИРЭА, 1947г, пр.Вернадского, 78 строекие 3, Москва, 55.669986, 37.480409"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Vernadka1,10));

                LatLng Vernadka2 = new LatLng(55.661445,37477049);
                googleMap.addMarker(new MarkerOptions().position(Vernadka2).title("РТУ МИРЭА, 1947г, пр.Вернадского, 86, Москва, 55.661445, 37477049 "));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Vernadka2,10));

                LatLng Pirogovskay = new LatLng(55.731582, 37.574840);
                googleMap.addMarker(new MarkerOptions().position(Pirogovskay).title("РТУ МИРЭА, 1947г,Малая Пироговская ул., 1с5, Москва, 55.731582, 37.574840"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Pirogovskay,10));




                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        //googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });

        return view;
    }



}