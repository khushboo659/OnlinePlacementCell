package com.example.opc;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class fragment_stats extends Fragment {

    Activity context;
    Button button;
    ImageView image;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_fragment_stats, container, false);



        View rootView =inflater.inflate(R.layout.fragment_fragment_stats, container, false);
        ImageView imageView=(ImageView) rootView.findViewById(R.id.imgstats);
        // Inflate the layout for this fragment
       // ImageView.setImageResource(AndroidImageAssete.getmeads.get(0));
        imageView.setImageResource(R.drawable.stats);
        return rootView;
    }

    public interface OnFragmentInteractionListener {










            void onFragmentInteraction(Uri uri);
    }
}
