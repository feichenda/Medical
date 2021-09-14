package com.lenovo.feizai.medical.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lenovo.feizai.medical.databinding.FragmentInfoBinding;
import com.lenovo.feizai.medical.databinding.FragmentRecordsListBinding;

/**
 * @Author feizai
 * @Date 2021/9/14 0014  下午 10:24:25
 * @Explain
 */
public class RecordsListItemInfoFragment extends Fragment {

    private FragmentInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
