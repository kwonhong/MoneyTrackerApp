package ysdesigns.moneytrackerapp.DashboardPackage;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ysdesigns.moneytrackerapp.R;
import ysdesigns.moneytrackerapp.UtilPackage.Utils;
import ysdesigns.moneytrackerapp.Views.AnimatedExpandableListView;

/**
 * Created by james on 15-08-15.
 */
public class DashBoardFragment extends Fragment{

    private AnimatedExpandableListView listView;
    private DashBoardAdapter dashBoardAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard2, container, false);

        List<DashBoardAdapter.GroupItem> items = new ArrayList<>();
        items = fillData(items);

        dashBoardAdapter = new DashBoardAdapter(getActivity());
        dashBoardAdapter.setData(items);

        listView = (AnimatedExpandableListView) rootView.findViewById(R.id.list_view);
        listView.setDividerHeight(0);
        listView.setAdapter(dashBoardAdapter);
        Utils.setListViewHeight(listView);


        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Utils.setListViewHeight(parent, groupPosition);
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroupWithAnimation(groupPosition);
                } else {
                    listView.expandGroupWithAnimation(groupPosition);
                }

                return true;
            }

        });

        // Set indicator (arrow) to the right
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                50, r.getDisplayMetrics());
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            listView.setIndicatorBounds(width - px, width);
        } else {
            listView.setIndicatorBoundsRelative(width - px, width);
        }

        return rootView;
    }

    private List<DashBoardAdapter.GroupItem> fillData(List<DashBoardAdapter.GroupItem> items) {
        DashBoardAdapter.GroupItem item = new DashBoardAdapter.GroupItem();
        item.title = "Product1";
        item.imageUrl = "http://pengaja.com/uiapptemplate/newphotos/shop/1.jpg";
        DashBoardAdapter.ChildItem child;
        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with paypal";
        item.items.add(child);

        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with Visa card";
        item.items.add(child);

        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with maestero card";
        item.items.add(child);

        items.add(item);

        item = new DashBoardAdapter.GroupItem();
        item.title = "Product2";
        item.imageUrl = "http://pengaja.com/uiapptemplate/newphotos/shop/2.jpg";
        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with paypal";
        item.items.add(child);

        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with Visa card";
        item.items.add(child);

        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with maestero card";
        item.items.add(child);

        items.add(item);

        item = new DashBoardAdapter.GroupItem();
        item.title = "Product3";
        item.imageUrl = "http://pengaja.com/uiapptemplate/newphotos/shop/3.jpg";
        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with paypal";
        item.items.add(child);

        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with Visa card";
        item.items.add(child);

        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with maestero card";
        item.items.add(child);

        items.add(item);

        item = new DashBoardAdapter.GroupItem();
        item.title = "Product4";
        item.imageUrl = "http://pengaja.com/uiapptemplate/newphotos/shop/4.jpg";
        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with paypal";
        item.items.add(child);

        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with Visa card";
        item.items.add(child);

        child = new DashBoardAdapter.ChildItem();
        child.title = "Pay with maestero card";
        item.items.add(child);

        items.add(item);

        return items;
    }




}
