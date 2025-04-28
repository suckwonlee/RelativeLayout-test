package kr.ac.kopo.etc_layout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GridDir05_5 extends AppCompatActivity {

    EditText input1, input2;
    Button[] btnNums = new Button[10];
    Button btn_Plus, btn_Minus, btn_Multiply, btn_Divide;

    TextView text_result;
    int[] btnNumIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
    String num1 = "";
    String num2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.grid_dir05_5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);

        for (int i = 0; i < btnNums.length; i++) {
            btnNums[i] = findViewById(btnNumIds[i]);
        }

        btn_Plus = findViewById(R.id.btn_plus);
        btn_Minus = findViewById(R.id.btn_minus);
        btn_Multiply = findViewById(R.id.btn_multiply);
        btn_Divide = findViewById(R.id.btn_divide);

        text_result = findViewById(R.id.text_result);

        for (int i = 0; i < btnNums.length; i++) {
            final int index = i;

            btnNums[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (input1.isFocused()) {
                        num1 = input1.getText().toString() + btnNums[index].getText().toString();
                        input1.setText(num1);
                    } else if (input2.isFocused()) {
                        num2 = input2.getText().toString() + btnNums[index].getText().toString();
                        input2.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트 텍스트를 선택해 주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        btn_Plus.setOnClickListener(btnListener);
        btn_Minus.setOnClickListener(btnListener);
        btn_Divide.setOnClickListener(btnListener);
        btn_Multiply.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button eventBtn=(Button) v;
            String editStr1=input1.getText().toString();
            String editStr2=input2.getText().toString();
            int editNum1=Integer.parseInt(editStr1);
            int editNum2=Integer.parseInt(editStr2);
            int result=0;
            if(eventBtn==btn_Plus){
                result=editNum1+editNum2;
            } else if (eventBtn==btn_Minus) {
                result=editNum1-editNum2;
            } else if (eventBtn==btn_Divide) {
                if (editNum2 == 0||editNum1==0) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = editNum1 / editNum2;
            }else{
                result=editNum1*editNum2;}


            text_result.setText("계산결과: "+result);
        }
    };
}
