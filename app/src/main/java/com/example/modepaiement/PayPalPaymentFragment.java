package com.example.modepaiement;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link PayPalPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayPalPaymentFragment extends DialogFragment {

    EditText editTextUsername, editTextPassword;
    Button buttonSendPayPalInfo;

    public PayPalPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment PayPalPaymentFragment.
     */
    public static PayPalPaymentFragment newInstance() {
        PayPalPaymentFragment fragment = new PayPalPaymentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_pal_payment, container, false);
        editTextUsername = view.findViewById(R.id.editTextUsername);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonSendPayPalInfo = view.findViewById(R.id.buttonSendPayPalInfo);
        buttonSendPayPalInfo.setOnClickListener(v -> {
            String username, password;
            Boolean flag = true;
            username = editTextUsername.getText().toString();
            if (!username.matches("[a-zA-Z -][a-zA-Z0-9]+")) {
                Toast.makeText(getContext(), "Username is invalid", Toast.LENGTH_SHORT).show();
                flag = false;
            }
            password = editTextPassword.getText().toString();
            if (!password.matches("[a-zA-Z0-9 -]{8}[a-zA-Z0-9 -]*")){
                Toast.makeText(getContext(), "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                flag = false;
            }
            if (flag) {
                TextView textViewCredentials = (TextView) getActivity().findViewById(R.id.textViewCredentials);
                textViewCredentials.setText(infoToString());
                getDialog().dismiss();
            }
        });
        return  view;
    }

    private String infoToString() {
        String credentials, username;
        username = editTextUsername.getText().toString();
        credentials = getString(R.string.credentials_username) + username;
        return credentials;
    }
}