package com.example.todoappandroid2.ui.profile;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    public MutableLiveData<Uri> filePath = new MutableLiveData<>();

    public void setAvatar(Uri path) {
        filePath.postValue(path);
    }
}