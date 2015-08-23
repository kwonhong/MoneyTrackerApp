package ysdesigns.moneytrackerapp.NewTransactionPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ysdesigns.moneytrackerapp.DataPackage.Expense;
import ysdesigns.moneytrackerapp.R;
import ysdesigns.moneytrackerapp.UtilPackage.Utils;

public class NewExpenseActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    TextView categoryTxtView, balanceTxtView, calendarTxtView;
    EditText descriptionTxtView;
    TextView btnCancel, btnSubmit;

    SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat outputFormatter = DateFormat.getDateInstance(DateFormat.FULL);
    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.FRANCE);

    int selectedCategoryIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        final CategoryListDialog categoryListDialog =
                new CategoryListDialog(NewExpenseActivity.this,
                        Utils.getDefaultExpenseCategory(this));
        categoryListDialog.setCustomDialogListener(new CategoryListDialog.CustomDialogListener() {
            @Override
            public void handleConfirmAction(int position) {
                selectedCategoryIndex = position;
                categoryTxtView.setText(Utils.getDefaultExpenseCategory(getApplicationContext()).get(position).getName());
                categoryListDialog.dismiss();
            }
        });

        categoryTxtView = (TextView) findViewById(R.id.categoryTxtView);
        categoryTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryListDialog.show();
            }
        });


        final NumberInputCatalog numberInputCatalog =
                new NumberInputCatalog(NewExpenseActivity.this);
        balanceTxtView = (TextView) findViewById(R.id.balanceTxt);
        try {
            balanceTxtView.setText(numberFormat.parse("0").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        balanceTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberInputCatalog.show();
            }
        });

        calendarTxtView = (TextView) findViewById(R.id.calendarTxt);
        calendarTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        NewExpenseActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        descriptionTxtView = (EditText) findViewById(R.id.descriptionEditTxt);

        btnCancel = (TextView) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSubmit = (TextView) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Date date = outputFormatter.parse(calendarTxtView.getText().toString());
                    Double balance = Double.parseDouble(balanceTxtView.getText().toString());
                    int categoryIndex = selectedCategoryIndex;
                    String description = descriptionTxtView.getText().toString();
                    Expense expense = new Expense(categoryIndex, description, date, balance);
                    expense.save();
                    finish();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {


        try {
            Date date = inputFormatter.parse(String.format("%d/%d/%d", year, monthOfYear, dayOfMonth));
            calendarTxtView.setText(outputFormatter.format(date));
        } catch (ParseException e) {

        }


    }
}
