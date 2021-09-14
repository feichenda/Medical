package com.lenovo.feizai.medical.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lenovo.feizai.medical.R;
import com.lenovo.feizai.medical.base.BaseModel;
import com.lenovo.feizai.medical.base.BaseObserver;
import com.lenovo.feizai.medical.databinding.FragmentLoginBinding;
import com.lenovo.feizai.medical.entity.LoginEntity;
import com.lenovo.feizai.medical.entity.PasswordLoginQuery;
import com.lenovo.feizai.medical.network.ExceptionHandle;
import com.lenovo.feizai.medical.network.RequestApi;
import com.lenovo.feizai.medical.network.RetrofitClient;
import com.lenovo.feizai.medical.util.MD5Util;
import com.lenovo.feizai.medical.util.ToolUtil;
import com.lenovo.feizai.medical.viewModel.LoginViewModel;

import java.util.Date;
import java.util.UUID;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private NavController controller;
    private LoginViewModel viewModel;
    private RetrofitClient client;
    private String tage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        client = RetrofitClient.getInstance(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        String key = ToolUtil.getUserName(getActivity());
        controller = Navigation.findNavController(view);
        if (!TextUtils.isEmpty(key)) {
            viewModel.setTitle(key);
            binding.user.setText(key);
        }
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                controller = Navigation.findNavController(v);
//                String s = binding.user.getText().toString();
//                viewModel.setTitle(s);
//                Bundle bundle = new Bundle();
//                bundle.putString("key",s);
//                controller.navigate(R.id.action_loginFragment_to_mainFragment,bundle);
//                controller.navigate(R.id.action_loginFragment_to_mainFragment);
//                passwordLogin();
                controller.navigate(R.id.action_loginFragment_to_mainFragment);
            }
        });
        binding.codeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePicture();
            }
        });
        binding.see.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP://松开事件发生后执行代码的区域
                        binding.pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        break;
                    case MotionEvent.ACTION_DOWN://按住事件发生后执行代码的区域
                        binding.pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        break;
                    default:
                        break;
                }
                //切换后将EditText光标置于末尾
                CharSequence charSequence = binding.pass.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
                return true;
            }
        });
        changePicture();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("life", "pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("life", "stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("life", "destory");
    }

    private void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }

    private void changePicture() {
        tage = ToolUtil.getUUID();
        Glide.with(getContext())
                .load(RequestApi.BASE_IMAGE_URL + "api/hospital/passwordLogin/codes?count=0&tage=" + tage)
                .override(200,100)
                .error(R.mipmap.image_error)
                .placeholder(R.mipmap.image_error)
                .into(binding.codeImg);
    }

    private void passwordLogin() {
        String user = binding.user.getText().toString();
        String pass = binding.pass.getText().toString();
        String code = binding.verificationCode.getText().toString();
        String md5 = MD5Util.string2MD5(pass);
        PasswordLoginQuery query = new PasswordLoginQuery();
        query.setIdentityId(user);
        query.setPassword(md5);
        query.setCode(code);
        query.setTage(tage);
        client.password(query, new BaseObserver<BaseModel<LoginEntity>>(getContext()) {
            @Override
            protected void showDialog() {

            }

            @Override
            protected void hideDialog() {

            }

            @Override
            protected void successful(BaseModel<LoginEntity> loginEntityBaseModel) {
                showToast("登录成功");
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE).edit();
                editor.putString("user", binding.user.getText().toString());
                editor.putString("phone", loginEntityBaseModel.getData().getPhoneNumber());
                editor.apply();
                editor.commit();
                controller.navigate(R.id.action_loginFragment_to_mainFragment);
            }

            @Override
            protected void defeated(BaseModel<LoginEntity> loginEntityBaseModel) {
                showToast(loginEntityBaseModel.getMsg());
                changePicture();
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                showToast(e.getMessage());
                changePicture();
            }
        });
    }
}