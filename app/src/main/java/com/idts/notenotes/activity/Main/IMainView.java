package com.idts.notenotes.activity.Main;

import androidx.fragment.app.Fragment;

public interface IMainView {
    void finishActivity();
    void handleFragment(Fragment fragment);
    void setCustomView(int selectedTan, int non1, int non2);
    void setTextAndAnimation(String text, int image);
}
