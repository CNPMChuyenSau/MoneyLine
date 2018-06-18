package nocompany.quanlytaichinhcanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import nocompany.quanlytaichinhcanhan.data.DBManager;

public class SuccessfullyCreateActivity extends AppCompatActivity {

    private DBManager dbManager;
    Button mBtn_Back, mBtn_next;
    TextView mTxt_sotien, mTxt_loaitien;
    ImageView mImg_iconGT;
    String Email_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfully_create);

        dbManager = new DBManager(this);

        mBtn_Back = (Button)findViewById(R.id.btn_back_acSuccessfully);
        mBtn_next = (Button)findViewById(R.id.btn_ok_acSuccessfully);
        mTxt_sotien = (TextView)findViewById(R.id.txt_sotien_acSuccessfully);
        mTxt_loaitien  =(TextView)findViewById(R.id.txt_loaivi_acSuccessfully);
        mImg_iconGT = (ImageView)findViewById(R.id.imv_icon_acSuccessfully);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(CreateViTienActivity.BUNDLE);
            if (bundle != null) {
                int position = bundle.getInt(CreateViTienActivity.GIOITINH);
                switch (position)
                {
                    case 0:
                    {
                        mImg_iconGT.setImageResource(R.drawable.vitien_icon_1);
                        break;
                    }
                    case 1:
                    {
                        mImg_iconGT.setImageResource(R.drawable.vitien_icon_2);
                        break;
                    }
                    case 2:
                    {
                        mImg_iconGT.setImageResource(R.drawable.vitien_icon_3);
                        break;
                    }
                    case 3:
                    {
                        mImg_iconGT.setImageResource(R.drawable.vitien_icon_4);
                        break;
                    }
                        default:
                            break;
                }
                mTxt_loaitien.setText(bundle.getString(CreateViTienActivity.LOAITIEN).toString());
                mTxt_sotien.setText(bundle.getString(CreateViTienActivity.SOTIEN).toString());
                Email_ID = bundle.getString(CreateViTienActivity.EMAIL);
                Toast toast = Toast.makeText(SuccessfullyCreateActivity.this,"Thanh Cong " ,Toast.LENGTH_SHORT);
                toast.show();
            }

        }

        // xu kien click button next
        mBtn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessfullyCreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // xu kien click button back
        mBtn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.Delete_User1(Email_ID);
                Intent intent = new Intent(SuccessfullyCreateActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }



}
