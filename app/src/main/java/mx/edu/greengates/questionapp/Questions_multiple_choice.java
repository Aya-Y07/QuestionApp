package mx.edu.greengates.questionapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import mx.edu.greengates.questionapp.data.model.WriteIntoMistaken_QuestionCSV;

public class Questions_multiple_choice extends AppCompatActivity {

    private TextView questionNumber;
    private TextView questionText;
    private TextView Timer;
    private RadioGroup rgAnswers;
    private RadioButton radioAnswer1;
    private RadioButton radioAnswer2;
    private RadioButton radioAnswer3;
    private RadioButton radioAnswer4;
    private int score;
    private int quizNum;
    private int Score;
    private String rightAnswer;
    private Timer timer;
    private long period;
    private int count;
    private SimpleDateFormat Time;
    private final int QUIZ_COUNT = 4;
    private final SimpleDateFormat dataFormat = new SimpleDateFormat("mm:ss.S", Locale.US);
    private final Handler handler = new Handler(Looper.getMainLooper());
    public ArrayList<String> finishedQuestions;
    public ArrayList<String> finishedAns;
    public ArrayList<String> result;
    private ArrayList<StringTokenizer> questions;
    private List<List<String>> quiz = null;
    private String currQuestion;
    private String currSubject;
    private String username;

    ImageView imageView;
    int imageNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_multiple_choice);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");


        questionNumber = (TextView) findViewById(R.id.questionNum);
        questionText = (TextView) findViewById(R.id.Question_text);
        rgAnswers = (RadioGroup) findViewById(R.id.radioGroup);
        radioAnswer1 = (RadioButton) findViewById(R.id.ans_a);
        radioAnswer2 = (RadioButton) findViewById(R.id.ans_b);
        radioAnswer3 = (RadioButton) findViewById(R.id.ans_c);
        radioAnswer4 = (RadioButton) findViewById(R.id.ans_d);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageNum = 1;

        quizNum = 1;
        score = 0;

    }

    public void getQuestionsFromCSV(Context context){

        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        try{
            String QUESTION_FILE ="Questions.csv";
            is = assetManager.open(QUESTION_FILE);
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            String line ="";

            StringTokenizer st = null;
            int numLine = 0;
            while((line = reader.readLine())!=null){
                if(numLine > 0){
                    st = new StringTokenizer(line,",");


                    String ID = st.nextToken();
                    String QUESTION = st.nextToken();
                    String ANSWER = st.nextToken();
                    String A0 = st.nextToken();
                    String A1 = st.nextToken();
                    String A2 = st.nextToken();
                    String A3 = st.nextToken();
                    String SOLUTION = st.nextToken();
                    String SUBJECT = st.nextToken();


                    questions.add(st);

                    quizfromCSV();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void quizfromCSV() {


        Intent intent = getIntent();
        String topic = intent.getStringExtra("Quiz");

        int coulmnNum = questions.size();

        while (coulmnNum > 0) {

            String id = questions.get(coulmnNum).nextToken(String.valueOf(0));
            if (id.contains(topic)) {

                quiz = Collections.singletonList(
                        Arrays.asList(
                                questions.get(coulmnNum).nextToken(String.valueOf(1)),
                                questions.get(coulmnNum).nextToken(String.valueOf(2)),
                                questions.get(coulmnNum).nextToken(String.valueOf(3)),
                                questions.get(coulmnNum).nextToken(String.valueOf(4)),
                                questions.get(coulmnNum).nextToken(String.valueOf(5)),
                                questions.get(coulmnNum).nextToken(String.valueOf(6)))
                );
                coulmnNum -= 1;

            } else {
                coulmnNum -= 1;
            }
        }
        showQuestionsOnDisplay();
    }


    private void showQuestionsOnDisplay() {

        questionNumber.setText(getString(R.string.question_count,quizNum));

        Random r = new Random();
        int randomNum = r.nextInt(quiz.size());
        List<String> Questions = quiz.get(randomNum);

        finishedQuestions.add(Questions.get(0));
        questionText.setText(Questions.get(0));

        questionText.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);

        rightAnswer = Questions.get(1);
        finishedAns.add(Questions.get(1));

        currQuestion = Questions.get(0);
        currSubject = Questions.get(2);
        int i = 2;

        ArrayList<List<String>> choice = new ArrayList<>();

        while (i < 6){

            choice.add(quiz.get(i));
            i += 1;
        }

        Collections.shuffle(choice);

        radioAnswer1.setText((CharSequence) choice.get(0));
        radioAnswer1.setChecked(false);
        radioAnswer1.setText((CharSequence) choice.get(1));
        radioAnswer2.setChecked(false);
        radioAnswer1.setText((CharSequence) choice.get(2));
        radioAnswer3.setChecked(false);
        radioAnswer1.setText((CharSequence) choice.get(3));
        radioAnswer4.setChecked(false);



        quiz.remove(randomNum);
    }


    protected void measureTime(Bundle savedInstanceState) {

        long delay = 0;
        period = 100;

        Timer = findViewById(R.id.txtTime);
        Timer.setText(dataFormat.format(0));

        if (null != timer) {
            timer.cancel();
            timer = null;
        }

        timer = new Timer();

        count = 0;
        Timer.setText(dataFormat.format(0));

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        count++;
                        Timer.setText(dataFormat.format(count * period));
                    }
                });
            }
        }, delay, period);
        checkAnswer();
    }

    private void checkAnswer(){


        Log.println(Log.DEBUG,"answer", "Correct answer =[" + rightAnswer + "]");
        int selected = rgAnswers.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selected);
        String userAns = radioButton.getText().toString().trim();

        String notification;

        if (userAns.equals(rightAnswer)){
            score += 1;
            notification = "correct";
            result.add("○");
        }else{
            notification = "incorrect";
            result.add("×");

            String[] questionData = {username,currQuestion,currSubject};

            WriteIntoMistaken_QuestionCSV writer = new WriteIntoMistaken_QuestionCSV(this, "mistaken_question.csv");
            try {
                writer.writeQuestionDataCSV("mistaken_question.csv", questionData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(notification);
        builder.setMessage("Answer : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizNum == QUIZ_COUNT) {

                    Intent myIntent = new Intent(Questions_multiple_choice.this, Result.class);
                    Questions_multiple_choice.this.startActivity(myIntent);

                    Score = score;
                    myIntent.putExtra("score", Score);
                    startActivity(myIntent);



                    if (null != timer) {
                        timer.cancel();
                        timer = null;
                        Timer.setText(Questions_multiple_choice.this.dataFormat.format(0));
                        Time = Questions_multiple_choice.this.dataFormat;

                    }
                    myIntent.putExtra("time", Time);
                    startActivity(myIntent);

                    Date now = new Date();
                    checkAnswer();
                    myIntent.putExtra("Date", now);
                    startActivity(myIntent);

                    myIntent.putExtra("username", username);
                    startActivity(myIntent);

                    String page_name = "Question";
                    myIntent.putExtra("Page Name", page_name);
                    startActivity(myIntent);



                } else {
                    quizNum++;
                    showQuestionsOnDisplay();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    }

    public List<String> getFinishedQuestions(){
        List<String> finishedquestions = this.finishedQuestions;
        return finishedQuestions;
    }
    public List<String> getFinishedAns(){
        List<String> finishedans = this.finishedAns;
        return finishedAns;
    }
    public List<String> getResult(){
        List<String> result = this.result;
        return result;
    }

    public List<StringTokenizer> getQuestionList(){
        ArrayList<StringTokenizer> question_list = this.questions;
        return questions;
    }



}

