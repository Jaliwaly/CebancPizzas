package com.aitor.cebancpizza;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CebancPizza_portada extends FragmentActivity implements OnMapReadyCallback {
    private FirstMapFragment mFirstMapFragment;
    private Button sig, tlf, salir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cebanc_pizza_portada);
        sig = (Button) findViewById(R.id.btnSDatos);
        tlf = (Button) findViewById(R.id.btnTlf);
        salir = (Button) findViewById(R.id.btnSalir);
        mFirstMapFragment = FirstMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.map, mFirstMapFragment).commit();

        // Registrar escucha onMapReadyCallback
        mFirstMapFragment.getMapAsync(this);
        //Los diferentes onClickListeners de los botones
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzaCliente();
            }
        });
        tlf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzaTlf();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    //Metodo para lanzar la actividad del cliente
    public void lanzaCliente() {
        Intent i = new Intent(this, CebancPizza_Cliente.class);
        startActivity(i);
    }
    //Metodo para poder realizar la llamada al numero introducido en el setData
    public void lanzaTlf() {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:943001100"));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }

    //Generar y especificar el lugar del mapa
    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng cebanc = new LatLng(43.30469411639206, -2.0168709754943848);
        googleMap.addMarker(new MarkerOptions()
                .position(cebanc)
                .title("CebancPizza"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marcador));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(cebanc)
                .zoom(13)
                .build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
}
}
