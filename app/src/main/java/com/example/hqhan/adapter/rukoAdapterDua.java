package com.example.hqhan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hqhan.databinding.ItemPenggunaBinding;
import com.example.hqhan.model.entity.ruko;

import java.util.List;

public class rukoAdapterDua extends RecyclerView.Adapter<rukoAdapterDua.ViewHolder> {

    private static final String TAG = rukoAdapterDua.class.getSimpleName();

    private Context context;
    private List<ruko> list;
    private rukoAdapterDua.rukoAdapterDuaCallback mAdapterCallback;
    private ItemPenggunaBinding binding;

    public rukoAdapterDua(Context context, List<ruko> list, rukoAdapterDua.rukoAdapterDuaCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = (rukoAdapterDuaCallback) adapterCallback;
    }

    @Override
    public rukoAdapterDua.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemPenggunaBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new rukoAdapterDua.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(rukoAdapterDua.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        ruko item = list.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<ruko> rukos){
        this.list = rukos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull ItemPenggunaBinding itemView) {
            super(itemView.getRoot());
            binding.tvAlamatruko.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ruko ruko = list.get(getAdapterPosition());
                    mAdapterCallback.lokasiruko(ruko);
                }
            });

            binding.ivKontak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ruko ruko = list.get(getAdapterPosition());
                    mAdapterCallback.kontakRuko(ruko);
                }
            });
        }
        void bindData(ruko item) {
            Integer uid = item.idruko;
            String gmap_url = item.gmap;

            String note1 = item.namaruko;
            binding.tvNamaruko.setText(note1);

            String note2 = item.alamatruko;
            binding.tvAlamatruko.setText(note2);

            String note3 = item.deskripsiruko;
            binding.tvdeskripsi.setText(note3);

            String note4 = item.kontakruko;
            binding.tvKontakruko.setText(note4);

            binding.judulAlamat.setText("Alamat\t:\t\t");
            binding.JudulKontakruko.setText("Kontak\t\t:\t\t");
            binding.juduldeskripsi.setText("Deskripsi :");
        }

    }

    public interface rukoAdapterDuaCallback {
        void lokasiruko(ruko ruko);
        void kontakRuko(ruko ruko);
    }

}
