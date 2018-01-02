package archview.com.ty.recyclermovedelete;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by edz on 2018/1/2.
 */

public class MyItemTouchHelper extends ItemTouchHelper.Callback {
    private static final String TAG = "MyItemTouchHelper";

    private ItemTouchCallBack itemTouchCallBack;

    public MyItemTouchHelper(ItemTouchCallBack callBack) {
        this.itemTouchCallBack = callBack;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.d(TAG,"getMovementFlags");
        return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.d(TAG,"onMove:");
        itemTouchCallBack.onMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setScrollX(0);
        ((MyAdapter.MyViewHolder)viewHolder).button.setText("左滑删除");
        ViewGroup.LayoutParams layoutParams = ((MyAdapter.MyViewHolder)viewHolder).button.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = 240;
        ((MyAdapter.MyViewHolder)viewHolder).button.setLayoutParams(layoutParams);
        ((MyAdapter.MyViewHolder)viewHolder).button.setVisibility(View.VISIBLE);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.d(TAG,"onChildDraw:state = "+actionState + "dx = "+dX);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            viewHolder.itemView.scrollTo(-(int)dX,0);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }


    public interface ItemTouchCallBack{
        void onItemDelete(int position);
        void onMove(int fromPosition,int toPosition);
    }
}
