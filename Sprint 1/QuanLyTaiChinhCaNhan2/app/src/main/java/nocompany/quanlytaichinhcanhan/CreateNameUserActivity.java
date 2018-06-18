package nocompany.quanlytaichinhcanhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import nocompany.quanlytaichinhcanhan.data.DBManager;

public class CreateNameUserActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    private EditText mEdt_username;
    private EditText mEdt_password;
    private Button mBtn_login;
    private Button mBtn_forgetpassword;
    private Button mBtn_createacc;
    private DBManager dbManager;

    public static final String EMAIL_GOOGLEAPI = "email_googleapi";
    public static final String BUNDLE = "bundel";

    public static final int SIGN_IN_CODE=777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_name_user);
        dbManager = new DBManager(this);

        initlayout();

        //chuyển sang Activity Create User
        mBtn_createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CreateNameUserActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        //Chuyển sang Activity Forget password
        mBtn_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateNameUserActivity.this, ForgetPassworkActivity.class);
                startActivity(intent);
            }
        });

        // Click button login
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdt_username.getText().toString().length()==0||mEdt_password.getText().toString().length()==0)
                {
                    Toast toast = Toast.makeText(CreateNameUserActivity.this,"Hãy nhập đầy đủ thông tin",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    int s = dbManager.check_Account(mEdt_username.getText().toString(),mEdt_password.getText().toString());
                    switch (s)
                    {
                        case 0:
                        {
                            Toast toast = Toast.makeText(CreateNameUserActivity.this,"Tên Email sai.",Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                        case 1:
                        {
                            Toast toast = Toast.makeText(CreateNameUserActivity.this,"Password không đúng.",Toast.LENGTH_SHORT);
                            toast.show();
                            break;
                        }
                        case 2:
                        {
                            Intent intent = new Intent(CreateNameUserActivity.this, MainActivity.class);
                            startActivity(intent);
                            break;
                        }
                        default:
                            break;
                    }

                }

            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==777)
        {
            GoogleSignInResult result =  Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    //Hàm duy trì tình trạng đăng nhập
    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess())
        {
            // Đã đăng nhập thành công, hiển thị trạng thái đăng nhập.
            goMainScreen(result);
        }else {
            // Đã đăng xuất, hiển thị trạng thái đăng xuất.
            Toast.makeText(this, R.string.no_log_in, Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen(GoogleSignInResult result) {
        GoogleSignInAccount googleSignInAccount = result.getSignInAccount();
        String email = googleSignInAccount.getEmail();
        byBundle(email);
    }

    // Anh xa layout
    public void initlayout()
    {
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        mBtn_forgetpassword = (Button)findViewById(R.id.btn_forget_acloginmain);
        mBtn_login=(Button)findViewById(R.id.btn_login_acloginmain);
        mBtn_createacc=(Button)findViewById(R.id.btn_createacc_acloginmain);

        mEdt_password=(EditText)findViewById(R.id.edt_password_acloginmain);
        mEdt_username=(EditText)findViewById(R.id.edt_username_acloginmain);
    }

    public void byBundle(String email1) {
        int s = dbManager.check_Account(email1,"1");
        if(s==0)
        {
            Intent intent = new Intent(CreateNameUserActivity.this, CreateViTien1Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString(EMAIL_GOOGLEAPI, email1);
            intent.putExtra(BUNDLE, bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(CreateNameUserActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}
