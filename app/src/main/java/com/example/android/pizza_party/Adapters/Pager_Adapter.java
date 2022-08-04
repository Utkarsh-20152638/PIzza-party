package com.example.android.pizza_party.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.android.pizza_party.Non_veg;
import com.example.android.pizza_party.Sides;
import com.example.android.pizza_party.Veg;

public class Pager_Adapter extends FragmentStatePagerAdapter {


    public Pager_Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return new Veg();
         else if (position==1)
            return new Non_veg();
            else
                return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
            return "Veggies";
        else if(position==1)
            return "Non-Veg";

        else
            return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
