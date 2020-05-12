package com.shunplus.navigation;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shunplus.navigation.base.BaseActivity;
import com.shunplus.navigation.ui.test.LiftCycleTest;

public class MainActivity extends BaseActivity {

    private BottomNavigationView navView;



    @Override
    protected void onResume() {
        super.onResume();
        getLifecycle().addObserver(new LiftCycleTest());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
