package com.prominente.android.vittal.fragments;


import android.app.MediaRouteButton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.prominente.android.vittal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFormFragment extends Fragment {

    private String[] paymentsMode;
    private ArrayAdapter<String> adapter;
    private Spinner paymentsModeSpinners;
    private View currentView;
    private View cashView;
    private View defaultView;
    private View checkView;
    private View inChashView;
    private View creditCardOrCbuView;

    public PaymentFormFragment() {
        // Required empty public constructor
    }

    public static PaymentFormFragment newInstance() {
        PaymentFormFragment fragment = new PaymentFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_form, container, false);

        //View
        inChashView         = view.findViewById(R.id.view_in_cash);
        cashView            = view.findViewById(R.id.view_cash);
        checkView           = view.findViewById(R.id.view_check);
        creditCardOrCbuView = view.findViewById(R.id.view_credit_card_or_cbu);
        defaultView         = new View(getContext());
        currentView         = defaultView;
        //
        paymentsMode = new String[] {
                "Tipo de pago", "Tarjeta de credito/CBU", "Cheque", "Efectivo", "Contado"
        };

        paymentsModeSpinners= (Spinner) view.findViewById(R.id.fr_payment_form_spn_payment_mode);
        adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, paymentsMode);
        paymentsModeSpinners.setAdapter(adapter);

        paymentsModeSpinners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentView.setVisibility(View.GONE);
                switch (position) {
                    case 1: currentView  = creditCardOrCbuView; break;
                    case 2: currentView  = checkView; break;
                    case 3: currentView  = cashView; break;
                    case 4: currentView  = inChashView; break;
                    default: currentView = defaultView; break;
                }
                currentView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

}
