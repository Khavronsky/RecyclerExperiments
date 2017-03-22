package com.khavronsky.myapplication333.questionnaire;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khavronsky.myapplication333.R;

import java.util.List;

class QuestionAdapter extends RecyclerView.Adapter<QuestionHolder> implements QuestionHolder.ICheckListener {
    private QuestionsModel mQuestion;
    private IAnswersListener mListener;
    @IntDef({
            RADIO_TYPE,
            CHECK_TYPE,
    })
    @interface CheckBoxType {}
    static final int RADIO_TYPE = R.drawable.radiobutton_selection;
    static final int CHECK_TYPE = R.drawable.checkbox_selection;

    QuestionAdapter(QuestionsModel question) {
        this.mQuestion = question;
    }

    @Override
    public QuestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.qstn_holder_item, parent, false);
        return new QuestionHolder(v);
    }

    @Override
    public void onBindViewHolder(final QuestionHolder holder, int position) {
        holder.setAnswer(mQuestion.getAnswers().get(position).getAnswer(),
                mQuestion.getAnswers().get(position).isSelected(),
                setCheckBoxDrawable(mQuestion.isMultiChoice()));
        holder.setListener(this, position);
    }

    @Override
    public int getItemCount() {
        return mQuestion != null && mQuestion.getAnswers() != null ? mQuestion.getAnswers().size() : 0;
    }

    void setIQDListener(IAnswersListener listener) {
        this.mListener = listener;
    }

    @Override
    public void check(boolean isChecked, int pos) {
        List<QuestionsModel.Answer> answers = mQuestion.getAnswers();

        if (!mQuestion.isMultiChoice()) {
            for (int i = 0; i < mQuestion.getAnswers().size(); i++) {
                answers.get(i).setSelected(false);
            }
            answers.get(pos).setSelected(true);
        } else {
            answers.get(pos).setSelected(!isChecked);
        }
        mListener.selectItem();
    }
    @CheckBoxType
    private int setCheckBoxDrawable(boolean multiChoice) {
        return multiChoice ? CHECK_TYPE : RADIO_TYPE;
    }

    interface IAnswersListener {
        void selectItem();
    }
}
