package archview.com.ty.recyclermovedelete;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by edz on 2018/1/2.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements MyItemTouchHelper.ItemTouchCallBack {

    private Context context;
    private List<String> list;

    public MyAdapter(Context context,List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemDelete(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public void onMove(int fromPosition, int toPosition) {
        Collections.swap(list,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Button button;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            button = (Button) itemView.findViewById(R.id.btn);
        }
    }
}
