package me.satelitkangkung.grafkom;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParamLingkaranActivity extends AppCompatActivity {

    @BindView(R.id.et_x)
    EditText etX;
    @BindView(R.id.et_y)
    EditText etY;
    @BindView(R.id.et_radius)
    EditText etRadius;
    @BindView(R.id.btn_drawCircle)
    Button btnDrawCircle;

    int typeLine = 0;
    int ketebalan = 2;
    int colorCircle;

    private int currentBackgroundColor = 0xffffffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_lingkaran);
        ButterKnife.bind(this);
    }

    public void onTipeGarisCircleClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_tipeCircle_solid:
                if (checked)
                    typeLine = 0;
                break;
            case R.id.rb_tipeCircle_dotted:
                if (checked)
                    typeLine = 1;
                break;
            case R.id.rb_tipeCircle_dashed:
                if (checked)
                    typeLine = 2;
                break;
        }
    }

    public void onKetebalanCircleClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_thighCircle_tipis:
                if (checked)
                    ketebalan = 2;
                break;
            case R.id.rb_thighCircle_sedang:
                if (checked)
                    ketebalan = 6;
                break;
            case R.id.rb_thighCircle_tebal:
                if (checked)
                    ketebalan = 12;
                break;
        }
    }

    public void onColorCircleCLicked(View view){
        ColorPickerDialogBuilder
                .with(ParamLingkaranActivity.this)
                .setTitle("Pilih Warna")
                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        colorCircle = selectedColor;
                    }
                })
                .setPositiveButton("pilih", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        colorCircle = selectedColor;
                    }
                })
                .setNegativeButton("tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }

    @OnClick(R.id.btn_drawCircle)
    public void onViewClicked() {
        if(validationForm()){
            final Intent intent = new Intent(ParamLingkaranActivity.this, LingkaranActivity.class);
            intent.putExtra("xcircle",Integer.parseInt(etX.getText().toString()));
            intent.putExtra("ycircle",Integer.parseInt(etY.getText().toString()));
            intent.putExtra("radcircle",Integer.parseInt(etRadius.getText().toString()));
            intent.putExtra("tipeGaris", typeLine);
            intent.putExtra("ketebalanElips", ketebalan);
            intent.putExtra("colorElips", colorCircle);

            final ProgressDialog progress = new ProgressDialog(this);
            progress.setTitle("Menghitung");
            progress.setMessage("Mohon menunggu, sesaat sebelum mulai pembentukan.");
            progress.show();

            Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    progress.cancel();
                    startActivity(intent);
                }
            };

            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 2000);
        }
    }

    public Boolean validationForm(){
        if(etX.getText().toString().equalsIgnoreCase("") || etY.getText().toString().equalsIgnoreCase("") || etRadius.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"Pastikan semua input terisi.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
