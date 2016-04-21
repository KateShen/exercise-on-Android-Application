package com.example.u5778016.calculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText input;
    private TextView output;
    private String inputString;
    private int hisId;
    private HistoryDB db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.input);
        output = (TextView)findViewById(R.id.output);
        db = new HistoryDB(this);
        Intent intent = getIntent();
        hisId = intent.getIntExtra(ListsActivity.EXTRA_HIS_ID, -1);

        if (hisId > 0) {
            History history = db.getHis(hisId);
            input.setText(history.getFormula());
            output.setText(history.getResult());
        }

        int idList[] = {R.id.bt_0, R.id.bt_1, R.id.bt_2, R.id.bt_3, R.id.bt_4, R.id.bt_5, R.id.bt_6, R.id.bt_7, R.id.bt_8, R.id.bt_9,
                R.id.bt_div, R.id.bt_mul, R.id.bt_minus, R.id.bt_plus, R.id.bt_left, R.id.bt_right, R.id.bt_point,
                R.id.bt_cos, R.id.bt_sin, R.id.bt_tan, R.id.bt_e, R.id.bt_pi, R.id.bt_power, R.id.bt_lg, R.id.bt_ln, R.id.bt_mod, R.id.bt_root};
        for(int id : idList) {
            View v = findViewById(id);
            v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String str = input.getText().toString();
                    switch (v.getId()) {
                        case R.id.bt_clear:
                            input.setText("");
                            break;
                        case R.id.bt_point:
                            input.setText(str + ".");
                            break;
                        case R.id.bt_left:
                            input.setText(str + "(");
                            break;
                        case R.id.bt_right:
                            input.setText(str + ")");
                            break;
                        case R.id.bt_minus:
                            input.setText(str + "-");
                            break;
                        case R.id.bt_plus:
                            input.setText(str + "+");
                            break;
                        case R.id.bt_mul:
                            input.setText(str + "*");
                            break;
                        case R.id.bt_div:
                            input.setText(str + "/");
                            break;
                        case R.id.bt_0:
                            input.setText(str + "0");
                            break;
                        case R.id.bt_1:
                            input.setText(str + "1");
                            break;
                        case R.id.bt_2:
                            input.setText(str + "2");
                            break;
                        case R.id.bt_3:
                            input.setText(str + "3");
                            break;
                        case R.id.bt_4:
                            input.setText(str + "4");
                            break;
                        case R.id.bt_5:
                            input.setText(str + "5");
                            break;
                        case R.id.bt_6:
                            input.setText(str + "6");
                            break;
                        case R.id.bt_7:
                            input.setText(str + "7");
                            break;
                        case R.id.bt_8:
                            input.setText(str + "8");
                            break;
                        case R.id.bt_9:
                            input.setText(str + "9");
                            break;
                    }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        } else if(id == R.id.action_view) {
            Intent intent = new Intent(this, ListsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void analysisinput(View view){
        inputString = input.getText().toString();

        if (inputString.equals("") || inputString==null){Toast.makeText(MainActivity.this,"Nothing Input",Toast.LENGTH_LONG).show();
        }
        else {
            double aftercalcu = Formula.getInstnace().formula(inputString);
            output.setText(Double.toString(aftercalcu));
            save();
        }
    }

    public void clearAll(View view) {
        input.setText("");
    }

    public void viewHistory(View view) {
        Intent intent = new Intent(this, ListsActivity.class);
        startActivity(intent);
    }

    public void save() {
        String formula = input.getText().toString();
        String result = output.getText().toString();
        /*judge whether formula or result is null
          If yes, it will give a toast. If no, it will put formula and result insert into database.
          */
        if(formula.equals("")||result.equals(""))
        {
            Toast.makeText(this,"Formula or result is null", Toast.LENGTH_SHORT).show();
        }else
        {
            ToDatabase();	//Insert to database
            Toast.makeText(this,"Save success", Toast.LENGTH_SHORT).show();
        }
    }

    public final int ToDatabase()
    {
        //put title and content to the database
        String formula = input.getText().toString();
        String result = output.getText().toString();
        int newId = -1;
        History newNote = new History(formula, result);
        newId = db.addHis(newNote);
        return newId;
    }
}
