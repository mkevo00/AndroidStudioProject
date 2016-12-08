package adagelic.fesb.hr.simplecalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // Views
    private EditText firstArgumentEt, secondArgumentEt;
    private Button addBtn, minusBtn, multiplyBtn, divisionBtn;
    private TextView expressionTv, resultTv;

    // logic
    private int pickedOperation = R.id.addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initiate all local views
        addViewReferences();

        // initiate required listeners
        addButtonListeners();
        addEditTextListeners();


        this.addBtn.setTextColor(Color.BLUE);
    }

    private void addViewReferences() {
        this.firstArgumentEt = (EditText) findViewById(R.id.firstArgument);
        this.secondArgumentEt = (EditText) findViewById(R.id.secondArgument);
        this.addBtn = (Button) findViewById(R.id.addButton);
        this.minusBtn = (Button) findViewById(R.id.minusButton);
        this.multiplyBtn = (Button) findViewById(R.id.multiplyButton);
        this.divisionBtn = (Button) findViewById(R.id.divisionButton);
        this.expressionTv = (TextView) findViewById(R.id.expressionTv);
        this.resultTv = (TextView) findViewById(R.id.resultTv);
    }

    private void addButtonListeners() {
        View.OnClickListener operationClickedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickedOperation = v.getId();
                calculateResult();
                setOperationButtonStyle((Button) v);
            }
        };

        this.addBtn.setOnClickListener(operationClickedListener);
        this.minusBtn.setOnClickListener(operationClickedListener);
        this.multiplyBtn.setOnClickListener(operationClickedListener);
        this.divisionBtn.setOnClickListener(operationClickedListener);
    }

    private void setOperationButtonStyle(Button selectedBtn) {
        this.addBtn.setTextColor(Color.BLACK);
        this.minusBtn.setTextColor(Color.BLACK);
        this.multiplyBtn.setTextColor(Color.BLACK);
        this.divisionBtn.setTextColor(Color.BLACK);

        selectedBtn.setTextColor(Color.BLUE);
    }

    private void calculateResult() {
        int firstArgument = Integer.valueOf(this.firstArgumentEt.getText().toString());
        int secondArgument = Integer.valueOf(this.secondArgumentEt.getText().toString());
        int result = 0;
        String operation = "";

        switch (this.pickedOperation) {
            case R.id.addButton:
                result = firstArgument + secondArgument;
                operation = "+";
                break;
            case R.id.minusButton:
                result = firstArgument - secondArgument;
                operation = "-";
                break;
            case R.id.multiplyButton:
                result = firstArgument * secondArgument;
                operation = "*";
                break;
            case R.id.divisionButton:
                if (secondArgument != 0) {
                    result = firstArgument / secondArgument;
                } else {
                    result = 0;
                }
                operation = "/";
                break;
        }

        this.expressionTv.setText(String.valueOf(firstArgument) + " " + operation + " " + String.valueOf(secondArgument) + " = ");
        this.resultTv.setText(String.valueOf(result));
    }

    private void addEditTextListeners() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    return;
                }

                calculateResult();
            }
        };

        this.firstArgumentEt.addTextChangedListener(textWatcher);
        this.secondArgumentEt.addTextChangedListener(textWatcher);
    }
}

