package example.sdbi.com.myzxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    private TextView showResult;
    private EditText minput;
    private ImageView mResult;
    private CheckBox mLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showResult = (TextView) findViewById(R.id.show_result);
        minput = (EditText) findViewById(R.id.et_text);
        mResult = (ImageView) findViewById(R.id.imageview);
        mLogo = (CheckBox) findViewById(R.id.cb_logo);
    }

    /**
     * 扫描二维码
     *
     * @param view
     */
    public void OnClick(View view) {
        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
    }

    /**
     * 生成二维码
     *
     * @param view
     */
    public void make(View view) {
        String input = minput.getText().toString();
        if (minput.getVisibility() == View.VISIBLE) {
            minput.setVisibility(View.GONE);
        } else {
            minput.setVisibility(View.VISIBLE);
        }
        if (input.equals("")) {
            Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap bitmap = EncodingUtils.createQRCode(input, 500, 500, mLogo.isChecked() ? BitmapFactory.decodeResource(getResources(), android.R.drawable.star_big_on) : null);
            mResult.setImageBitmap(bitmap);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            showResult.setText(result);
        }
    }
}
