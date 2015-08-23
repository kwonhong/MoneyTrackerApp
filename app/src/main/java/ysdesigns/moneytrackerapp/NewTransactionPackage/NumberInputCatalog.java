package ysdesigns.moneytrackerapp.NewTransactionPackage;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import java.util.List;

import ysdesigns.moneytrackerapp.R;

/**
 * Created by james on 15-08-21.
 */
public class NumberInputCatalog extends Dialog {


    public NumberInputCatalog(Context context) {
        super(context);
        setDialogContent();
    }

    private void setDialogContent() {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_number_input);
        this.setTitle(null);

    }


}
