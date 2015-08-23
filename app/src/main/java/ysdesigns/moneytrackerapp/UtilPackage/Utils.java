package ysdesigns.moneytrackerapp.UtilPackage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Arrays;
import java.util.List;

import ysdesigns.moneytrackerapp.NewTransactionPackage.Category;
import ysdesigns.moneytrackerapp.R;

/**
 * Created by james on 15-08-09.
 */
public class Utils {

    public static String getString(Context context, int keyID) {
        return context.getResources().getString(keyID);
    }

    public static Drawable getDrawable(Context context, int keyID) {
        return context.getResources().getDrawable(keyID);
    }


    public static int getSampleColor(Context context) {
        int color = context.getResources().getColor(R.color.material_grey_100);
        return Color.argb(0xCD, Color.red(color), Color.green(color),
                Color.blue(color));
    }

    public static List<Category> getDefaultExpenseCategory(Context context) {
        Category foodCategory = new Category("Food", getDrawable(context,R.drawable.ic_drawer));
        Category educationCategory = new Category("Education", getDrawable(context,R.drawable.ic_drawer));
        Category billCategory = new Category("Bill", getDrawable(context, R.drawable.ic_calendar));
        Category clothingCategory = new Category("Clothing", getDrawable(context, R.drawable.ic_calendar));
        Category EntertainmentCategory = new Category("Entertainment", getDrawable(context, R.drawable.ic_calendar));
        Category GroceryCategory = new Category("Groceries", getDrawable(context, R.drawable.ic_calendar));


        return Arrays.asList(foodCategory, educationCategory, billCategory, clothingCategory, EntertainmentCategory, GroceryCategory);
    }

    public static void initializeImageLoader(Context context) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        }
    }

    public static void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}
