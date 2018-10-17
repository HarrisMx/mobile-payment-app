package com.example.cccosmo.simplepayapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler  extends  FingerprintManager.AuthenticationCallback{
    private Context context;

    public FingerprintHandler(Context context){
        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal ();

        fingerprintManager.authenticate ( cryptoObject, cancellationSignal , 0 , this, null );


    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("There was an Auth Error, " + errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update ( "Authentication Failed..", false );
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update ( "Error, + " +  helpString, false );
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update ( "Authentication Succeeded ", true );
    }

    private void update(String s, boolean b) {
        ImageView imageView = ((Activity)context).findViewById ( R.id.icon );
        TextView textView = ((Activity)context).findViewById ( R.id.errorText );

        textView.setText ( s );

        if(b == false){
            textView.setTextColor( ContextCompat.getColor ( context, R.color.colorAccent ) );
        }else{
            textView.setTextColor( ContextCompat.getColor ( context, R.color.colorGreenish) );
            imageView.setImageResource(R.drawable.ic_done);
        }
    }
}
