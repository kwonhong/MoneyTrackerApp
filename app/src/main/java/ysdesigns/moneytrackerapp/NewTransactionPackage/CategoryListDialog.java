package ysdesigns.moneytrackerapp.NewTransactionPackage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import lombok.Setter;
import ysdesigns.moneytrackerapp.R;

/**
 * Created by james on 15-08-20.
 */
public class CategoryListDialog extends Dialog {

    @Setter
    CustomDialogListener customDialogListener;
    TextView titleText, addCategoryBtn;
    ListView listView;
    Context context;

    public CategoryListDialog(Context context, List<Category> categories) {
        super(context);
        this.context = context;
        setDialogContent(categories);
    }

    private void setDialogContent(List<Category> categories) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_category_list);
        this.setTitle(null);

        // Setting ListView
        CategoryListAdapter adapter = new CategoryListAdapter(context);
        adapter.setCategoryList(categories);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setDivider(null);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                customDialogListener.handleConfirmAction(position);
            }
        });

        // Add Category Button
        addCategoryBtn = (TextView) findViewById(R.id.addCategoryBtn);
        addCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public interface CustomDialogListener {
        void handleConfirmAction(int position);
    }

}
