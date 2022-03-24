package mx.edu.greengates.questionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.edu.greengates.questionapp.data.model.loginCred;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.profile_username);
        password = (EditText) findViewById(R.id.input_password);

        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);

    }

    private String password(String password){

        String token = "";

        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes("utf8"));
            token = String.format("%040x", new BigInteger(1,digest.digest()));//BingInteger 整数値を格納するためのクラス

        }catch(Exception e){
            e.printStackTrace();
        }
        return token;//return

    }

    private boolean checkCredentials(String user, String pass){
        boolean validCredential = false;
        String encryptedPass = password(pass);//暗号化したパスワード
        ArrayList<loginCred> objList = new ArrayList<loginCred>();
        AssetManager assetManager = getAssets();//assetに含まれているフォルダーに保存する
        InputStream is = null;//連続するデータを順次に必要な分だけ読む虚無もの
        try{
            is = assetManager.open("users.csv");
        }catch(IOException e){
            e.printStackTrace();//スタックトレースとはコンピュータプログラムにエラーが発生した際に直前に実行していた関数やメゾットなどの経歴を表示すること
        }
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line ="" ;
        StringTokenizer st = null;
        try{
            while((line = reader.readLine())!=null){
                st = new StringTokenizer(line,",");
                String username = st.nextToken();
                String password = st.nextToken();
                System.out.println(password + " : " + encryptedPass);
                if (user.compareTo(username) == 0 && encryptedPass.compareToIgnoreCase(password) == 0){
                    validCredential = true;
                    break;
                }

            }
        }catch (IOException e) {
            e.printStackTrace();
        }//コンパイルエラーとはコードに何かしらの不具合やバグが発生すること
        return validCredential;

    }

    public void onClick(View v)
    {
        String user = String.valueOf(username.getText());
        String pass = String.valueOf(password.getText());

        if(v == login && checkCredentials(user,pass)){//指定された名前とパスワードをこのテキストで検証するために、着信要求ごとに呼び出される
            Intent myIntent = new Intent(Login.this, Home.class);
            myIntent.putExtra("progress", 50);//optional parameters
            Login.this.startActivity(myIntent);

        }
        return;
    }














}