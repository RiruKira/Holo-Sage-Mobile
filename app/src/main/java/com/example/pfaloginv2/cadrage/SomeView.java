package com.example.pfaloginv2.cadrage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.pfaloginv2.BitmapExt.BitmapHelper;
import com.example.pfaloginv2.beans.Coords;
import com.example.pfaloginv2.beans.ListHelper;

import java.util.ArrayList;
import java.util.List;

public class SomeView extends View implements View.OnTouchListener {
    private Paint paint;
    public static List<Coords> points;
    int DIST = 2;
    boolean flgPathDraw = true;

    Coords mfirstpoint = null;
    boolean bfirstpoint = false;

    Coords mlastpoint = null;
    int countPoints = 0;
    Bitmap bitmap = BitmapHelper.getInstance().getBitmap(); //get bitmap stored in instance
    Context mContext;
    private static List<Coords> coords;

    public SomeView(Context c) {
        super(c);

        mContext = c;
        setFocusable(true);
        setFocusableInTouchMode(true);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        paint.setAlpha(50);

        this.setOnTouchListener(this);
        points = new ArrayList<Coords>();
        coords = new ArrayList<Coords>();
        bfirstpoint = false;
    }

    public SomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setFocusable(true);
        setFocusableInTouchMode(true);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.WHITE);

        this.setOnTouchListener(this);
        points = new ArrayList<Coords>();
        bfirstpoint = false;

    }


    public void onDraw(Canvas canvas) {
        int widtha= 1080;
        int heighta= 1080;
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, widtha, heighta, true);
        canvas.drawBitmap(resizedBitmap, 0, 0, null);
        Path path = new Path();
        boolean first = true;

        for (int i = 0; i < points.size(); i += 2) {
            Coords point = points.get(i);
            if (first) {
                first = false;
                path.moveTo(point.x, point.y);
            } else if (i < points.size() - 1) {
                Coords next = points.get(i + 1);
                path.quadTo(point.x, point.y, next.x, next.y);
            } else {
                mlastpoint = points.get(i);
                path.lineTo(point.x, point.y);
            }
        }
        canvas.drawPath(path, paint);
    }

    public boolean onTouch(View view, MotionEvent event) {
        // if(event.getAction() != MotionEvent.ACTION_DOWN)
        // return super.onTouchEvent(event);

        Coords point = new Coords();
        point.x = (int) event.getX();
        point.y = (int) event.getY();

        if (flgPathDraw) {

            if (bfirstpoint) {

                if (comparepoint(mfirstpoint, point)) {
                    // points.add(point);
                    points.add(mfirstpoint);
                    flgPathDraw = false;
                    showcropdialog();
                } else {
                    points.add(point);
                }
            } else {
                points.add(point);
            }

            if (!(bfirstpoint)) {

                mfirstpoint = point;
                bfirstpoint = true;
            }
        }

        invalidate();

        //Copy the list to a new list 'listPoints' and delete the repetition of points
        if (countPoints % 2 == 0) {
            coords.add(new Coords(point.x, point.y));
        }
        countPoints++;
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mlastpoint = point;
            if (flgPathDraw) {
                if (points.size() > 12) {
                    if (!comparepoint(mfirstpoint, mlastpoint)) {
                        flgPathDraw = false;
                        points.add(mfirstpoint);
                        showcropdialog();
                    }
                }
            }
        }

        return true;
    }

    private boolean comparepoint(Coords first, Coords current) {
        int left_range_x = (int) (current.x - 3);
        int left_range_y = (int) (current.y - 3);

        int right_range_x = (int) (current.x + 3);
        int right_range_y = (int) (current.y + 3);

        if ((left_range_x < first.x && first.x < right_range_x)
                && (left_range_y < first.y && first.y < right_range_y)) {
            if (points.size() < 10) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public void fillinPartofPath() {
        Coords point = new Coords();
        point.x = points.get(0).x;
        point.y = points.get(0).y;

        points.add(point);
        invalidate();
    }

    public void resetView() {
        coords.clear();
        points.clear();
        countPoints = 0;
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        bfirstpoint=false;
        flgPathDraw = true;
        invalidate();
    }

    private void showcropdialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent;
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
                        Log.e("list", coords.toString());
                        ListHelper.getInstance().setCoords(coords);
                        ((Activity) mContext).finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        resetView();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Voulez vous continuez?")
                .setPositiveButton("Envoyer", dialogClickListener)
                .setNegativeButton("Annuler", dialogClickListener).show()
                .setCancelable(false);
    }
}