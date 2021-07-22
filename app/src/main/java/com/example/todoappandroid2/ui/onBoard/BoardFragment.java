package com.example.todoappandroid2.ui.onBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.example.todoappandroid2.R;
import com.example.todoappandroid2.Prefs;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import org.jetbrains.annotations.NotNull;

public class BoardFragment extends Fragment {
    private Button btnSkip;
    DotsIndicator dotsIndicator;
    ViewPager2 viewPager2;
    BoardAdapter boardAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dotsIndicator = view.findViewById(R.id.dots_indicator);
        viewPager2 =  view.findViewById(R.id.viewPager2);
        boardAdapter = new BoardAdapter();
        viewPager2.setAdapter(boardAdapter);
        dotsIndicator.setViewPager2(viewPager2);

        btnSkip = view.findViewById(R.id.btn_skip);
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(R.id.navigation_home);
                close();
            }
        });

        private void close() {
            Prefs.getInstance().saveBoardsState();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigateUp();
        }

        boardAdapter.SetOpenHome(this::btnFinishClick);


        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }

    private void navigate(int navigate) {
        new Prefs(requireContext()).saveBoardsState();
        NavController navController = Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment);
        navController.navigate(navigate);
    }

    public void btnFinishClick() {
        navigate(R.id.navigation_home);
    }
}