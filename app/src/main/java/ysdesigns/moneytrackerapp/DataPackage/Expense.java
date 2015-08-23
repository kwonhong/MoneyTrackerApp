package ysdesigns.moneytrackerapp.DataPackage;

import com.orm.SugarRecord;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by james on 15-08-23.
 */
@Data
@AllArgsConstructor
public class Expense extends SugarRecord<Expense> {
    private int categoryIndex;
    private String description;
    private Date transactionDate;
    private Double amount;

    public Expense() {
        // Empty Constructor Required for Sugar Library
    }
}
