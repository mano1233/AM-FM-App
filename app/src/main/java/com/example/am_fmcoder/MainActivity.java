package com.example.am_fmcoder;

import android.content.Intent;
import android.os.Bundle;

import com.example.am_fmcoder.ui.AddBinDict;
import com.example.am_fmcoder.ui.AddDict;
import com.example.am_fmcoder.ui.Dictonaries;
import com.example.am_fmcoder.ui.Extended;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private Button[] letters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View homelayout = findViewById(R.id.home_layout);
        Map<String, Map<String, String>> letterdicts = Dictonaries.getLetterDict();
        Object[] names = letterdicts.keySet().toArray();
        letters = new Button[letterdicts.size()];
        for(int i = 0; i < letters.length; i++) {
            letters[i] = new Button(this);
            letters[i].setText((String)names[i]);
            letters[i].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            letters[i].setId(i);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            letters[i].setLayoutParams(lp);
            ((LinearLayout) homelayout).addView(letters[i]);
            letters[i].setOnClickListener(this);

        }


    }

    @Override
    public void onClick(View v) {
        sendMessage();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void newBin(){
        Intent intent = new Intent(this, AddBinDict.class);
        startActivity(intent);
    }
    private void sendMessage(){
        Intent intent = new Intent(this, SendMessage.class);
        for (Button b : letters){
            if (b.isPressed()){
                intent.putExtra("dictname", String.valueOf(b.getText()));
                startActivity(intent);
            }
        }
    }



}
