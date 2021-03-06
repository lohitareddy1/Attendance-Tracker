package com.example.s528162.attendance;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
public class HomeActivity extends AppCompatActivity {
    private Button scan;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        scan=(Button)findViewById(R.id.scan);
        logout=(Button)findViewById(R.id.button2);
        final Activity activity= this;
        scan.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                IntentIntegrator integrator= new IntentIntegrator(activity);
                integrator. setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                HomeActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
        {
            if (result.getContents()== null)
            {
                Toast.makeText(this, "You cancelled the scan", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
        else
        {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
