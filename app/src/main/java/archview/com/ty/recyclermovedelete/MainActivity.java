package archview.com.ty.recyclermovedelete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("ty test : "+i);
        }
        myAdapter = new MyAdapter(MainActivity.this,list);
        recyclerView.setAdapter(myAdapter);
        ItemTouchHelper.Callback callBack = new MyItemTouchHelper(myAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callBack);
        helper.attachToRecyclerView(recyclerView);
    }
}
