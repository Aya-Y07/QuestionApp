package mx.edu.greengates.questionapp.data.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class User_folder {

    public final int Username = 0;
    public final int UserId= 1;
    public final int Password = 2;
    public final int Emailaddress = 3;
    public final int Surname = 4;
    public final int FirstName = 5;
    public final int Phone_Number = 6;

    Context context;

    public String filename;
    private ArrayList<String[]> user_document;
    public User_folder(Context context, String filename){
        this.context = context;
        this.filename = filename;

    }

    public ArrayList<String[]> questionFolder(){
        ArrayList<String[]> document = new ArrayList<>();
        try{
            InputStream inputStream = context.getAssets().open(this.filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader question_folder = new BufferedReader(inputStreamReader);
            String row;
            int row_num = 0;
            while((row = question_folder.readLine()) != null){
                String[] data = row .split(",");
                document.add(data);
            }
            question_folder.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error");
        }return document;
    }
    public void printDocument(){
        for (String[] row: user_document){
            for(int col = 0; col < row.length; col++){
                System.out.print(row[col] + " | ");
            }
            System.out.println();
        }
    }
    public static Users getUsersFromCSV(Context context){

        ArrayList<User> users = new ArrayList<User>();
        Users usersFromFile = new Users(users);

        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        try{
            String USER_FILE ="users.csv";
            is = assetManager.open(USER_FILE);
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            String line ="";

            StringTokenizer st = null;
            int numLine = 0;
            while((line = reader.readLine())!=null){
                if(numLine > 0){
                    st = new StringTokenizer(line,",");

                    String USER_ID = st.nextToken();
                    String USERNAME = st.nextToken();
                    String PASSWORD = st.nextToken();
                    String EMAIL_ADDRESS = st.nextToken();
                    String SURNAME = st.nextToken();
                    String FIRSTNAME = st.nextToken();
                    String PHONE_NUMBER = st.nextToken();


                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return usersFromFile;
    }

    public List<User> createUsers() {

        List<User> userList = new ArrayList<>();
        int rowNum = 0;
        for (String[] row : user_document) {
            if (rowNum > 0) {
                String userID = row[UserId];
                String username = row[Username];
                String password = row[Password];
                String firstname = row[FirstName];
                String surname = row[Surname];
                String phone_number = row[Phone_Number];
                String email_address = row[Emailaddress];
                User userObj = new User(userID,username,password,firstname,surname,phone_number,email_address);
                userList.add(userObj);
            }
            rowNum++;
        }
        return userList;
    }
}
