package com.example.morfaap.ui.registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class RegistroViewModel extends AndroidViewModel {
    private Context context;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
}
