package com.khavronsky.myapplication333;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.khavronsky.myapplication333.questionnaire.AnnouncementDialog;
import com.khavronsky.myapplication333.questionnaire.QuestionDialog;
import com.khavronsky.myapplication333.questionnaire.QuestionsModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements QuestionDialog.IQstnListener {

    Button button1;
    Button button2;
    Button button3;
    QuestionDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.but1);
        button2 = (Button) findViewById(R.id.but_announcement);
        button3 = (Button) findViewById(R.id.but2);
        final Bundle bundle = new Bundle();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new QuestionDialog();
                dialog.setArguments(bundle);
                dialog.setListener(MainActivity.this);
                bundle.putSerializable("Question", createQuestion1());
                dialog.show(getSupportFragmentManager(), null);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnnouncementDialog dialogFragment = new AnnouncementDialog();
//                bundle.putInt("resId", ... );
//                bundle.putString("title", ... );
//                bundle.putString("text", ... );
                dialogFragment.setArguments(bundle);
                dialogFragment.show(getSupportFragmentManager(), null);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new QuestionDialog();
                dialog.setArguments(bundle);
                dialog.setListener(MainActivity.this);
                bundle.putSerializable("Question", createQuestion2());
                dialog.show(getSupportFragmentManager(), null);

            }
        });
    }

    QuestionsModel createQuestion1() {
        QuestionsModel question = new QuestionsModel();
        question.setImgResource(R.drawable.ic_bounds);
        question.setTitle("Внимание! Вопрос:");
        question.setQuestion("Какой специалист занимается изучением неопознанных летающих объектов?");
        List<QuestionsModel.Answer> answers = new ArrayList<>();
        answers.add(new QuestionsModel.Answer().setAnswer("Кинолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Уфолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Сексопатолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Психиатр").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Сайентолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Антрополог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Дерматовенеролог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Фитопалеонтолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Гидрометеоролог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Агрометеоролог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Психофизиолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Санскритолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Политтехнолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Феноменолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Геоморфолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Мартиролог").setSelected(false));
        question.setAnswers(answers);
        question.setMultiChoice(false);
        return question;
    }

    QuestionsModel createQuestion2() {
        QuestionsModel question = new QuestionsModel();
        question.setImgResource(R.drawable.ic_bounds);
        question.setTitle("Внимание! Вопрос:");
        question.setQuestion("Какой специалист занимается изучением неопознанных летающих объектов?");
        List<QuestionsModel.Answer> answers = new ArrayList<>();
        answers.add(new QuestionsModel.Answer().setAnswer("Кинолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Уфолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Сексопатолог").setSelected(false));
        answers.add(new QuestionsModel.Answer().setAnswer("Психиатр").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Сайентолог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Антрополог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Дерматовенеролог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Фитопалеонтолог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Гидрометеоролог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Агрометеоролог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Психофизиолог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Санскритолог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Политтехнолог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Феноменолог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Геоморфолог").setSelected(false));
//        answers.add(new QuestionsModel.Answer().setAnswer("Мартиролог").setSelected(false));
        question.setAnswers(answers);
        question.setMultiChoice(true);
        return question;
    }

    @Override
    public void answersSelected(QuestionsModel question) {
        Toast.makeText(MainActivity.this, "Save", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void questionAborted(QuestionsModel answerList) {
        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
    }
}
