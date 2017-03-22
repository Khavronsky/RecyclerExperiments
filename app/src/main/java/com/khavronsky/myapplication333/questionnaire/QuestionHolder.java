package com.khavronsky.myapplication333.questionnaire;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.khavronsky.myapplication333.R;

class QuestionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tv_item;
    private CheckBox checkBox;
    private ICheckListener listener;
    private View answerItem;
    private int pos;

    QuestionHolder(View view) {
        super(view);
        tv_item = (TextView) view.findViewById(R.id.qstn_answer);
        checkBox = (CheckBox) view.findViewById(R.id.qstn_checkbox);
        answerItem = view.findViewById(R.id.qstn_answer_item);
    }

    void setAnswer(String text, boolean selected, int backgroundSource) {

        tv_item.setText(text);
        checkBox.setChecked(selected);
        checkBox.setBackgroundResource(backgroundSource);
        answerItem.setOnClickListener(this);
    }

    void setListener(ICheckListener listener, int pos) {
        this.listener = listener;
        this.pos = pos;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.check(checkBox.isChecked(), pos);
        }
    }

    interface ICheckListener {
        void check(boolean isChecked, int pos);
    }
}
