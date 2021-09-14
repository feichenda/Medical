package com.lenovo.feizai.medical.viewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * @author feizai
 * @date 2021/6/13 0013 PM 2:51:52
 */
public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<String> title;
    private final SharedPreferences.Editor editor;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        editor = getApplication().getSharedPreferences("userdata", Context.MODE_PRIVATE).edit();
    }

    public MutableLiveData<String> getTitle() {
        if (title == null) {
            title = new MutableLiveData<>();
        }
        return title;
    }

    public void setTitle(String title) {
        getTitle().setValue(title);
    }

}
