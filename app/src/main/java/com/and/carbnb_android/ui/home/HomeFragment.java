package com.and.carbnb_android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.and.carbnb_android.R;
import com.and.carbnb_android.databinding.FragmentHomeBinding;
import com.and.carbnb_android.ui.CartScreenFrag;
import com.and.carbnb_android.ui.GarageFrag;
import com.and.carbnb_android.ui.HomeScreenFrag;
import com.and.carbnb_android.ui.SearchScreenFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ViewPager actuator;
    private BottomNavigationView bottomBar;
    private HomeScreenFrag homeFragment;
    private SearchScreenFrag searchScreenFrag;
    private GarageFrag garageFrag;
    private CartScreenFrag cartScreenFrag;
    private MenuItem item;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        actuator = root.findViewById(R.id.actuator);
        bottomBar = root.findViewById(R.id.bottom_navigation);

        bottomBar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home_indic:
                                actuator.setCurrentItem(0);
                                break;
                            case R.id.search_indic:
                                actuator.setCurrentItem(1);
                                break;
                            case R.id.cart_indic:
                                actuator.setCurrentItem(2);
                                break;
                            case R.id.garage_indic:
                                actuator.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        actuator.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (item != null) {
                    item.setChecked(false);
                } else {
                    bottomBar.getMenu().getItem(0).setChecked(false);
                }
                bottomBar.getMenu().getItem(position).setChecked(true);
                item = bottomBar.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(actuator);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupViewPager(ViewPager actuator) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        cartScreenFrag = new CartScreenFrag();
        garageFrag = new GarageFrag();
        searchScreenFrag = new SearchScreenFrag();
        homeFragment = new HomeScreenFrag();

        adapter.addFragment(homeFragment);
        adapter.addFragment(searchScreenFrag);
        adapter.addFragment(cartScreenFrag);
        adapter.addFragment(garageFrag);
        actuator.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }
}