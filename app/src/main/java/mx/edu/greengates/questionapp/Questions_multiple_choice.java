package mx.edu.greengates.questionapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;


import mx.edu.greengates.questionapp.data.model.Question;
import mx.edu.greengates.questionapp.data.model.Questions;
import mx.edu.greengates.questionapp.data.model.Question_folder;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Questions_multiple_choice extends AppCompatActivity implements View.OnClickListener{

    private TextView questionNumber;
    private TextView questionText;
    private RadioGroup rgAnswers;
    private RadioButton radioAnswer1;
    private RadioButton radioAnswer2;
    private RadioButton radioAnswer3;
    private RadioButton radioAnswer4;
    private Button btnEndQuiz;
    private Button btnTimer;
    private ImageButton btnNext;
    private ImageButton btnBack;
    private int score;
    private int quizNum;
    private final int Quiz_length = 4;
    private Questions quiz;
    private Question TempQuestion;
    private int rightAnswerCount;
    private String rightAnswer;
    private Timer timer;
    LocalTime time;

    ImageView imageView;

    int imageNum;
    int [] Topic_1_Images = {
            R.drawable.topic_1_1,
            R.drawable.topic_1_2,
            R.drawable.topic_1_3,
            R.drawable.topic_1_4,
    };
    int [] Topic_2_Images = {
            R.drawable.topic_2_1,
            R.drawable.topic_2_2,
            R.drawable.topic_2_3,
            R.drawable.topic_2_4,
    };

    int [] Topic_3_Images = {
            R.drawable.topic_3_1,
            R.drawable.topic_3_2,
            R.drawable.topic_3_3,
            R.drawable.topic_3_4,
    };

    int [] Topic_4_Images = {
            R.drawable.topic_4_1,
            R.drawable.topic_4_2,
            R.drawable.topic_4_3,
            R.drawable.topic_4_4,
    };

    ArrayList<Question>TempQuestionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_multiple_choice);

        // Receiving information from previous activity
        Intent intent = getIntent();
        String topic = intent.getStringExtra("Quiz");


        questionNumber = (TextView) findViewById(R.id.questionNum);
        rgAnswers = (RadioGroup) findViewById(R.id.radioGroup);
        radioAnswer1 = (RadioButton) findViewById(R.id.ans_a);
        radioAnswer2 = (RadioButton) findViewById(R.id.ans_b);
        radioAnswer3 = (RadioButton) findViewById(R.id.ans_c);
        radioAnswer4 = (RadioButton) findViewById(R.id.ans_d);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageNum = 1;

        quiz = Question_folder.getQuestionsFromCSV(this);
        quizNum = 1;
        score = 0;


        btnTimer = (Button) findViewById(R.id.btn_start);
        btnTimer.setOnClickListener(this);
        btnNext = (ImageButton) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
        btnBack = (ImageButton) findViewById(R.id.btn_go_back);
        btnBack.setOnClickListener(this);
        btnEndQuiz = (Button) findViewById(R.id.btn_end_quiz);
        btnEndQuiz.setOnClickListener(this);
        btnEndQuiz.setEnabled(false);


//update question display
        String line ="";
        int i = 0;
        BufferedReader reader = null;
        try{
            while((line = reader.readLine()) !=null){
                TempQuestion = quiz.getQuestion(i);
                if (TempQuestion.getId() == topic){
                    TempQuestionList.add(TempQuestion);

                    i += 1;
                }else{
                    i += 1;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        showQuestionOnDisplay();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickStart(View view) {
        if (timer != null) {
            return;
        }
        TextView textViewJudge = (TextView)findViewById(R.id.txtTime);
        final Handler handler = new Handler();
        time = LocalTime.of(0, 0);
        int period = 100;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time = time.plusNanos((long) (period * Math.pow(10, 6)));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String fmt = time.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
                        textViewJudge.setText(fmt);
                    }
                });
            }
        }, 0, period);
    }

    public void showQuestionOnDisplay() {
        questionNumber.setText(getString(R.string.question_count,quizNum));
        rightAnswer = TempQuestion.getAnswer();
        String[] options = TempQuestion.getOptions();
        Collections.shuffle(Arrays.asList(options));
        radioAnswer1.setText(options[0]);
        radioAnswer1.setChecked(false);
        radioAnswer2.setText(options[1]);
        radioAnswer2.setChecked(false);
        radioAnswer3.setText(options[2]);
        radioAnswer3.setChecked(false);
        radioAnswer4.setText(options[3]);
        radioAnswer4.setChecked(false);

    }


    private boolean checkAnswer(){
        String correct = TempQuestion.getAnswer().trim();
        Log.println(Log.DEBUG,"answer", "Correct answer =[" + correct + "]");
        // get selected radio button from radioGroup
        int selectedId = rgAnswers.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        RadioButton radioButton = (RadioButton) findViewById(selectedId);
        if (radioButton.equals(rightAnswer)){

        }
        String userAnswer = radioButton.getText().toString().trim();
        Log.println(Log.DEBUG,"answer", "User answer =[" + userAnswer + "]");
        return (correct.compareTo(userAnswer) == 0);

    }
    private Questions getQuestionsFromCSV(){
        int ID = 0;
        int A0 = 1;
        int A1 = 2;
        int A2 = 3;
        int A3 = 4;
        int SUBJECT = 5;

        ArrayList<Question> questions = new ArrayList<>();
        Questions questionsFromFile = new Questions(questions);

        AssetManager assetManager = getAssets();
        InputStream is = null;

        try {
            String QUIZ_FILE = "Questions.csv";
            is = assetManager.open(QUIZ_FILE);
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            String line = "";
            StringTokenizer st = null;
            int numLine = 0;
            while ((line = reader.readLine()) != null) {
                if(numLine >0 ){
                    st = new StringTokenizer(line, ",");

                    String id = st.nextToken();
                    String question= st.nextToken();
                    String answer= st.nextToken();
                    String a0 = st.nextToken();
                    String a1 = st.nextToken();
                    String a2 = st.nextToken();
                    String a3 = st.nextToken();
                    String subject = st.nextToken();

                    Question quizObj = new Question( id, question, answer, a0,a1, a2, a3, subject);

                    questionsFromFile.addQuestion(quizObj);
                }
                numLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return questionsFromFile;
    }
    public void onClick(View v)
    {
        if (v == btnNext)
        {
            if(checkAnswer()){
                score ++;
                Log.println(Log.DEBUG,"score", "Score =[" + score + "]");
            }
            if (quizNum == Quiz_length){
                btnEndQuiz.setEnabled(true);
            }
            else {
                quizNum++;
                showQuestionOnDisplay();
            }
        }
        else if(v == btnEndQuiz)
        {
            Intent myIntent = new Intent(Questions_multiple_choice.this, Result.class);
            myIntent.putExtra("score", score);
            Questions_multiple_choice.this.startActivity(myIntent);
            if(null != timer){
                // Cancel
                timer.cancel();
                timer = null;
            }

        }
        return;
    }
}


