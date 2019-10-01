package com.example.capstonmaster.board.free_board;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FreeViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public FreeViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("자유게시판");
    }
    public LiveData<String> getText() {
        return mText;
    }
}
