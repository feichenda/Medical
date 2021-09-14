package com.lenovo.feizai.medical.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lenovo.feizai.medical.R;
import com.lenovo.feizai.medical.databinding.FragmentMainBinding;
import com.lenovo.feizai.medical.viewModel.LoginViewModel;

public class MainFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentMainBinding binding;
    private NavController controller;
    private LoginViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
        binding.bottomNavigation.setSelectedItemId(R.id.navigation_my_medical_records);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        viewModel.getTitle().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
//                binding.title.setText(s);
            }
        });

//        Bundle bundle = getArguments();
//        String s = bundle.getString("key", "");
//        binding.title.setText(s);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_my_medical_records:
                RecordsListFragment listFragment = new RecordsListFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,listFragment).commit();
                return true;
            case R.id.navigation_my_setting:
                SettingFragment settingFragment = new SettingFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,settingFragment).commitNow();
                return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK){
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }
}