package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.ui.trending;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dagger.hilt.android.AndroidEntryPoint;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.R;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.databinding.TrendingFragmentBinding;


@AndroidEntryPoint
public class TrendingFragment extends Fragment {

    private TrendingViewModel mViewModel;

    private TrendingFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.trending_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(TrendingViewModel.class);
        getActivity().setTitle("Trending Repos");


        return binding.getRoot();
    }



}