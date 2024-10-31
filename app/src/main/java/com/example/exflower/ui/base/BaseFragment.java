package com.example.exflower.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class BaseFragment extends Fragment {
    protected NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    protected void navigate(int destinationId) {
        navController.navigate(destinationId);
    }

    protected void navigate(int destinationId, Bundle args) {
        navController.navigate(destinationId, args);
    }
}
