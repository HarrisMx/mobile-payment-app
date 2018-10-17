package com.example.cccosmo.simplepayapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class main_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main_activity );
        Toolbar toolbar = (Toolbar) findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );
        final Activity activity = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById ( R.id.fab );
        fab.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                //Snackbar.make ( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                //.setAction ( "Action", null ).show ( );


                //Toast.makeText(getApplicationContext(), "Scanner Opened", Toast.LENGTH_SHORT).show();
                IntentIntegrator intentIntegrator = new IntentIntegrator ( activity );
                intentIntegrator.setDesiredBarcodeFormats ( IntentIntegrator.QR_CODE_TYPES );
                intentIntegrator.setPrompt ( "Scan" );
                intentIntegrator.setCameraId ( 0 );
                intentIntegrator.setBeepEnabled ( false );
                intentIntegrator.setBarcodeImageEnabled ( false );
                intentIntegrator.initiateScan ( );
            }
        } );


        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener ( toggle );
        toggle.syncState ( );

        NavigationView navigationView = (NavigationView) findViewById ( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener ( this );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        if (drawer.isDrawerOpen ( GravityCompat.START )) {
            drawer.closeDrawer ( GravityCompat.START );
        } else {
            super.onBackPressed ( );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ( ).inflate ( R.menu.main_activity, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ( );

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected ( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId ( );


        if (id == R.id.scan) {
            final Activity activity = this;
            //Toast.makeText(getApplicationContext(), "Scanner Opened", Toast.LENGTH_SHORT).show();
            IntentIntegrator intentIntegrator = new IntentIntegrator ( activity );
            intentIntegrator.setDesiredBarcodeFormats ( IntentIntegrator.QR_CODE_TYPES );
            intentIntegrator.setPrompt ( "Scan" );
            intentIntegrator.setCameraId ( 0 );
            intentIntegrator.setBeepEnabled ( false );
            intentIntegrator.setBarcodeImageEnabled ( false );
            intentIntegrator.initiateScan ( );
        } else if (id == R.id.shopping) {

        } else if (id == R.id.profile) {

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        drawer.closeDrawer ( GravityCompat.START );
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult ( requestCode, resultCode, data );
        if (result != null) {
            if (result.getContents ( ) == null) {
                Toast.makeText ( getApplicationContext ( ), "No data Returned", Toast.LENGTH_SHORT ).show ( );
            } else {
                Toast.makeText ( getApplicationContext ( ), result.getContents ( ), Toast.LENGTH_SHORT ).show ( );
                /*try {
                    executePost("http://196.11.240.224:8310/AmountChargingService/services/AmountCharging");
                } catch (IOException e) {
                    e.printStackTrace ( );
                }*/
            }
        } else {
            super.onActivityResult ( requestCode, resultCode, data );
        }
    }

    public void perfomPayment() {

    }

    public void perfomRefund() {

    }

    public static void executePost(String targetURL) throws IOException {
        String url = "http://example.com";
        String query = "<soapenv:Envelope xmlns:loc=\"http://www.csapi.org/schema/parlayx/payment/amount_charging/v2_1/local\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soapenv:Header>" +
                "    <RequestSOAPHeader xmlns=\"http://www.huawei.com.cn/schema/common/v2_1\">\n" +
                "      <spId>270110000648</spId>\n" +
                "      <spPassword>becfd915954f7ff327c96968a533499f</spPassword>\n" +
                "      <timeStamp>20181013124019</timeStamp>\n" +
                "    </RequestSOAPHeader>\n" +
                "  </soapenv:Header>\n" +
                "  <soapenv:Body>\n" +
                "    <loc:chargeAmount>\n" +
                "      <loc:endUserIdentifier>+27788135799</loc:endUserIdentifier>\n" +
                "      <loc:charge>\n" +
                "        <description>charge</description>\n" +
                "        <currency>ZAR</currency>\n" +
                "        <amount>1</amount>\n" +
                "      </loc:charge>\n" +
                "      <loc:referenceCode>5114689627</loc:referenceCode>\n" +
                "    </loc:chargeAmount>\n" +
                "  </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        URLConnection urlConnection = new URL ( url ).openConnection ( );
        urlConnection.setUseCaches ( false );
        urlConnection.setDoOutput ( true ); // Triggers POST.
        urlConnection.setRequestProperty ( "accept-charset", "UTF-8" );
        urlConnection.setRequestProperty ( "content-type", "text/plain" );

        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter ( urlConnection.getOutputStream ( ), "UTF-8" );
            writer.write ( query ); // Write POST query string (if any needed).
        } finally {
            if (writer != null) {
                try {
                    writer.close ( );
                } catch (IOException logOrIgnore) {
                    //Toast.makeText ( getApplicationContext ( ), "Error : " + logOrIgnore.getMessage (), Toast.LENGTH_SHORT ).show ( );
                }
            }
        }

        InputStream result = urlConnection.getInputStream ( );


    }
}
