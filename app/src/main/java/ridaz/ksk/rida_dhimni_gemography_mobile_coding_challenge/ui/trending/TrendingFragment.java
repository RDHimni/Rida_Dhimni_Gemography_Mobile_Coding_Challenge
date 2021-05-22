package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.ui.trending;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.R;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.adapters.TrendingReposAdapter;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.databinding.TrendingFragmentBinding;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.models.Item;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.models.Respanse;


@AndroidEntryPoint
public class TrendingFragment extends Fragment {

    private TrendingViewModel mViewModel;

    private TrendingFragmentBinding binding;

    private TrendingReposAdapter trendingReposAdapter;
    private ArrayList<Item> itemList ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.trending_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(TrendingViewModel.class);
        getActivity().setTitle("Trending Repos");

        itemList = new ArrayList<>();
        trendingReposAdapter = new TrendingReposAdapter(getContext(),itemList);
        binding.trendingReposList.setAdapter(trendingReposAdapter);
        binding.trendingReposList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));

        mViewModel.getReps("created:>2017-10-22","stars","desc","0");

        mViewModel.getResult().observe(getViewLifecycleOwner(), new Observer<Respanse>() {
            @Override
            public void onChanged(Respanse respanse) {
                itemList.addAll(respanse.getItems());
                trendingReposAdapter.setModel_list(itemList);

            }
        });

        return binding.getRoot();
    }



}