package nocompany.quanlytaichinhcanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import nocompany.quanlytaichinhcanhan.adapter.GioitinhAdapter;
import nocompany.quanlytaichinhcanhan.data.DBManager;
import nocompany.quanlytaichinhcanhan.model.Item_Gioitinh;
import nocompany.quanlytaichinhcanhan.model.Username;

public class CreateViTienActivity extends AppCompatActivity {
    EditText mEdt_sotien, mEdt_tenvi;
    Button mBtn_next,mBtn_back;
    Spinner spinner_Gioitinh;


    private DBManager dbManager;

    String email;

    public static final String EMAIL= "email";
    public static final String GIOITINH = "1";
    public static final String SOTIEN ="sotien";
    public static final String LOAITIEN = "loaitien";
    public static final String BUNDLE = "bundel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vi_tien);
        dbManager = new DBManager(this);

        //Anh xa layout
        mBtn_back=(Button)findViewById(R.id.btn_back_acTaovi);
        mBtn_next = (Button)findViewById(R.id.btn_next_acTaovi);
        mEdt_sotien=(EditText)findViewById(R.id.edt_sotien_acTaovi);
        mEdt_tenvi=(EditText)findViewById(R.id.edt_loaivi_acTaovi);
        spinner_Gioitinh = (Spinner)findViewById(R.id.spn_listItem_acTaovi);

        //custom item gioi tinh
        ArrayList<Item_Gioitinh> arrayList = new ArrayList<Item_Gioitinh>();
        arrayList.add(new Item_Gioitinh(R.drawable.vitien_icon_1,"Nam 1"));
        arrayList.add(new Item_Gioitinh(R.drawable.vitien_icon_2,"Nam 2"));
        arrayList.add(new Item_Gioitinh(R.drawable.vitien_icon_3, "Nu 1"));
        arrayList.add(new Item_Gioitinh(R.drawable.vitien_icon_4, "Nu 2"));
        GioitinhAdapter peopleAdapter = new GioitinhAdapter(this, R.layout.spiner_user_account_activity, arrayList);
        spinner_Gioitinh.setAdapter(peopleAdapter);



        //Click xu kien go back
        mBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateViTienActivity.this,CreateAccountActivity.class);
                startActivity(intent);
            }
        });
        mBtn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdt_sotien.getText().length()==0||mEdt_tenvi.getText().length()==0)
                {
                    Toast toast = Toast.makeText(CreateViTienActivity.this,"Hãy nhập đầy đủ thông tin",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    int position = spinner_Gioitinh.getSelectedItemPosition();
                    String gt = String.valueOf(position);
                    Username username = createUsername(gt);
                    if (username != null) {
                        dbManager.addData(username);
                    }
                }
                byBundle();
            }

        });

    }
    private Username createUsername(String Gt) {
        //Nhan du lieu tu activity tao username
        String sEmail, sPhonenumber, sPassword;
        sEmail = "111111";
        sPhonenumber = "111";
        sPassword = "111";
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(CreateAccountActivity.BUNDLE);
            if (bundle != null) {
                email = bundle.getString(CreateAccountActivity.EMAIL1);

                sEmail = bundle.getString(CreateAccountActivity.EMAIL1);
                sPhonenumber = bundle.getString(CreateAccountActivity.PHONENUMBER1).toString();
                sPassword = bundle.get(CreateAccountActivity.PASSWORD1).toString();
                Toast toast = Toast.makeText(CreateViTienActivity.this,"Thanh Cong " + sEmail +" - "+ sPhonenumber + " - "+sPassword,Toast.LENGTH_SHORT);
                toast.show();
            }


        }
        String sTenvi = mEdt_tenvi.getText().toString();
        String sSotien = mEdt_sotien.getText().toString();

        //Username username = new Username(sEmail,sPhonenumber,sPassword,Gt,sTenvi,sSotien);
        Username username = new Username(sEmail,sPhonenumber,sPassword,Gt,sTenvi,sSotien);
        return username;
    }

    public void byBundle() {
        Intent intent = new Intent(CreateViTienActivity.this, SuccessfullyCreateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(GIOITINH,spinner_Gioitinh.getSelectedItemPosition());
        bundle.putString(SOTIEN, mEdt_sotien.getText().toString());
        bundle.putString(LOAITIEN, mEdt_tenvi.getText().toString());
        bundle.putString(EMAIL, email);
        intent.putExtra(BUNDLE, bundle);
        startActivity(intent);
    }
}
