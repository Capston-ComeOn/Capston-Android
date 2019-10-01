package com.example.capstonmaster.board.notice_board;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NoticeViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public NoticeViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("공지사항");
    }
    public LiveData<String> getText() {
        return mText;
    }
}
