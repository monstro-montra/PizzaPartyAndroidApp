package com.zybooks.pizzaparty;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final int SLICES_PER_PIZZA = 8;
    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumAttendEditText = findViewById(R.id.num_attend_edit_text);
        mNumPizzasTextView = findViewById(R.id.num_pizzas_text_view);
        mHowHungryRadioGroup = findViewById(R.id.hungry_radio_group);
    }

    public void calculateClick(View view) {
        String numAttendStr = mNumAttendEditText.getText().toString(); //store value from mNumAttendEditText to a string in numAttendStr
        Log.d(TAG, "number is " + numAttendStr);

        //if NumberFormatException not accounted for, the unhandled exception will crash the program.
        int numAttend;
        try {
            numAttend = Integer.parseInt(numAttendStr); //convert the string to an integer
        } catch (NumberFormatException er) {
            numAttend = 0;
        }
        
        int slicesPerPerson = 0;
        int checkId = mHowHungryRadioGroup.getCheckedRadioButtonId();

        //determine how many slices per person depending on how hungry the group is.
        if (checkId == R.id.light_radio_button){
            slicesPerPerson = 2;
        } else if (checkId == R.id.medium_radio_button){
            slicesPerPerson = 3;
        } else if (checkId == R.id.ravenous_radio_button){
            slicesPerPerson = 4;
        }

        //calculate how many pizzas total.
        int totalPizzas = (int) Math.ceil(numAttend * slicesPerPerson / (double) SLICES_PER_PIZZA);
        mNumPizzasTextView.setText("Total Pizzas: " + totalPizzas);

    }
}