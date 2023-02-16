package com.example.hqhan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hqhan.databinding.ItemRukoBinding;

import java.util.List;

public class rukoAdapter extends RecyclerView.Adapter<rukoAdapter.ViewHolder> {

    private static final String TAG = rukoAdapter.class.getSimpleName();

    private Context context;
    private List<ruko> list;
    private rukoAdapterCallback mAdapterCallback;
    private ItemRukoBinding binding;

    public rukoAdapter(Context context, List<ruko> list, rukoAdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = adapterCallback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ItemRukoBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //kasih false biar datane gak menggila
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

        ViewHolder(@NonNull ItemRukoBinding itemView) {
            super(itemView.getRoot());

            binding.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ruko ruko = list.get(getAdapterPosition());
                    mAdapterCallback.onDelete(ruko);
                }
            });
            binding.tvAlamatruko.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ruko ruko = list.get(getAdapterPosition());
                    mAdapterCallback.lokasiruko(ruko);
                }
            });
            binding.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ruko ruko = list.get(getAdapterPosition());
                    mAdapterCallback.onEdit(ruko);
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

    public interface rukoAdapterCallback {
        void onDelete(ruko ruko);
        void lokasiruko(ruko ruko);
        void onEdit(ruko ruko);
    }

}