package com.app.bicicreta.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.bicicreta.R;
import com.app.bicicreta.app.model.Peca;

import java.util.ArrayList;
import java.util.List;

public class AdapterPeca extends RecyclerView.Adapter<AdapterPeca.PecaViewHolder> {
    private List<Peca> pecas = new ArrayList<>();
    public AdapterPeca(List<Peca> pecas) {
        this.pecas = pecas;
    }

    @NonNull
    @Override
    public PecaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.peca_item, parent, false);
        return new AdapterPeca.PecaViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull PecaViewHolder holder, int position) {
        Peca peca = pecas.get(position);
        holder.dataCompra.setText(peca.getDataCompra());
        holder.nomePeca.setText(peca.getNomePeca());
        holder.valor.setText(String.valueOf(peca.getValor()));
        holder.nomeBicicleta.setText("AAAA");
        holder.quilometros.setText(String.valueOf(peca.getQuilometros()));
    }

    @Override
    public int getItemCount() {
        return pecas.size();
    }

    public class PecaViewHolder extends RecyclerView.ViewHolder{
        TextView dataCompra;
        TextView valor;
        TextView quilometros;
        TextView nomeBicicleta;
        TextView nomePeca;
        public PecaViewHolder(@NonNull View itemView) {
            super(itemView);
            dataCompra = itemView.findViewById(R.id.dataCompraViewHolderTextView);
            valor = itemView.findViewById(R.id.valorPecaViewHolderTextView);
            quilometros = itemView.findViewById(R.id.quilometroPecaViewHolderTextView);
            nomeBicicleta = itemView.findViewById(R.id.nomeBicicletaPecaViewHolderTextView);
            nomePeca = itemView.findViewById(R.id.nomePecaViewHolderTextView);
        }
    }
}
