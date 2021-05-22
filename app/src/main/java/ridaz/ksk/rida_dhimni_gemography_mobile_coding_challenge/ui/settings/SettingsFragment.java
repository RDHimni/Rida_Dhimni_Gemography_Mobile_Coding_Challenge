package ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.ui.settings;

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

import java.util.zip.Inflater;

import dagger.hilt.android.AndroidEntryPoint;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.R;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.databinding.SettingsFragmentBinding;
import ridaz.ksk.rida_dhimni_gemography_mobile_coding_challenge.ui.trending.TrendingViewModel;

@AndroidEntryPoint
public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;

    private SettingsFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.settings_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        getActivity().setTitle("Settings");

        return binding.getRoot();
    }


}