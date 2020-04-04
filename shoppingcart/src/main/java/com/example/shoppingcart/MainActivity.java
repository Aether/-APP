package com.example.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ucla on 2018/2/11.
 */

public class MainActivity extends AppCompatActivity {

    private Button btnStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btn_start_order);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

    }
}
