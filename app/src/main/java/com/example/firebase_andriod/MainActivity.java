package com.example.firebase_andriod;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText employeeNameEdt, employeePhoneEdt, employeeAddressEdt;
    Button sendDatabtn;

//    Instances
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EmployeeInfo employeeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addData(View view) {

        // initializing our edittext and button
        employeeNameEdt = findViewById(R.id.idEdtEmployeeName);
        employeePhoneEdt = findViewById(R.id.idEdtEmployeePhoneNumber);
        employeeAddressEdt = findViewById(R.id.idEdtEmployeeAddress);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("employee");

        String name = employeeNameEdt.getText().toString();
        String phone = employeePhoneEdt.getText().toString();
        String address = employeeAddressEdt.getText().toString();

        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(address)) {
            Toast.makeText(MainActivity.this, "Please Fill In All Info", Toast.LENGTH_SHORT).show();
        }else{
            employeeInfo = new EmployeeInfo(name, phone, address);
            databaseReference.child(name).setValue(employeeInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                    employeeNameEdt.setText("");
                    employeePhoneEdt.setText("");
                    employeeAddressEdt.setText("");
                }
            });
        }

    }
}