package com.example.cccosmo.simplepayapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {

    private Button signIn;
    private Button signUp;
    private TextView fingerprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        signIn = findViewById ( R.id.Bsignin );
        signUp = findViewById ( R.id.Bsignup );
        fingerprint = findViewById ( R.id.fingerprint );

        signIn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( login.this, main_activity.class );
                startActivity ( intent );
            }
        } );

        fingerprint.setOnClickListener ( new View.OnClickListener (){
            @Override
            public void onClick(View view){
                Intent finger = new Intent ( login.this, fingerprint_scanner.class );
                startActivity ( finger );
            }
        } );
    }
}
