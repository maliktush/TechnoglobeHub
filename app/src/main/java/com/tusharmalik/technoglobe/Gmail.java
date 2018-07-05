package com.tusharmalik.technoglobe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Gmail extends Activity {

    Button btnSend,btnCompose;
    EditText etemailId,etPass,etTo,etSub,etBody;
    String emailId,spass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = (Button)findViewById(R.id.btnSend);
        btnCompose = (Button)findViewById(R.id.btnCompose);
        etemailId = (EditText)findViewById(R.id.etemail);
        etPass = (EditText)findViewById(R.id.etPass);
        etTo = (EditText)findViewById(R.id.etTo);
        etSub = (EditText)findViewById(R.id.etSubject);
        etBody = (EditText)findViewById(R.id.etBody);
        final LinearLayout layout= (LinearLayout)findViewById(R.id.composeLinear);


        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String sub = etSub.getText().toString();
                String to = etTo.getText().toString();
                String body = etBody.getText().toString();


//                try {
//                    GmailSender sender = new GmailSender(emailId, spass);
//                    sender.sendMail(sub,body,emailId,to);
//                    Toast.makeText(getApplicationContext(),"Mail has been sent.",Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(),"Error! Try again later.",Toast.LENGTH_SHORT).show();
//                }

            }
        });
        btnCompose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                emailId = etemailId.getText().toString();
                spass = etPass.getText().toString();
                if(emailId.equals("") || spass.equals("")){
                    Toast.makeText(getApplicationContext(),"Email ID and Password fields are mandatory.",Toast.LENGTH_SHORT).show();
                }else{
                    layout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
