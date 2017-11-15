package com.projects.deus_ex_machina.clustereducation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//TODO(2) Add logo
//TODO(3) Add new colors
public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private static final String ANONYMOUS = "Enroller";
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private String mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        //-----------------------------Checking Authentication -------------------------
        //Creating Google API for Client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();

        //Checking if user is signed in
//        if (mFirebaseUser == null) {
//            // Not signed in, launch the Sign In activity
//            startActivity(new Intent(this, ChooserActivity.class));
//            finish();
//        } else {
//            //Getting username and photo
//            mUsername = mFirebaseUser.getDisplayName();
//            if (mFirebaseUser.getPhotoUrl() != null) {
//                String mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
//            }
//        }
        //------------------------------------------------------------------------------

        setContentView(R.layout.activity_main);

        //TODO Decide what to do with it
        //getSupportActionBar().setElevation(0); Deleting shadows for Status bar

        //Getting ID of viewPager and tabLayout
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);


        FragmentPageAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //setting icons for TabLayout
        tabLayout.setupWithViewPager(viewPager);
        int[] imageResId = {
                R.drawable.ic_dashboard_plate,
                R.drawable.ic_subjects,
                R.drawable.ic_list };
        for (int i = 0; i < imageResId.length; i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * Functionality of bottom menu
     *
     * @param item
     * @return what to do
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out:
                mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mUsername = ANONYMOUS;
                startActivity(new Intent(this, ChooserActivity.class));
                return true;

            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * If connection failed
     *
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
