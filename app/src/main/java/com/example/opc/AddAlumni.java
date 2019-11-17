package com.example.opc;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class AddAlumni extends Fragment {

    private EditText name,email,password,branch,passYear,currentPos,ID;
    private Button register;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference rootref = db.getReference();
    private DatabaseReference alumniRef =  rootref.child("Alumni");



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_add_alumni, container, false);

        mAuth = FirebaseAuth.getInstance();
        name = v.findViewById(R.id.addAlumniName);
        email = v.findViewById(R.id.addAlumniEmail);
        password = v.findViewById(R.id.addAlumniPass);
        ID = v.findViewById(R.id.addAlumniId);
        branch = v.findViewById(R.id.addAlumniBranch);
        currentPos = v.findViewById(R.id.AlumniCurrentPos);
        passYear = v.findViewById(R.id.addPassYearAlumni);
        register = v.findViewById(R.id.addAlumniRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
                addAlumniInDatabase();
            }
        });


        return v;
    }


    public void createAccount() {
        final String myEmail = email.getText().toString();
        final String myPassword = password.getText().toString();
        if (myEmail.length() <= 0 || myPassword.length() <= 0)
            Toast.makeText(getActivity(), "please fill required fields", Toast.LENGTH_SHORT).show();
        else{
        mAuth.createUserWithEmailAndPassword(myEmail, myPassword)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
                            Toast.makeText(getActivity(), "Sign Up Successful", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Sign Up failed.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

    }



    public void addAlumniInDatabase()
    {
        final String myEmail = email.getText().toString().trim();
        final String myname = name.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        final String id = ID.getText().toString().trim();
        final String alBranch = branch.getText().toString().trim();
        final String currentPOS = currentPos.getText().toString().trim();
        final String passyear = passYear.getText().toString().trim();

        if(myEmail.length()<=0 || myname.length()<=0 || pass.length()<=0 ||
                id.length()<=0 || alBranch.length()<=0 || currentPOS.length()<=0 || passyear.length()<=0 ){
            Toast.makeText(getActivity(),"please fill required fields",Toast.LENGTH_SHORT).show();
    }
        HashMap<String,String > AlumniMap = new HashMap<String, String>();
        AlumniMap.put("name",myname);
        AlumniMap.put("email",myEmail);
        AlumniMap.put("id",id);
        AlumniMap.put("branch",alBranch);
        AlumniMap.put("current",currentPOS);
        AlumniMap.put("passyear",passyear);

        int index = myEmail.indexOf('@');
        String dbIdOftpo = myEmail.substring(0,index);

        alumniRef.child(dbIdOftpo).setValue(AlumniMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(getActivity(),"Registration Successfull",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
            }
        });


    }


}
