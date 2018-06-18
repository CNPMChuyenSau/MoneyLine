package nocompany.quanlytaichinhcanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nocompany.quanlytaichinhcanhan.data.DBManager;

public class ForgetPassworkActivity extends AppCompatActivity {

    Button mBtn_back, mBtn_SeePasswork;
    EditText mEdt_Email, mEdt_Phonenumber;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_passwork);
        dbManager = new DBManager(this);
        init();
        mBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPassworkActivity.this,CreateNameUserActivity.class);
                startActivity(intent);
            }
        });

        mBtn_SeePasswork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdt_Email.getText().toString().length()==0||mEdt_Phonenumber.getText().toString().length()==0)
                {
                    Toast toast = Toast.makeText(ForgetPassworkActivity.this,"Hãy nhập đầy đủ thông tin",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    String s = dbManager.Search_Passwod(mEdt_Email.getText().toString(),mEdt_Phonenumber.getText().toString());
                    if(s.equals("0"))
                    {
                        Toast toast = Toast.makeText(ForgetPassworkActivity.this,"Tên Email sai.",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else
                    {
                        if(s.equals("1"))
                        {
                            Toast toast = Toast.makeText(ForgetPassworkActivity.this,"Phone number sai.",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            Toast toast = Toast.makeText(ForgetPassworkActivity.this,"Password: "+ s,Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                }
            }
        });


    }

    public void init()
    {
        mBtn_back = (Button)findViewById(R.id.btn_back_acForgetpass);
        mBtn_SeePasswork=(Button)findViewById(R.id.btn_seepass_acForgetpass);
        mEdt_Email=(EditText)findViewById(R.id.edt_username_acforgetpass);
        mEdt_Phonenumber=(EditText)findViewById(R.id.edt_phoneNumb_acforgetpass);
    }
}
