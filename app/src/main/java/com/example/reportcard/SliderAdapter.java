package com.example.reportcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;

    public SliderAdapter(Context context){
        this.context = context;
    }

    // Arrays
    public int[] sliderIcons = {
        R.drawable.icon_read,
    R.drawable.icon_eat,
    R.drawable.icon_code
    };

    public String[] sliderHeadings = {
        "HEADING",
        "HEADING",
        "HEADING"
    };

    public String[] sliderDescription = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
            "sed to elusmod tempor incididunt ut et dolore labore etmagna aliqua.",

            "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                    "sed to elusmod tempor incididunt ut et dolore labore etmagna aliqua.",

            "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                    "sed to elusmod tempor incididunt ut et dolore labore etmagna aliqua."
    };



    @Override
    public int getCount() {
        return sliderHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImage = view.findViewById(R.id.ivSlideIcon);
        slideImage.setImageResource(sliderIcons[position]);

        TextView slideHeading = view.findViewById(R.id.txtSlideHeading);
        slideHeading.setText(sliderHeadings[position]);

        TextView slideDescription = view.findViewById(R.id.txtSlideDescription);
        slideDescription.setText(sliderDescription[position]);

        container.addView(view);

        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);

        super.destroyItem(container, position, object);
    }
}
