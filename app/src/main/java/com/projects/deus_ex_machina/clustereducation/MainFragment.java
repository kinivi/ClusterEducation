package com.projects.deus_ex_machina.clustereducation;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);

        //Getting ID of viewPager and tabLayout
        ViewPager viewPager = rootView.findViewById(R.id.viewPager);
        TabLayout tabLayout = rootView.findViewById(R.id.tabLayout);

        //Setting Fragment page adapter to View pager
        FragmentPageAdapter adapter = new FragmentPageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //setting icons for TabLayout
        tabLayout.setupWithViewPager(viewPager);
        int[] imageResId = {
                R.drawable.ic_dashboard_plate,
                R.drawable.ic_subjects,
                R.drawable.ic_list};
        for (int i = 0; i < imageResId.length; i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
        }

        return rootView;
    }

}
