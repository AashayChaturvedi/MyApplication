package com.example.gur48256.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private static final String FILE_NAME = "User_Details.txt";
    private static final int REQUEST_PERMISSION_WRITE = 1001;
    public EditText comment, fName, lName, phNo, address;
    public Button submit, display, openFile;
    public CheckBox cbox;
    public RadioGroup rg;
    public RadioButton rbtn;
    public TextView tv;
    public String comm, first, last, phone, addr, gender;
    public boolean isValidaionFailed = false, permissionGranted = false;

    @Override
    protected void onResume() {
        super.onResume();
        comment.setText("");
        fName.setText("");
        lName.setText("");
        phNo.setText("");
        address.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!permissionGranted) {
            checkPermissions();
        }

        comment = (EditText) findViewById(R.id.ecomm);
        fName = (EditText) findViewById(R.id.efName);
        lName = (EditText) findViewById(R.id.elName);
        phNo = (EditText) findViewById(R.id.eph_no);
        address = (EditText) findViewById(R.id.eAddr);
        submit = (Button) findViewById(R.id.btn);
        openFile = (Button) findViewById(R.id.btnFile);
        display = (Button) findViewById(R.id.btn_DB);
        cbox = (CheckBox) findViewById(R.id.cbox);
        rg = (RadioGroup) findViewById(R.id.rg);
        rbtn = (RadioButton) findViewById(R.id.rMale);
        tv = (TextView) findViewById(R.id.tv);

        fName.setSingleLine();
        fName.setImeOptions(EditorInfo.IME_ACTION_DONE);
        fName.setOnEditorActionListener(this);

        lName.setSingleLine();
        lName.setImeOptions(EditorInfo.IME_ACTION_DONE);
        lName.setOnEditorActionListener(this);

        phNo.setSingleLine();
        phNo.setImeOptions(EditorInfo.IME_ACTION_DONE);
        phNo.setOnEditorActionListener(this);

        SpannableString tnc = new SpannableString("Accept Terms & Conditions");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TextActivity.class));
            }
        };
        tnc.setSpan(clickableSpan, 7, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv.setText(tnc);
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        rbtn.setChecked(true);
        gender = rbtn.getText().toString();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    gender = rb.getText().toString();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comm = comment.getText().toString();
                first = fName.getText().toString();
                last = lName.getText().toString();
                phone = phNo.getText().toString();
                addr = address.getText().toString();

                boolean check = validate();
                if (check) {
                    return;
                }

                fileHandling();

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("comments", comm);
                intent.putExtra("first", first);
                intent.putExtra("last", last);
                intent.putExtra("phone", phone);
                intent.putExtra("address", addr);
                intent.putExtra("gender", gender);
                startActivity(intent);

            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DisplayDB.class));
            }
        });

        openFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFolder();
            }
        });

    }

    public void openFolder() {
        Intent intent = new Intent(MainActivity.this, OpenFile.class);
        startActivity(intent);




    }

    private boolean checkPermissions() {
        permissionGranted = true;

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE);
            return false;
        } else {
            return true;
        }
    }

    public boolean validate() {
        String msg = "";
        isValidaionFailed = false;

        if (comm.equalsIgnoreCase("")) {
            msg = "Comments not filled";
            isValidaionFailed = true;
        } else if (first.equalsIgnoreCase("")) {
            msg = "First Name not filled";
            isValidaionFailed = true;
        } else if (last.equalsIgnoreCase("")) {
            msg = "Last Name not filled";
            isValidaionFailed = true;
        } else if (phone.length() == 0) {
            msg = "Phone Number not filled";
            isValidaionFailed = true;
        } else if (phone.length() < 10) {
            msg = "Phone Number short";
            isValidaionFailed = true;
        } else if (addr.equalsIgnoreCase("")) {
            msg = "Address not filled";
            isValidaionFailed = true;
        } else if (gender.equalsIgnoreCase("")) {
            msg = "Gender not filled";
            isValidaionFailed = true;
        } else if (!cbox.isChecked()) {
            msg = "CheckBox not filled";
            isValidaionFailed = true;
        }

        if (isValidaionFailed)
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

        return isValidaionFailed;
    }

    private File getFile() {
        return new File(Environment.getExternalStorageDirectory() + "/Download", FILE_NAME);
    }

    public void fileHandling() {
        FileOutputStream fileOutputStream = null;
        File file = getFile();

        try {
            fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write((comm + " ").getBytes());
            fileOutputStream.write((first + " ").getBytes());
            fileOutputStream.write((last + " ").getBytes());
            fileOutputStream.write((phone + " ").getBytes());
            fileOutputStream.write((addr + " ").getBytes());
            fileOutputStream.write((gender + "\n\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {

        if (actionId == 0 || actionId == EditorInfo.IME_ACTION_DONE) {

            switch (textView.getId()) {
                case R.id.efName:
                    lName.requestFocus();
                    break;
                case R.id.elName:
                    phNo.requestFocus();
                    break;
                case R.id.eph_no:
                    address.requestFocus();
                    break;
            }
        }
        return false;
    }
}
