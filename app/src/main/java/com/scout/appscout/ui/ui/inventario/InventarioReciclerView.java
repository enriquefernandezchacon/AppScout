package com.scout.appscout.ui.ui.inventario;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scout.appscout.common.Articulo;

import java.util.ArrayList;

public class InventarioReciclerView extends RecyclerView.Adapter<InventarioReciclerView.ViewHolder> {
    private ArrayList<Articulo> articulos;
    private Context context;

    public InventarioReciclerView(Context context, ArrayList<Articulo> articulos) {
        this.context = context;
        this.articulos = articulos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
