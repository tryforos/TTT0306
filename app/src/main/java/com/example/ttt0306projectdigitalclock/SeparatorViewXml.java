package com.example.ttt0306projectdigitalclock;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static java.security.AccessController.getContext;

public class SeparatorViewXml extends ConstraintLayout {

    private View view;

    // constructors
    public SeparatorViewXml(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        //THIS GETS OVERRIDDEN
        //converts XML into views
        inflate(getContext(), R.layout.separator_view, this);
    }
    // END constructors

    public void setValue(boolean value){

        View viewTopDot = findViewById(R.id.viewTopDot);
        View viewBottomDot = findViewById(R.id.viewBottomDot);

        if(value == true){
            viewTopDot.setVisibility(VISIBLE);
            viewBottomDot.setVisibility(VISIBLE);
        } else {
            viewTopDot.setVisibility(INVISIBLE);
            viewBottomDot.setVisibility(INVISIBLE);
        }
    }

    public void setColor(int value) {

        //set views
        View viewTopDot = findViewById(R.id.viewTopDot);
        View viewBottomDot = findViewById(R.id.viewBottomDot);

        //set colors
        viewTopDot.setBackgroundColor(value);
        viewBottomDot.setBackgroundColor(value);

    }

}
