package me.satelitkangkung.grafkom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_koorgaris)
    Button btnKoorgaris;
    @BindView(R.id.btn_koorlingkaran)
    Button btnKoorlingkaran;
    @BindView(R.id.btn_koorelips)
    Button btnKoorelips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_koorgaris)
    public void onBtnKoorgarisClicked() {
        Toast.makeText(getApplicationContext(),"Masukkan Parameter", Toast.LENGTH_SHORT).show();
        Intent intentGaris = new Intent(MainActivity.this, ParamGarisActivity.class);
        startActivity(intentGaris);
    }

    @OnClick(R.id.btn_koorlingkaran)
    public void onBtnKoorlingkaranClicked() {
        Toast.makeText(getApplicationContext(),"Masukkan Parameter", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, ParamLingkaranActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_koorelips)
    public void onBtnKoorelipsClicked() {
        Toast.makeText(getApplicationContext(),"Masukkan Parameter", Toast.LENGTH_SHORT).show();
        Intent intentElips = new Intent(MainActivity.this, ParamElipsActivity.class);
        startActivity(intentElips);
    }
}
