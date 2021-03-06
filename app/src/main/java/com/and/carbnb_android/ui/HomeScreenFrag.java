package com.and.carbnb_android.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.and.carbnb_android.CarsData;
import com.and.carbnb_android.R;
import com.and.carbnb_android.SearchRecyclerViewAdapter;
import com.and.carbnb_android.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeScreenFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeScreenFrag extends Fragment {

    SliderView sliderView;
    int[] images = {R.drawable.alfaromeo,
            R.drawable.cadillac,
            R.drawable.elantra,
            R.drawable.wrangler,
            R.drawable.maybach};

    private ArrayList<CarsData> carsData = new ArrayList<>();
    private RecyclerView giftPageRecyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeScreenFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeScreenFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeScreenFrag newInstance(String param1, String param2) {
        HomeScreenFrag fragment = new HomeScreenFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        sliderView = view.findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
//        Button button = view.findViewById(R.id.navigator_btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                SearchScreenFrag searchScreenFrag = new SearchScreenFrag();
//                transaction.replace(R.id.home_screen_frag, searchScreenFrag);
//                transaction.commit();
//            }
//        });

        giftPageRecyclerView = view.findViewById(R.id.search_recyclerview);

        setProductsInfo();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        SearchRecyclerViewAdapter adapter = new SearchRecyclerViewAdapter(carsData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        giftPageRecyclerView.setLayoutManager(layoutManager);
        giftPageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        giftPageRecyclerView.setAdapter(adapter);
    }

    private void setProductsInfo() {
        for(int i = 0; i < 10; i++ ){
            carsData.add(new CarsData("V-8 Engine","Product Name "+i, "150$"));
        }
    }
}