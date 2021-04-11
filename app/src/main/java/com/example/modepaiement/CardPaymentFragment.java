package com.example.modepaiement;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link DialogFragment} subclass.
 * Use the {@link CardPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardPaymentFragment extends DialogFragment {

    EditText editTextName, editTextCardNumber, editTextCVCCode, editTextExpiryDate;
    Button buttonSendCardInfo;

    public CardPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment CardPaymentFragment.
     */
    public static CardPaymentFragment newInstance() {
        CardPaymentFragment fragment = new CardPaymentFragment();
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
        View view = inflater.inflate(R.layout.fragment_card_payment, container, false);
        editTextName = view.findViewById(R.id.editTextName);
        editTextCardNumber = view.findViewById(R.id.editTextCardNumber);
        editTextCVCCode = view.findViewById(R.id.editTextCVCCode);
        editTextExpiryDate = view.findViewById(R.id.editTextExpiryDate);
        buttonSendCardInfo = view.findViewById(R.id.buttonSendCardInfo);
        buttonSendCardInfo.setOnClickListener(v -> {
            String name, cardNumber, cvc, expiryDate;
            Boolean flag = true;
            name = editTextName.getText().toString();
            if (!name.matches("[a-zA-Z -]+")) {
                Toast.makeText(getContext(), "Name is invalid", Toast.LENGTH_SHORT).show();
                flag = false;
            }
            cardNumber = editTextCardNumber.getText().toString();
            if (!cardNumber.matches("[0-9]{16}")){
                Toast.makeText(getContext(), "Card number is invalid", Toast.LENGTH_SHORT).show();
                flag = false;
            }
            cvc = editTextCVCCode.getText().toString();
            if (!cvc.matches("[0-9]{3}")){
                Toast.makeText(getContext(), "CVC code is invalid", Toast.LENGTH_SHORT).show();
                flag = false;
            }
            expiryDate = editTextExpiryDate.getText().toString();
            if (!expiryDate.matches("([0][0-9])|([1][0-2])/[0-9]{2}")){
                Toast.makeText(getContext(), "Expiry date is invalid", Toast.LENGTH_SHORT).show();
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
        String credentials, name, cardNumber;
        name = editTextName.getText().toString();
        cardNumber = editTextCardNumber.getText().toString();
        if (cardNumber.length() > 4)
        {
            cardNumber = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        }

        credentials = getString(R.string.credentials_name) + name + "\n" + getString(R.string.credentials_card_number) + cardNumber;
        return credentials;
    }
}