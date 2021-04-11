package com.example.modepaiement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonCard, buttonPayPal;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        buttonCard = findViewById(R.id.buttonCard);
        buttonCard.setOnClickListener(v -> {
            CardPaymentFragment cardPaymentFragment = CardPaymentFragment.newInstance();
            cardPaymentFragment.show(fm, "fragment_card_payment");
        });
        buttonPayPal = findViewById(R.id.buttonPayPal);
        buttonPayPal.setOnClickListener(v -> {
            PayPalPaymentFragment payPalPaymentFragment = PayPalPaymentFragment.newInstance();
            payPalPaymentFragment.show(fm, "fragment_card_payment");
        });
    }
}