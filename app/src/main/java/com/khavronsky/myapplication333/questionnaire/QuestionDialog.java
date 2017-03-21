package com.khavronsky.myapplication333.questionnaire;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khavronsky.myapplication333.R;


public class QuestionDialog extends DialogFragment implements View.OnClickListener {


    private QuestionsModel mQuestion;
    private IQstnListener mDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Dialog dialog = new Dialog(getContext(),R.style.FullScreenActivity);
//        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mQuestion = (QuestionsModel) getArguments().getSerializable("Question");
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qstn_dialog, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        TextView btn_ok = (TextView) view.findViewById(R.id.qstn_btn_ok);
        TextView btn_cancel = (TextView) view.findViewById(R.id.qstn_btn_cancel);
        ImageView imageView = (ImageView) view.findViewById(R.id.qstn_img);
        TextView title = (TextView) view.findViewById(R.id.qstn_title);
        TextView questionText = (TextView) view.findViewById(R.id.qstn_text);
        final ImageView upperDivider = (ImageView) view.findViewById(R.id.qstn_upper_div);
        final ImageView lowerDivider = (ImageView) view.findViewById(R.id.qstn_lower_div);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.qstn_recycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        final QuestionAdapter mAdapter = new QuestionAdapter(mQuestion);

        imageView.setBackgroundResource(mQuestion.getImgResource());
        title.setText(mQuestion.getTitle());
        questionText.setText(mQuestion.getQuestion());
        btn_ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        view.post(new Runnable() {
            @Override
            public void run() {
//                Log.d("KHS", "FirstCompletelyVisibleItemPosition  " + layoutManager.findFirstCompletelyVisibleItemPosition());
                Log.d("KHS", "LastCompletelyVisibleItemPosition  " + layoutManager.findLastCompletelyVisibleItemPosition());
                Log.d("KHS", "mQuestion.getAnswers().size()  " + mQuestion.getAnswers().size());
                if (layoutManager.findLastCompletelyVisibleItemPosition() == mQuestion.getAnswers().size() - 1) {
                    lowerDivider.setVisibility(View.INVISIBLE);
                    upperDivider.setVisibility(View.INVISIBLE);
                }
            }
        });


//        layoutManager.findLastCompletelyVisibleItemPosition()


        mAdapter.setIQDListener(new QuestionAdapter.IAnswersListener() {
            @Override
            public void selectItem() {
                Toast.makeText(getContext(), "Checked", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
            }
        });
    }
//    @Override
//    public void onStart() {
    // getDialog().getWindow().setWindowAnimations(R.style.MatchActivityDialog);
//        super.onStart();
//        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        getDialog().getWindow().setBackgroundDrawable(null);
//    }

    public void setListener(IQstnListener listener) {
        this.mDialogListener = listener;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.qstn_btn_ok:
                if (answerSelected()) {
                    mDialogListener.answersSelected(mQuestion);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Ни один вариант не выбран", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.qstn_btn_cancel:
                mDialogListener.questionAborted(mQuestion);
                dismiss();
                break;
        }
    }

    public interface IQstnListener {
        void answersSelected(QuestionsModel question);

        void questionAborted(QuestionsModel answerList);
    }

    // лучше подет написать isAnswerSelected - т.к. это говорит что метод что-то проверяет 
    boolean answerSelected() {
        for (QuestionsModel.Answer answer :
                mQuestion.getAnswers()) {
            if (answer.isSelected()) return true;
        }
        return false;
    }
}
