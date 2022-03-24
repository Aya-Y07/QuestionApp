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

public class User_record_folder {

    public final int UserID = 0;
    public final int ID = 1;
    public final int Question_ID_Mistaken = 2;
    public final int Score = 3;
    public final int Accuracy_rate = 4;
    public final int Time_taken = 5;
    public final int Date = 6;

    Context context;

    public String filename;
    private ArrayList<String[]> document_mistake;
    public User_record_folder(Context context, String filename){
        this.context = context;
        this.filename = filename;

    }

    public ArrayList<String[]> questionFolder(){
        ArrayList<String[]> document_mistake = new ArrayList<>();
        try{
            InputStream inputStream = context.getAssets().open(this.filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader question_folder = new BufferedReader(inputStreamReader);
            String row;
            int row_num = 0;
            while((row = question_folder.readLine()) != null){
                String[] data = row .split(",");
                document_mistake.add(data);
            }
            question_folder.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error");
        }return document_mistake;
    }
    public void printDocument(){
        for (String[] row: document_mistake){
            for(int col = 0; col < row.length; col++){
                System.out.print(row[col] + " | ");
            }
            System.out.println();
        }
    }
    public static Users_record getRecordsFromCSV(Context context){

        ArrayList<User_record> mistakes = new ArrayList<User_record>();
        Users_record recordsFromFile = new Users_record(mistakes);

        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        try{
            String USER_RECORD_FILE = "Users_record.csv";
            is = assetManager.open(USER_RECORD_FILE);
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            String line ="";

            StringTokenizer st = null;
            int numLine = 0;
            while((line = reader.readLine())!=null){
                if(numLine > 0){
                    st = new StringTokenizer(line,",");

                    String UserID = st.nextToken();
                    String ID = st.nextToken();
                    String QUESTION_ID_MISTAKEN = st.nextToken();
                    String SCORE = st.nextToken();
                    String ACCURACY_RATE = st.nextToken();
                    String TIME_TAKEN = st.nextToken();
                    String DATE = st.nextToken();

                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return recordsFromFile;
    }

    public List<User_record> createRecords() {

        List<User_record> recordList = new ArrayList<>();
        int rowNum = 0;
        for (String[] row : document_mistake) {
            if (rowNum > 0) {
                String id = row[ID];
                String user_id = row[UserID];
                String question_id_mistaken = row[Question_ID_Mistaken];
                String score = row[Score];
                String accuracy_rate = row[Accuracy_rate];
                String time_taken = row[Time_taken];
                String date = row[Date];

                User_record recordObj = new User_record(id,user_id,question_id_mistaken,score,accuracy_rate,time_taken,date);
                recordList.add(recordObj);
            }
            rowNum++;
        }
        return recordList;
    }
}
