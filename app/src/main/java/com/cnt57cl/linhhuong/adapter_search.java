package com.cnt57cl.linhhuong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adapter_search extends ArrayAdapter<phim> {

    private int LayoutID;
    private int TextViewID;

    private LayoutInflater Inflater;

    private ArrayList<phim> ObjectsList;

    public adapter_search(@NonNull Context context, int resource, int textViewResourceId, @NonNull ArrayList<phim> objects) {
        super(context, resource, textViewResourceId, objects);
        LayoutID = resource;
        TextViewID = textViewResourceId;

        ObjectsList = objects;

        Inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int Position, View ConvertView, ViewGroup Parent) {
        phim p = getItem(Position);

        if (ConvertView == null) {
            ConvertView = Inflater.inflate(LayoutID, null);

            ResultHolder Holder = new ResultHolder();

            Holder.tv_tenphim = ConvertView.findViewById(R.id.tv_tenphim);
            Holder.tv_thoiluong = ConvertView.findViewById(R.id.tv_thoiluong);
            Holder.img_search = ConvertView.findViewById(R.id.img_phim);

            ConvertView.setTag(Holder);
        }

        ResultHolder Holder = (ResultHolder) ConvertView.getTag();

        Holder.tv_tenphim.setText(p.getName());
        Holder.tv_thoiluong.setText(p.getThoiluong() + " Phút");
        Picasso.get().load(p.getBanner()).error(R.drawable.ic_launcher_background).into(Holder.img_search);
        return ConvertView;
    }

    @Override
    public Filter getFilter() {
        return CustomFilter;
    }

    private Filter CustomFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object ResultValue) {
            return ((phim) ResultValue).getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence Constraint) {
            FilterResults ResultsFilter = new FilterResults();

            ArrayList<phim> OriginalValues = new ArrayList<>(ObjectsList);

            if (Constraint == null || Constraint.length() == 0) {
                ResultsFilter.values = OriginalValues;
                ResultsFilter.count = OriginalValues.size();
            } else {
                String PrefixString = Constraint.toString();

                final ArrayList<phim> NewValues = new ArrayList<>();

                for (phim p : OriginalValues) {
                    String ValueText = p.getName();

                    if (ValueText.startsWith(PrefixString))
                        NewValues.add(p);
                }

                ResultsFilter.values = NewValues;
                ResultsFilter.count = NewValues.size();
            }

            return ResultsFilter;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            clear();
            if(results.count>0)
            {
                addAll(((ArrayList<phim>) results.values));
            }
            else
                notifyDataSetInvalidated();

        }

    };

        private static class ResultHolder {
        TextView tv_tenphim;
        TextView tv_thoiluong;
        ImageView img_search;
    }
}
