package nocompany.quanlytaichinhcanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nocompany.quanlytaichinhcanhan.data.DBManager;

public class CreateAccountActivity extends AppCompatActivity {

    private Button mBtn_cancel, mBtn_next;

    private DBManager dbManager;

    public static final String EMAIL1 = "email1";
    public static final String PHONENUMBER1 = "phonenumber1";
    public static final String PASSWORD1 = "password1";
    public static final String BUNDLE = "bundel";

    private EditText mEdt_email, mEdt_phonenumber, mEdt_password, mEdt_confpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        dbManager = new DBManager(this);

        initLayout();
        // Click button CANCEL
        mBtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this,CreateNameUserActivity.class);
                startActivity(intent);
            }
        });

        // Click button NEXT
        mBtn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = CheckInputData();
                switch (r){
                    case 0:
                    {
                        int s = dbManager.check_Account(mEdt_email.getText().toString(),"1");
                        if(s==0)
                        {
                            byBundle();

                        }
                        else
                        {
                            Toast toast = Toast.makeText(CreateAccountActivity.this,"Tài khoảng Email đã được sử dụng.",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        break;
                    }
                    case 1:
                    {
                        Toast toast = Toast.makeText(CreateAccountActivity.this,"Hãy nhập đầy đủ thông tin",Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                    }
                    case 2:
                    {
                        Toast toast = Toast.makeText(CreateAccountActivity.this,"Email không hợp lệ",Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                    }
                    case 3:
                    {
                        Toast toast = Toast.makeText(CreateAccountActivity.this,"Hãy nhập mật khẩu từ 6 -> 15 ký tự",Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                    }
                    case 4:
                    {
                        Toast toast = Toast.makeText(CreateAccountActivity.this,"Mật khẩu xác nhận không đúng.",Toast.LENGTH_SHORT);
                        toast.show();
                        break;
                    }
                    default:
                        break;
                }
            }
        });
    }

    //Ánh xạ Layout
    public void initLayout()
    {
        mBtn_cancel = (Button)findViewById(R.id.btn_cancel_accreateacc);
        mBtn_next=(Button)findViewById(R.id.btn_next_accreateacc);

        mEdt_email=(EditText)findViewById(R.id.edt_username_accreateacc);
        mEdt_phonenumber=(EditText)findViewById(R.id.edt_phone_accreateacc);
        mEdt_password=(EditText) findViewById(R.id.edt_pass_accreateacc);
        mEdt_confpassword=(EditText)findViewById(R.id.edt_confirm_accreateacc);
    }
    //kiểm tra dữ liệu nhập vào
    public int CheckInputData()
    {
        String t1 = mEdt_email.getText().toString();
        String t2 = mEdt_phonenumber.getText().toString();
        String t3 = mEdt_password.getText().toString();
        String t4 = mEdt_confpassword.getText().toString();

        boolean s = checkEmail(t1);
        if(t1.length()==0||t2.length()==0||t3.length()==0||t4.length()==0)
            {return 1;}
        else
        {
            if(!s)
            {
                return 2;
            }
            else
            {
                if(t3.length()<6||t3.length()>15)
                {return 3;}
                else
                {
                    if(!t3.equals(t4))
                    {return 4;}
                }
            }
        }
        return 0;
    }

    //kiểm tra đăng nhập gmail
    public boolean checkEmail(String email)
    {
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";

        Matcher matcher= Pattern.compile(validemail).matcher(email);
        if (matcher.matches()){
            return true;
        }
        return  false;
    }
    // Truyen du lieu
    public void byBundle() {
        Intent intent = new Intent(CreateAccountActivity.this, CreateViTienActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(EMAIL1, mEdt_email.getText().toString());
        bundle.putString(PHONENUMBER1, mEdt_phonenumber.getText().toString());
        bundle.putString(PASSWORD1, mEdt_password.getText().toString());
        intent.putExtra(BUNDLE, bundle);
        startActivity(intent);
    }
}
