package me.satelitkangkung.grafkom;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class ParamElipsActivity extends AppCompatActivity {

    @BindView(R.id.et_xjari)
    EditText etXjari;
    @BindView(R.id.et_yjari)
    EditText etYjari;
    @BindView(R.id.et_x1pusat)
    EditText etX1pusat;
    @BindView(R.id.et_y1pusat)
    EditText etY1pusat;
    @BindView(R.id.btn_drawElips)
    Button btnDrawElips;

    int typeLine = 0;
    int ketebalan = 2;
    int colorElips;

    private int currentBackgroundColor = 0xffffffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_elips);
        ButterKnife.bind(this);
    }

    public void onTipeGarisClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_tipe_solid:
                if (checked)
                    typeLine = 0;
                    break;
            case R.id.rb_tipe_dotted:
                if (checked)
                    typeLine = 1;
                break;
            case R.id.rb_tipe_dashed:
                if (checked)
                    typeLine = 2;
                    break;
        }
    }

    public void onKetebalanGarisClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_thigh_tipis:
                if (checked)
                    ketebalan = 2;
                break;
            case R.id.rb_thigh_sedang:
                if (checked)
                    ketebalan = 6;
                break;
            case R.id.rb_thigh_tebal:
                if (checked)
                    ketebalan = 12;
                break;
        }
    }


    @OnClick(R.id.btn_drawElips)
    public void onViewClicked() {
        if (validationForm()) {
            final Intent intent = new Intent(ParamElipsActivity.this, ElipsActivity.class);
            intent.putExtra("xjari", Integer.parseInt(etXjari.getText().toString()));
            intent.putExtra("yjari", Integer.parseInt(etYjari.getText().toString()));
            intent.putExtra("xpusat", Integer.parseInt(etX1pusat.getText().toString()));
            intent.putExtra("ypusat", Integer.parseInt(etY1pusat.getText().toString()));
            intent.putExtra("tipeGaris", typeLine);
            intent.putExtra("ketebalanElips", ketebalan);
            intent.putExtra("colorElips", colorElips);

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

    public void onColorElipsCLicked(View view){
        ColorPickerDialogBuilder
                .with(ParamElipsActivity.this)
                .setTitle("Pilih Warna")
                .initialColor(currentBackgroundColor)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
//                        toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                        colorElips = selectedColor;
                    }
                })
                .setPositiveButton("pilih", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
//                        changeBackgroundColor(selectedColor);
//                        toast("Beda onColorSelected: 0x" + Integer.toHexString(selectedColor));
                        colorElips = selectedColor;
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

    public Boolean validationForm(){
        if(etXjari.getText().toString().equalsIgnoreCase("") || etYjari.getText().toString().equalsIgnoreCase("") || etX1pusat.getText().toString().equalsIgnoreCase("") || etY1pusat.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"Pastikan semua input terisi.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
