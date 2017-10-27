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

public class ParamGarisActivity extends AppCompatActivity {

    @BindView(R.id.et_x0garis)
    EditText etX0garis;
    @BindView(R.id.et_y0garis)
    EditText etY0garis;
    @BindView(R.id.et_x1garis)
    EditText etX1garis;
    @BindView(R.id.et_y1garis)
    EditText etY1garis;
    @BindView(R.id.btn_drawLine)
    Button btnDrawLine;

    int typeLine = 0;
    int ketebalan = 2;
    int colorGaris;

    private int currentBackgroundColor = 0xffffffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_garis);
        ButterKnife.bind(this);
    }

    public void onTipeClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_tipeline_solid:
                if (checked)
                    typeLine = 0;
                break;
            case R.id.rb_tipeline_dotted:
                if (checked)
                    typeLine = 1;
                break;
            case R.id.rb_tipeline_dashed:
                if (checked)
                    typeLine = 2;
                break;
        }
    }

    public void onKetebalanClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_thighLine_tipis:
                if (checked)
                    ketebalan = 2;
                break;
            case R.id.rb_thighLine_sedang:
                if (checked)
                    ketebalan = 5;
                break;
            case R.id.rb_thighLine_tebal:
                if (checked)
                    ketebalan = 10;
                break;
        }
    }

    public void onColorElipsCLicked(View view){
        ColorPickerDialogBuilder
                .with(ParamGarisActivity.this)
                .setTitle("Pilih Warna")
                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
//                        toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                        colorGaris = selectedColor;
                    }
                })
                .setPositiveButton("pilih", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                        changeBackgroundColor(selectedColor);
//                        toast("Beda onColorSelected: 0x" + Integer.toHexString(selectedColor));
                        colorGaris = selectedColor;
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

    @OnClick(R.id.btn_drawLine)
    public void onViewClicked() {
        if (validationForm()){
            final Intent intent = new Intent(ParamGarisActivity.this, GarisActivity.class);
            intent.putExtra("x0line",Integer.parseInt(etX0garis.getText().toString()));
            intent.putExtra("x1line",Integer.parseInt(etX1garis.getText().toString()));
            intent.putExtra("y0line",Integer.parseInt(etY0garis.getText().toString()));
            intent.putExtra("y1line",Integer.parseInt(etY1garis.getText().toString()));

            intent.putExtra("tipeGaris", typeLine);
            intent.putExtra("ketebalanElips", ketebalan);
            intent.putExtra("colorGaris", colorGaris);


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
        if(etY0garis.getText().toString().equalsIgnoreCase("") || etX1garis.getText().toString().equalsIgnoreCase("") || etX0garis.getText().toString().equalsIgnoreCase("") || etY1garis.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"Pastikan semua input terisi.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
