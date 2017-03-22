package com.khavronsky.myapplication333.questionnaire;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.khavronsky.myapplication333.R;


public class AnnouncementDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.qstn_dialog_announcement, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.qstn_img);
        TextView title = (TextView) view.findViewById(R.id.qstn_title);
        TextView questionText = (TextView) view.findViewById(R.id.qstn_text);
        TextView btn_ok = (TextView) view.findViewById(R.id.qstn_btn_ok);
        imageView.setBackgroundResource(getArguments().getInt("resId", R.drawable.ic_bounds));
        title.setText(getArguments().getString("title", "Вопрос от ONETRAK"));
        questionText.setText(getArguments().getString("text", "Теперь в приложении есть рубрика «Вопрос от Onetrak». " +
                "Ваше участие в опросе поможет сделать наш продукт лучше."));
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
