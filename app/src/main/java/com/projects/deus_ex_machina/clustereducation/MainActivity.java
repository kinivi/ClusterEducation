package com.projects.deus_ex_machina.clustereducation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

//TODO(2) Add logo
//TODO(3) Add new colors
public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener,
        NavigationView.OnNavigationItemSelectedListener {

    private static final String ANONYMOUS = "Enroller";
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername;
    private DatabaseReference mDatabase;
    private Uri mPhotoURI;
    private String mUserEmail;
    private NavigationView navigationView;
    private int prevID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prevID = R.id.nav_dashboard;

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //--------------------Building Google API and retrieving data about user ----------------
        //Creating Google API for Client
        mGoogleApiClient = new GoogleApiClient.Builder(MainActivity.this)
                .enableAutoManage(MainActivity.this /* FragmentActivity */,
                        MainActivity.this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        mUsername = mFirebaseUser.getDisplayName();
        mUserEmail = mFirebaseUser.getEmail();
        mPhotoURI = null;

        if (mFirebaseUser.getPhotoUrl() != null) {
            mPhotoURI = mFirebaseUser.getPhotoUrl();
        }
        //------------------------------------------------------------------------------

        //Setting layout for main activity on create
        setContentView(R.layout.activity_main);

        //Inflate MainFragment to Activity
        setMainFragment();


        //Setting toolbar to activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting Navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Setting navigation drawer appearance
        setNavigationDrawerAppearance();
    }

    private void setNavigationDrawerAppearance() {

        View headerView = navigationView.getHeaderView(0);
        ImageView imgView = (ImageView) headerView.findViewById(R.id.profileImage);

        // Download photo and set to image
        if (mPhotoURI != null) {
            Context context = imgView.getContext();
            Picasso.with(context).load(mPhotoURI).into(imgView);
        }

        //set user name to navDrawer
        TextView name = headerView.findViewById(R.id.user_nav_name);
        name.setText(mUsername);

        //set user email to navDrawer
        TextView email = headerView.findViewById(R.id.user_nav_email);
        email.setText(mUserEmail);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    //Implement functionality of menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:


                mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mUsername = ANONYMOUS;
                startActivity(new Intent(this, ChooserActivity.class));
                MainActivity.this.finish();
                return true;

            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Show toast if connection failed
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    //Implement functionality of Navigation Drawer links
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard && prevID != id) {

            prevID = id;
            new Handler().postDelayed(new Runnable() {

                public void run() {
                    setMainFragment();
                }
            }, 200);

        } else if (id == R.id.nav_gallery) {
            prevID = id;
            new Handler().postDelayed(new Runnable() {

                public void run() {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.dynamic_content_container, new PollConstructor()).commit();
                }
            }, 200);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send && prevID != id) {

            new Handler().postDelayed(new Runnable() {

                public void run() {
                    Intent intent = new Intent(MainActivity.this, BackButtonActivity.class);
                    intent.putExtra("TypeOfFragment", "FeedbackFragment");
                    startActivity(intent);
                }
            }, 200);
            prevID = id;


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setMainFragment() {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.dynamic_content_container, new MainFragment())
                .disallowAddToBackStack()
                .commit();

    }
}


