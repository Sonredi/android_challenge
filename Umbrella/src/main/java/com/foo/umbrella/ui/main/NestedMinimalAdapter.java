package com.foo.umbrella.ui.main;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foo.umbrella.R;
import com.foo.umbrella.data.model.ForecastCondition;

import java.util.List;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */

public class NestedMinimalAdapter extends RecyclerView.Adapter<NestedMinimalAdapter.ViewHolder> {

    private List<ForecastCondition> results;

    public NestedMinimalAdapter(List<ForecastCondition> results) {
        this.results = results;
    }

    public interface OnItemCLickLIstener {
        void onItemClick();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.unit_row_stagged, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results != null ? results.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt_hour;
        private final TextView txt_temp;
        private final ImageView img_weather;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_hour = (TextView) itemView.findViewById(R.id.r_nested_hour);
            txt_temp = (TextView) itemView.findViewById(R.id.r_nested_temp);
            img_weather = (ImageView) itemView.findViewById(R.id.r_nested_img);
        }

        public void bind(ForecastCondition s) {
            txt_hour.setText(s.getDisplayTime());
            txt_temp.setText(s.getTempFahrenheit()+"°");


            switch (s.getIcon()) {
                case "cloudy":
                    img_weather.setBackgroundResource(R.drawable.weather_cloudy);
                    break;
                case "partlycloudy":
                    img_weather.setBackgroundResource(R.drawable.weather_partlycloudy);
                    break;
                case "sunny":
                    img_weather.setBackgroundResource(R.drawable.weather_snowy);
                    break;
                case "snowy":
                    img_weather.setBackgroundResource(R.drawable.weather_snowy);
                    break;
                case "rainy":
                    img_weather.setBackgroundResource(R.drawable.weather_rainy);
                    break;
                    default:
                        img_weather.setBackgroundResource(R.drawable.weather_windy_variant);
                        break;
            }
        }
    }
}
