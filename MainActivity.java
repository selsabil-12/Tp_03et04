package com.example.smartrate;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValue;
    private TextView textViewResult;
    private RadioGroup radioGroup;
    private RadioButton radioButtonDinarToEuro, radioButtonEuroToDinar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValue = findViewById(R.id.editTextValue);
        textViewResult = findViewById(R.id.textViewResult);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonDinarToEuro = findViewById(R.id.radioButtonDinarToEuro);
        radioButtonEuroToDinar = findViewById(R.id.radioButtonEuroToDinar);


        editTextValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();

                if (!input.matches("^\\d*\\.?\\d*$")) {
                    editTextValue.setTextColor(Color.RED); // تغيير لون النص إلى الأحمر
                } else {
                    editTextValue.setTextColor(Color.BLACK); // إعادة اللون العادي
                }
            }
        });
    }


    public void convertir(View v) {
        String inputValue = editTextValue.getText().toString();

        if (!inputValue.matches("^\\d*\\.?\\d*$")) {
            Toast.makeText(this, "الرجاء إدخال رقم صحيح فقط", Toast.LENGTH_SHORT).show();
            return;
        }

        if (inputValue.isEmpty()) {
            Toast.makeText(this, "الرجاء إدخال قيمة", Toast.LENGTH_SHORT).show();
            return;
        }

        float value = Float.parseFloat(inputValue);

        if (radioButtonDinarToEuro.isChecked()) {
            float result = dinarToEuro(value);
            textViewResult.setText("Résultat: " + result + " EUR");
        } else if (radioButtonEuroToDinar.isChecked()) {
            float result = euroToDinar(value);
            textViewResult.setText("Résultat: " + result + " DZD");
        } else {
            Toast.makeText(this, "الرجاء اختيار نوع التحويل", Toast.LENGTH_SHORT).show();
        }
    }

    // دالتا التحويل بين الدينار واليورو
    private float dinarToEuro(float dinarValue)
    {
        return dinarValue / 147;  // مثال: 1 يورو = 147 دينار
    }

    private float euroToDinar(float euroValue)
    {
        return euroValue * 147;
    }
}
