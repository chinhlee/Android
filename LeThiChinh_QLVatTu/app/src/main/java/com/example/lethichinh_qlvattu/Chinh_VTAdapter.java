package com.example.lethichinh_qlvattu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Chinh_VTAdapter extends ArrayAdapter<Chinh_VatTu> {
    private Context context;
    private int resource;
    private List<Chinh_VatTu> listvt;

    public Chinh_VTAdapter(@NonNull Context context, int resource, @NonNull List<Chinh_VatTu> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listvt = objects;
    }

    class ViewHolder {
        private TextView tvmavt, tvten, tvnsx, tvnamsx, tvsoluong, tvdongia;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //xét TH convertView rỗng thì
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_listvt, parent, false);
            viewHolder = new ViewHolder();
            //ánh xạ
            viewHolder.tvmavt = convertView.findViewById(R.id.tv_mavt);
            viewHolder.tvten = convertView.findViewById(R.id.tv_ten);
            viewHolder.tvnsx = convertView.findViewById(R.id.tv_nsx);
            viewHolder.tvnamsx = convertView.findViewById(R.id.tv_namsx);
            viewHolder.tvsoluong = convertView.findViewById(R.id.tv_soluong);
            viewHolder.tvdongia = convertView.findViewById(R.id.tv_dongia);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Thiết lập giá trị
        Chinh_VatTu vt = listvt.get(position);
        viewHolder.tvmavt.setText(vt.getMavt());
        viewHolder.tvten.setText(vt.getTen());
        viewHolder.tvnsx.setText(vt.getNsx());
        viewHolder.tvnamsx.setText(vt.getNamsx());
        viewHolder.tvsoluong.setText(vt.getSoluong());
        viewHolder.tvdongia.setText(vt.getDongia());

        return convertView;
    }

}
