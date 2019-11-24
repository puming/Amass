package com.pm.amass.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.pm.amass.R;

/**
 * @author pmcho
 */
public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_longin);
        NavController navController = Navigation.findNavController(this, R.id.nav_login_fragment);
//        NavigationUI.setupWithNavController(mNavView, navController);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }
}
