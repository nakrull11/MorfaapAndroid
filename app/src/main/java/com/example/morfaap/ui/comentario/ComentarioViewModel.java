package com.example.morfaap.ui.comentario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ComentarioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ComentarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Comentarios a locales");
    }

    public LiveData<String> getText() {
        return mText;
    }
}