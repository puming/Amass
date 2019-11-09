package com.pm.amass.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.pm.amass.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longin);
        NavController navController = Navigation.findNavController(this, R.id.nav_login_fragment);
//        NavigationUI.setupWithNavController(mNavView, navController);
    }
}
