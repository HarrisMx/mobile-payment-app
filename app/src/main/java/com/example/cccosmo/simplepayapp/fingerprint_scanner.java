package com.example.cccosmo.simplepayapp;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class fingerprint_scanner extends AppCompatActivity {
    private TextView error;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_fingerprint_scanner );
        error = findViewById ( R.id.errorText );

        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
            KeyguardManager keyguardManager;
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService ( FINGERPRINT_SERVICE );
            if(!fingerprintManager.isHardwareDetected ()){
                error.setText ( "Fingerprint not Recognized" );
            }else if(ContextCompat.checkSelfPermission ( this, Manifest.permission.USE_BIOMETRIC ) != PackageManager.PERMISSION_GRANTED){
                error.setText ( "You have NO permission to use the fingerprint scanner" );
            }else if(!keyguardManager.isKeyguardSecure ()){
                error.setText ( "Security not set for your screen" );
            }else if(!fingerprintManager.hasEnrolledFingerprints ()){
                error.setText ( "You should add at least one finger print" );
            }else{
                error.setText ( "Please your finger on the scanner to sigin" );
                FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                fingerprintHandler.startAuth ( fingerprintManager,null );
            }


        }

    }
}
