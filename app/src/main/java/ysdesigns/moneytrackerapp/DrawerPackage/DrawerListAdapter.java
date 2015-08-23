package ysdesigns.moneytrackerapp.DrawerPackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import ysdesigns.moneytrackerapp.DashboardPackage.DashBoardFragment;
import ysdesigns.moneytrackerapp.R;
import ysdesigns.moneytrackerapp.SettingPackage.SettingFragment;

public class DrawerListAdapter extends BaseAdapter {

	@Setter
	private List<NavigationDrawerItem> drawerItems;
	private LayoutInflater inflater;

	public DrawerListAdapter(Context context) {
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.drawerItems = generateNavigationDrawerItem(context);
	}

	@Override
	public int getCount() {
		return drawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return drawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView =  (convertView == null) ? setUpConvertView(parent) : convertView;
		final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

		viewHolder.title.setText(drawerItems.get(position).getMenuItemText());
		viewHolder.icon.setImageDrawable(drawerItems.get(position).getIcon());
		viewHolder.fragment = drawerItems.get(position).getFragment();

		return convertView;
	}

	private View setUpConvertView(ViewGroup parent) {

		final ViewHolder holder = new ViewHolder();
		final View convertView = inflater.inflate(R.layout.list_item_drawer, parent, false);

		holder.icon = (ImageView) convertView.findViewById(R.id.imageView);
		holder.title = (TextView) convertView.findViewById(R.id.name);
		convertView.setTag(holder);
		return convertView;
	}

	private static class ViewHolder {
		public ImageView icon;
		public/* Roboto */TextView title;
		public Fragment fragment;
	}

	private List<NavigationDrawerItem> generateNavigationDrawerItem(Context context) {
		NavigationDrawerItem dashboardDrawerItem =
				new NavigationDrawerItem(
						context.getResources().getDrawable(R.drawable.ic_calendar),
						"Dashboard",
						new DashBoardFragment());

		NavigationDrawerItem settingDrawerItem =
				new NavigationDrawerItem(
						context.getResources().getDrawable(R.drawable.ic_calendar),
						"Settings",
						new SettingFragment());

		return Arrays.asList(dashboardDrawerItem, settingDrawerItem);
	}

	@AllArgsConstructor
	@Data
	public static class NavigationDrawerItem {

		@Setter private Drawable icon;
		@Setter private String menuItemText;
		@Setter private Fragment fragment;

	}
}
