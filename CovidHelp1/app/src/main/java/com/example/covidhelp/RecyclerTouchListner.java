package com.example.covidhelp;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerTouchListner implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private ClickListner clickListner;

    public RecyclerTouchListner(Context context,final RecyclerView recyclerView,final ClickListner clickListner) {
        this.clickListner = clickListner;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child=recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child != null && clickListner != null)
                {
                    clickListner.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                }
            }
        });


    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        View child=rv.findChildViewUnder(e.getX(),e.getY());
        if(child != null && clickListner != null)
        {
            clickListner.onLongClick(child,rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    public interface ClickListner {

        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
        }
