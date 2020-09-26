package com.ulangtahun.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ulangtahun.R;
import com.ulangtahun.model.LoginResponse;
import com.ulangtahun.model.ReadPersonRecord;
import com.ulangtahun.model.ReadPersonResponse;
import com.ulangtahun.network.ApiService;
import com.ulangtahun.network.RetrofitClient;
import com.ulangtahun.ui.UcapanActivity;
import com.ulangtahun.util.SharedPrefManager;
import com.ulangtahun.util.Util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder>{
    Context context;
    private List<ReadPersonRecord> dataList;
    ApiService apiInterface = RetrofitClient.getClient().create(ApiService.class);

    public DataAdapter(Context context, List<ReadPersonRecord> dataList) {
        this.context = context;
        this.dataList = dataList;

    }

    @NotNull
    @Override
    public DataAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_data, parent, false);
        return new DataAdapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(DataAdapterViewHolder holder, int position) {
        final ReadPersonRecord modelRecord = dataList.get(position);
        holder.nama.setText(dataList.get(position).getNama());
        holder.jabatan.setText(dataList.get(position).getJabatan());
        holder.pangkat.setText(dataList.get(position).getPangkat());


        String tgl = dataList.get(position).getTglLahir();
        int tgl_lahir = Integer.parseInt(tgl.substring(8,10));
        int bln_lahir = Integer.parseInt(tgl.substring(5,7));
        int thn_lahir = Integer.parseInt(tgl.substring(0,4));

        Util util = new Util();
        String tgl_hari_ini = util.getTanggal();
        int tgl_ini = Integer.parseInt(tgl_hari_ini.substring(8,10));
        int bln_ini = Integer.parseInt(tgl_hari_ini.substring(5,7));
        int thn_ini = Integer.parseInt(tgl_hari_ini.substring(0,4));

        String umur = String.valueOf(thn_ini - thn_lahir);
        holder.umur.setText(dataList.get(position).getNrp());
        String status = dataList.get(position).getStatusr();

        if(status.equals("0")){
            holder.status.setVisibility(View.VISIBLE);
        }

        holder.item_list.setOnClickListener(view -> {
            Intent intent = new Intent(context, UcapanActivity.class);
            intent.putExtra(UcapanActivity.EXTRA_INTENT ,modelRecord);
            context.startActivity(intent);
        });

        holder.cv_ucapan.setOnClickListener(view -> {
            Intent intent = new Intent(context, UcapanActivity.class);
            intent.putExtra(UcapanActivity.EXTRA_INTENT ,modelRecord);
            context.startActivity(intent);
//            Call<ReadPersonResponse> call = apiInterface.updateStatus(dataList.get(position).getId(), "1", SharedPrefManager.getUser().getToken());
//            call.enqueue(new Callback<ReadPersonResponse>() {
//                @Override
//                public void onResponse(@NotNull Call<ReadPersonResponse> call, @NotNull Response<ReadPersonResponse> response) {
//
//                }
//
//                @Override
//                public void onFailure(@NotNull Call<ReadPersonResponse> call, @NotNull Throwable t) {
//
//                }
//            });
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public static class DataAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView  nama, umur, jabatan, pangkat;
        CardView cv_ucapan, item_list;
        ImageView status;


        public DataAdapterViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_nama);
            umur = itemView.findViewById(R.id.tv_umur);
            jabatan = itemView.findViewById(R.id.tv_jabatan);
            pangkat = itemView.findViewById(R.id.tv_pangkat);
            cv_ucapan = itemView.findViewById(R.id.cv_giftcard);
            item_list = itemView.findViewById(R.id.item_list_data);
            status = itemView.findViewById(R.id.status);
        }
    }
}
