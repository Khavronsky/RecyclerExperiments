package com.khavronsky.myapplication333.questionnaire;

import android.support.annotation.IntDef;
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

    @IntDef({
            RADIO_TYPE,
            CHECK_TYPE,
    })
    @interface CheckBoxType {}
    static final int RADIO_TYPE = R.drawable.radiobutton_selection;
    static final int CHECK_TYPE = R.drawable.checkbox_selection;

    QuestionHolder(View view) {
        super(view);
        tv_item = (TextView) view.findViewById(R.id.qstn_answer);
        checkBox = (CheckBox) view.findViewById(R.id.qstn_checkbox);
        answerItem = view.findViewById(R.id.qstn_answer_item);
    }


    // todo вместо multiChoosingType передавать int = BackgroundResource
    void setAnswer(String text, boolean selected, boolean multiChoosingType) {

        tv_item.setText(text);
        checkBox.setChecked(selected);
        checkBox.setBackgroundResource(setCheckBoxDrawable(multiChoosingType));
        answerItem.setOnClickListener(this);
    }

    //todo getCheckBoxDrawable. Перенести этот метод в адаптер 
    @CheckBoxType
    private int setCheckBoxDrawable(boolean multiChoice) {
        return multiChoice ? CHECK_TYPE : RADIO_TYPE;
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
