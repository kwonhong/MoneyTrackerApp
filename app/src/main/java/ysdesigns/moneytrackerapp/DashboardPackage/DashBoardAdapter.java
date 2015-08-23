package ysdesigns.moneytrackerapp.DashboardPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import ysdesigns.moneytrackerapp.R;
import ysdesigns.moneytrackerapp.Views.AnimatedExpandableListView;

/**
 * Created by james on 15-08-17.
 */
public class DashBoardAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter
        implements View.OnClickListener {

    @Setter private List<GroupItem> items;
    private LayoutInflater inflater;
    private Context context;

    public DashBoardAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setData(List<GroupItem> items) {
        this.items = items;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        ChildItem item = getChild(groupPosition, childPosition);
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = inflater
                    .inflate(R.layout.list_item_expandable_dashboard_child,
                            parent, false);
//            holder.title = (TextView) convertView
//                    .findViewById(R.id.list_item_expandable_shop_child_title);
//            holder.layout = (LinearLayout) convertView
//                    .findViewById(R.id.list_item_expandable_shop_child_layout);
//            holder.layout.setOnClickListener(this);
            //			holder.icon = (TextView) convertView
            //					.findViewById(R.id.list_item_expandable_shop_child_icon);
            //			holder.icon.setOnClickListener(this);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        //		holder.icon.setTag(childPosition);
//        holder.layout.setTag(childPosition);
//        holder.title.setText(item.title.toUpperCase());

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return items.get(groupPosition).items.size();
    }

    @Override
    public GroupItem getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).items.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder holder;
        GroupItem item = getGroup(groupPosition);
        if (convertView == null) {
            holder = new GroupHolder();
            convertView = inflater.inflate(
                    R.layout.list_item_expandable_dashboard_group, parent, false);
//            holder.title = (TextView) convertView
//                    .findViewById(R.id.list_item_expandable_shop_title);
//            holder.button = (TextView) convertView
//                    .findViewById(R.id.list_item_expandable_shop_button);
//            holder.image = (ImageView) convertView
//                    .findViewById(R.id.list_item_expandable_shop_image);
//            holder.button.setOnClickListener(this);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
//        holder.button.setTag(groupPosition);
//        ImageUtil.displayImage(holder.image, item.imageUrl, null);
//        holder.title.setText(item.title);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

//        int position = (Integer) v.getTag();
//        switch (v.getId()) {
//            case R.id.list_item_expandable_shop_button:
//                // click on explore button
//                Toast.makeText(context, "BUY " + position,
//                        Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.list_item_expandable_shop_child_layout:
//                // click on explore button
//                Toast.makeText(context,
//                        "Pay option:  " + position, Toast.LENGTH_SHORT).show();
//                break;
//        }
    }

    public static class GroupItem {
        String title;
        String imageUrl;
        List<ChildItem> items = new ArrayList<ChildItem>();
    }

    public static class ChildItem {
        String title;
    }

    public static class ChildHolder {
        TextView title;
        LinearLayout layout;
    }

    public static class GroupHolder {
        TextView title;
        ImageView image;
        TextView button;
    }


}
