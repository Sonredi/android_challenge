package com.foo.umbrella.ui.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foo.umbrella.R;
import com.foo.umbrella.data.model.CurrentObservation;
import com.foo.umbrella.data.model.DisplayLocation;
import com.foo.umbrella.data.model.ForecastCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */

public class MinimalAdapter extends RecyclerView.Adapter<MinimalAdapter.ViewHolder> {

    private static final String TAG = "MinimalAdapterTAG_";
    private Map<String, List<ForecastCondition>> results;
    private List<String> values;

    public MinimalAdapter(Map<String, List<ForecastCondition>> results) {
        this.results = results;
        Set<String> listKey = results.keySet();
        TreeSet order = new TreeSet(listKey);
        values = new ArrayList<>(order);
    }

    public interface OnItemCLickLIstener {
        void onItemClick();
    }

    public void updateKeySet() {
        Set<String> listKey = results.keySet();
        TreeSet order = new TreeSet(listKey);
        values = new ArrayList<>(order);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.unit_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String aux = values.get(position);
        List<ForecastCondition> auxList = results.get(aux);
        holder.bind(aux, auxList);
    }

    @Override
    public int getItemCount() {
        return results != null ? results.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt_date;
        private RecyclerView recyclerView;
        private NestedMinimalAdapter nestedMinimalAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        private List<ForecastCondition> resultList;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_date = (TextView) itemView.findViewById(R.id.r_txt_date);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.r_nested_recycler);


            resultList = new ArrayList<>();
            nestedMinimalAdapter = new NestedMinimalAdapter(resultList);
            mLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(nestedMinimalAdapter);

        }

        public void bind(String key, List<ForecastCondition> auxList) {
            txt_date.setText(key);
            resultList.clear();
            resultList.addAll(auxList);
            nestedMinimalAdapter.notifyDataSetChanged();



        }
    }
}
