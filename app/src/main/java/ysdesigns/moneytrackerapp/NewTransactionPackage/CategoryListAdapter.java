package ysdesigns.moneytrackerapp.NewTransactionPackage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lombok.Setter;
import ysdesigns.moneytrackerapp.R;

/**
 * Created by james on 15-08-20.
 */
public class CategoryListAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    @Setter private List<Category> categoryList;

    public CategoryListAdapter(Context context) {
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return categoryList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =  (convertView == null) ? setUpConvertView(parent) : convertView;
        final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.title.setText(categoryList.get(position).getName());
//        viewHolder.icon.setImageDrawable(categoryList.get(position).getImageDrawable());

        return convertView;
    }

    private static class ViewHolder {
        public ImageView icon;
        public/* Roboto */TextView title;
    }

    private View setUpConvertView(ViewGroup parent) {

        final ViewHolder holder = new ViewHolder();
        final View convertView = inflater.inflate(R.layout.list_item_icon_and_title, parent, false);

//        holder.icon = (ImageView) convertView.findViewById(R.id.imageView);
        holder.title = (TextView) convertView.findViewById(R.id.name);
        convertView.setTag(holder);
        return convertView;
    }
}
