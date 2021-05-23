package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.ui.trending;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;

import dagger.hilt.android.AndroidEntryPoint;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.R;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.adapters.TrendingReposAdapter;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.databinding.TrendingFragmentBinding;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.models.Item;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.models.Respanse;


@AndroidEntryPoint
public class TrendingFragment extends Fragment {

    private static final String TAG = "TrendingFragment";

    private TrendingViewModel mViewModel;

    private TrendingFragmentBinding binding;

    private TrendingReposAdapter trendingReposAdapter;
    private ArrayList<Item> itemList;
    private int pageNumber = 0, per_page = 100;
    private String last_30_day_date;

    private Boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************onCreateView()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.trending_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(TrendingViewModel.class);
        getActivity().setTitle("Trending Repos");

        init();

        return binding.getRoot();
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************liveData()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    private void liveData() {
        mViewModel.getResult().observe(getViewLifecycleOwner(), new Observer<Respanse>() {
            @Override
            public void onChanged(Respanse respanse) {

                itemList.addAll(respanse.getItems());
                trendingReposAdapter.setModel_list(itemList);


                binding.swipe.setRefreshing(false);
                binding.progressBarhor.setVisibility(View.INVISIBLE);
                binding.progressBarCercle.setVisibility(View.INVISIBLE);



            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************init()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    private void init() {

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~init list Repo~~~~~~~~~~~~~//
        itemList = new ArrayList<>();
        trendingReposAdapter = new TrendingReposAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.trendingReposList.setAdapter(trendingReposAdapter);
        binding.trendingReposList.setLayoutManager(layoutManager);

        /**~~~~LiveData~~~~~~*/
        liveData();

        /**~~~~getLast_30_day_date~~~~~~*/
        getLast_30_day_date();

        /**~~~~getData First Data~~~~~~*/
        binding.progressBarCercle.setVisibility(View.VISIBLE);
        getData();

        /**~~~~getData OnSwipe~~~~~~*/
        Onswipe();

        /**~~~~getData OnScrolList~~~~~~*/
        OnScroledList(layoutManager);

    }
    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************getLast_30_day_date()***********************//////////////
    ////////////////////////////////////////////////////////////////////////////////////
    private void getLast_30_day_date() {
        LocalDate now = LocalDate.now();
        LocalDate last_30_date = now.minusDays(30);
        last_30_day_date = last_30_date.toString();
        Log.d(TAG, "rida getLast_30_day_date: "+ last_30_day_date);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************getData()***********************/////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    private void getData() {
        pageNumber++;
        mViewModel.getReps("created:>" + last_30_day_date, "stars", "desc", pageNumber, per_page);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************OnScroledList()***********************//////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    private void OnScroledList(LinearLayoutManager layoutManager) {
        binding.trendingReposList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();


                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    binding.progressBarhor.setVisibility(View.VISIBLE);
                    isScrolling = false;

                    /**~~~~getData onScrolled~~~~~~*/

                    getData();
                }

            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////
    /////////////******************Onswipe()***********************////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    private void Onswipe() {
        binding.swipe.setColorSchemeResources(
                R.color.yellow_200,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ArrayList<Item> list = new ArrayList<>();
                for (int i = itemList.size() - 1; i >= 0; i--) {
                    list.add(itemList.get(i));
                }
                itemList.clear();
                itemList.addAll(list);
              /**~~~~getData On Swipe~~~~~~*/
                getData();

            }
        });
    }


}