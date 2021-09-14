package com.lenovo.feizai.medical.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lenovo.feizai.medical.R;
import com.lenovo.feizai.medical.entity.PatientEntity;

import java.util.List;

/**
 * @Author feizai
 * @Date 2021/9/13 0013  下午 9:48:43
 * @Explain
 */
public class RecordsListAdapter extends BaseQuickAdapter<PatientEntity, BaseViewHolder> implements LoadMoreModule {
    public RecordsListAdapter(@Nullable List<PatientEntity> data) {
        super(R.layout.info_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, PatientEntity patientEntity) {
        ImageView avatar = baseViewHolder.getView(R.id.avatar);
        Glide.with(getContext()).load(R.mipmap.we).into(avatar);
        baseViewHolder.setText(R.id.user_Id, patientEntity.getIdentityId());
        baseViewHolder.setText(R.id.user_Name, patientEntity.getUsername());
//        baseViewHolder.setText(R.id.phoneNumber, patientEntity.getPhoneNumber());
//        baseViewHolder.setText(R.id.age, patientEntity.getAge()+"岁");
        baseViewHolder.setText(R.id.sex, patientEntity.getSex()==0?"女":"男");
//        baseViewHolder.setText(R.id.doctor, patientEntity.getDoctorName());
    }
}
