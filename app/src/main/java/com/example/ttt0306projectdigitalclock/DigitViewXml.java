package com.example.ttt0306projectdigitalclock;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static java.security.AccessController.getContext;

public class DigitViewXml extends ConstraintLayout {

    private View view;

    // constructors
    public DigitViewXml(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        //THIS GETS OVERRIDDEN
        //converts XML into views
        inflate(getContext(), R.layout.digit_view, this);
    }
    // END constructors

    public void setValue(int value){

        //set views
        View viewTop = findViewById(R.id.viewTop);
        View viewLeftTop = findViewById(R.id.viewLeftTop);
        View viewRightTop = findViewById(R.id.viewRightTop);
        View viewMiddle = findViewById(R.id.viewMiddle);
        View viewBottom = findViewById(R.id.viewBottom);
        View viewLeftBottom = findViewById(R.id.viewLeftBottom);
        View viewRightBottom = findViewById(R.id.viewRightBottom);

        //set views accordingly
        if(value == 0){
            viewTop.setVisibility(VISIBLE);
            viewLeftTop.setVisibility(VISIBLE);
            viewRightTop.setVisibility(VISIBLE);
            viewMiddle.setVisibility(INVISIBLE);
            viewBottom.setVisibility(VISIBLE);
            viewLeftBottom.setVisibility(VISIBLE);
            viewRightBottom.setVisibility(VISIBLE);
        }
        else if (value == 1) {
            viewTop.setVisibility(INVISIBLE);
            viewLeftTop.setVisibility(INVISIBLE);
            viewRightTop.setVisibility(VISIBLE);
            viewMiddle.setVisibility(INVISIBLE);
            viewBottom.setVisibility(INVISIBLE);
            viewLeftBottom.setVisibility(INVISIBLE);
            viewRightBottom.setVisibility(VISIBLE);
        }
        else if (value == 2) {
            viewTop.setVisibility(VISIBLE);
            viewLeftTop.setVisibility(INVISIBLE);
            viewRightTop.setVisibility(VISIBLE);
            viewMiddle.setVisibility(VISIBLE);
            viewBottom.setVisibility(VISIBLE);
            viewLeftBottom.setVisibility(VISIBLE);
            viewRightBottom.setVisibility(INVISIBLE);
        }
        else if (value == 3) {
            viewTop.setVisibility(VISIBLE);
            viewLeftTop.setVisibility(INVISIBLE);
            viewRightTop.setVisibility(VISIBLE);
            viewMiddle.setVisibility(VISIBLE);
            viewBottom.setVisibility(VISIBLE);
            viewLeftBottom.setVisibility(INVISIBLE);
            viewRightBottom.setVisibility(VISIBLE);
        }
        else if (value == 4) {
            viewTop.setVisibility(INVISIBLE);
            viewLeftTop.setVisibility(VISIBLE);
            viewRightTop.setVisibility(VISIBLE);
            viewMiddle.setVisibility(VISIBLE);
            viewBottom.setVisibility(INVISIBLE);
            viewLeftBottom.setVisibility(INVISIBLE);
            viewRightBottom.setVisibility(VISIBLE);
        }
        else if (value == 5) {
            viewTop.setVisibility(VISIBLE);
            viewLeftTop.setVisibility(VISIBLE);
            viewRightTop.setVisibility(INVISIBLE);
            viewMiddle.setVisibility(VISIBLE);
            viewBottom.setVisibility(VISIBLE);
            viewLeftBottom.setVisibility(INVISIBLE);
            viewRightBottom.setVisibility(VISIBLE);
        }
        else if (value == 6) {
            viewTop.setVisibility(VISIBLE);
            viewLeftTop.setVisibility(VISIBLE);
            viewRightTop.setVisibility(INVISIBLE);
            viewMiddle.setVisibility(VISIBLE);
            viewBottom.setVisibility(VISIBLE);
            viewLeftBottom.setVisibility(VISIBLE);
            viewRightBottom.setVisibility(VISIBLE);
        }
        else if (value == 7) {
            viewTop.setVisibility(VISIBLE);
            viewLeftTop.setVisibility(INVISIBLE);
            viewRightTop.setVisibility(VISIBLE);
            viewMiddle.setVisibility(INVISIBLE);
            viewBottom.setVisibility(INVISIBLE);
            viewLeftBottom.setVisibility(INVISIBLE);
            viewRightBottom.setVisibility(VISIBLE);
        }
        else if (value == 8) {
            viewTop.setVisibility(VISIBLE);
            viewLeftTop.setVisibility(VISIBLE);
            viewRightTop.setVisibility(VISIBLE);
            viewMiddle.setVisibility(VISIBLE);
            viewBottom.setVisibility(VISIBLE);
            viewLeftBottom.setVisibility(VISIBLE);
            viewRightBottom.setVisibility(VISIBLE);
        }
        else { //value ==9
            viewTop.setVisibility(VISIBLE);
            viewLeftTop.setVisibility(VISIBLE);
            viewRightTop.setVisibility(VISIBLE);
            viewMiddle.setVisibility(VISIBLE);
            viewBottom.setVisibility(INVISIBLE);
            viewLeftBottom.setVisibility(INVISIBLE);
            viewRightBottom.setVisibility(VISIBLE);
        }
    }


    public void setColor(int value) {

        //set views
        View viewTop = findViewById(R.id.viewTop);
        View viewLeftTop = findViewById(R.id.viewLeftTop);
        View viewRightTop = findViewById(R.id.viewRightTop);
        View viewMiddle = findViewById(R.id.viewMiddle);
        View viewBottom = findViewById(R.id.viewBottom);
        View viewLeftBottom = findViewById(R.id.viewLeftBottom);
        View viewRightBottom = findViewById(R.id.viewRightBottom);

        //set colors
        viewTop.setBackgroundColor(value);
        viewLeftTop.setBackgroundColor(value);
        viewRightTop.setBackgroundColor(value);
        viewMiddle.setBackgroundColor(value);
        viewBottom.setBackgroundColor(value);
        viewLeftBottom.setBackgroundColor(value);
        viewRightBottom.setBackgroundColor(value);

    }

}
