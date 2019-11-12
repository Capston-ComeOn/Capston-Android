package com.example.capstonmaster.board.advice_board;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdviceViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public AdviceViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("공지사항");
    }
    public LiveData<String> getText() {
        return mText;
    }
}
