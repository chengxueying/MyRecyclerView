package recyclerview.hy.com.myrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button linearBtn;
    private Button gridBtn;
    private Button staggerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //声明控件
        linearBtn = (Button) findViewById(R.id.listView);
        gridBtn = (Button) findViewById(R.id.gridView);
        staggerBtn = (Button) findViewById(R.id.staggerView);
        //设置监听
        linearBtn.setOnClickListener(this);
        gridBtn.setOnClickListener(this);
        staggerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.listView:
                intent.setClass(this, LinearRecyclerActivity.class);
                startActivity(intent);
                break;
            case R.id.gridView:
                intent.setClass(this, GridRecyclerActivity.class);
                startActivity(intent);
                break;
            case R.id.staggerView:
                intent.setClass(this, StaggerRecyclerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
