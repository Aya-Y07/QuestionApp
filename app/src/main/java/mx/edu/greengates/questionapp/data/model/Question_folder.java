package mx.edu.greengates.questionapp.data.model;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Question_folder {

    public final int ID = 0;
    public final int QUESTION = 1;
    public final int ANSWER = 2;
    public final int A0 = 3;
    public final int A1 = 4;
    public final int A2 = 5;
    public final int A3 = 6;
    public final int SOLUTION = 7;
    Context context;

    public String filename;
    private ArrayList<String[]> document;
    public Question_folder(Context context, String filename){
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
        for (String[] row: document){
            for(int col = 0; col < row.length; col++){
                System.out.print(row[col] + " | ");
            }
            System.out.println();
        }
    }


    public List<Question> createQuestions() {

        List<Question> questionList = new ArrayList<>();
        int rowNum = 0;
        for (String[] row : document) {
            if (rowNum > 0) {
                String id = row[ID];
                String question = row[QUESTION];
                String answer = row[ANSWER];
                String a0 = row[A0];
                String a1 = row[A1];
                String a2 = row[A2];
                String a3 = row[A3];
                String solution = row[SOLUTION];
                Question questionObj = new Question(id,question,answer,a0,a1,a2,a3,solution);
                questionList.add(questionObj);
            }
            rowNum++;
        }
        return questionList;
    }





}
