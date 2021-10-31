package com.pm.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * @author pmcho
 */
@Route(path = "/login/login/LoginActivity")
public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login);
        NavController navController = Navigation.findNavController(this, R.id.nav_login_fragment);
//        NavigationUI.setupWithNavController(mNavView, navController);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }
}
