package com.lenovo.feizai.medical.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.lenovo.feizai.AndroidTool.util.TimeUtil;
import com.lenovo.feizai.medical.R;
import com.lenovo.feizai.medical.adapter.RecordsListAdapter;
import com.lenovo.feizai.medical.base.BaseModel;
import com.lenovo.feizai.medical.base.BaseObserver;
import com.lenovo.feizai.medical.base.BaseRefreshRecyclerView;
import com.lenovo.feizai.medical.databinding.FragmentRecordsListBinding;
import com.lenovo.feizai.medical.entity.ConsumerQuery;
import com.lenovo.feizai.medical.entity.PatientEntity;
import com.lenovo.feizai.medical.network.ExceptionHandle;
import com.lenovo.feizai.medical.network.RetrofitClient;
import com.lenovo.feizai.medical.util.ToolUtil;
import com.takisoft.datetimepicker.DatePickerDialog;
import com.takisoft.datetimepicker.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RecordsListFragment extends Fragment {

    private FragmentRecordsListBinding binding;
    private RetrofitClient client;
    private Integer count;
    private NavController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecordsListBinding.inflate(getLayoutInflater());
        client = RetrofitClient.getInstance(getContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
        count = 0;
        List<PatientEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PatientEntity entity = new PatientEntity(1L,"362521199812060018","liu",22,0,"15878321784",1689565165L,"2121/09/10","MS",0,null,null,null);
            list.add(entity);
        }
        BaseRefreshRecyclerView recordsList = new BaseRefreshRecyclerView(getView(),R.id.records_list,R.id.records_list_fresh) {
            @Override
            public BaseQuickAdapter initAdapter() {
                RecordsListAdapter adapter = new RecordsListAdapter(null);
                return adapter;
            }
        };
        binding.startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext());
                dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String start = year + "-" + String.format("%02d",(month+1)) + "-" + String.format("%02d",dayOfMonth);
                        if (compareTo(start, binding.enddate.getText().toString()) > 0) {
                            showToast("开始时间不能大于结束时间");
                        } else {
                            binding.startdate.setText(start);
                        }
                    }
                });
                dialog.show();
            }
        });
        binding.enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getContext());
                dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String end = year + "-" + String.format("%02d",(month+1)) + "-" + String.format("%02d",dayOfMonth);
                        if (compareTo(binding.startdate.getText().toString(), end) > 0) {
                            showToast("结束时间不能小于于开始时间");
                        } else {
                            binding.enddate.setText(end);
                        }
                    }
                });
                dialog.show();
            }
        });
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordsList.cleanData();
                ConsumerQuery query = new ConsumerQuery();
                String start = binding.startdate.getText().toString();
                String end = binding.enddate.getText().toString();
                if (TextUtils.isEmpty(start) || TextUtils.isEmpty(end)) {
                    showToast("时间不能为空");
                    return;
                }
                query.setIdentity(ToolUtil.getUserName(getActivity()));
                query.setStartTime(start);
                query.setEndTime(end);
                client.queryPatient(query, new BaseObserver<BaseModel<PatientEntity>>(getContext()) {
                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void successful(BaseModel<PatientEntity> patientEntityBaseModel) {
                        showToast(patientEntityBaseModel.getMsg());
                        recordsList.addDatas(patientEntityBaseModel.getDatas());
                        recordsList.refreshEnd();
                    }

                    @Override
                    protected void defeated(BaseModel<PatientEntity> patientEntityBaseModel) {
                        showToast(patientEntityBaseModel.getMsg());
                        recordsList.addDatas(list);
                        recordsList.refreshEnd();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        showToast(e.getMessage());
                        recordsList.addDatas(list);
                        recordsList.refreshEnd();
                    }
                });
            }
        });

        recordsList.addRefreshLisenter(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count = 0;
                recordsList.cleanData();
                recordsList.loadComplete();
                ConsumerQuery query = new ConsumerQuery();
                String start = binding.startdate.getText().toString();
                String end = binding.enddate.getText().toString();
                if (TextUtils.isEmpty(start) || TextUtils.isEmpty(end)) {
                    showToast("时间不能为空");
                    recordsList.refreshEnd();
                    return;
                }
                query.setIdentity(ToolUtil.getUserName(getActivity()));
                query.setStartTime(start);
                query.setEndTime(end);
                client.queryPatient(query, new BaseObserver<BaseModel<PatientEntity>>(getContext()) {
                    @Override
                    protected void showDialog() {

                    }

                    @Override
                    protected void hideDialog() {

                    }

                    @Override
                    protected void successful(BaseModel<PatientEntity> patientEntityBaseModel) {
                        showToast(patientEntityBaseModel.getMsg());
                        recordsList.addDatas(patientEntityBaseModel.getDatas());
                        recordsList.refreshEnd();
                    }

                    @Override
                    protected void defeated(BaseModel<PatientEntity> patientEntityBaseModel) {
                        showToast(patientEntityBaseModel.getMsg());
                        recordsList.addDatas(list);
                        recordsList.refreshEnd();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        showToast(e.getMessage());
                        recordsList.addDatas(list);
                        recordsList.refreshEnd();
                    }
                });
            }
        });

        recordsList.loadAutoMore(true);
        recordsList.loadMore(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (count<1) {
                    count++;
                    recordsList.addDatas(list);
                    recordsList.loadComplete();
                }else {
                    recordsList.loadEnd();
                }
            }
        });

        recordsList.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                controller.navigate(R.id.action_mainFragment_to_infoFragment);
            }
        });

    }

    private int compareTo(String start,String end) {
        if (TextUtils.isEmpty(start) || TextUtils.isEmpty(end)) {
            return 0;
        }
        Date startDate = TimeUtil.string2Date(start,TimeUtil.DATEFORMAT);
        Date endDate = TimeUtil.string2Date(end,TimeUtil.DATEFORMAT);
        return startDate.compareTo(endDate);
    }

    private void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }
}