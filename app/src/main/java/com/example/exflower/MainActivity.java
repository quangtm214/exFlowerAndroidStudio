package com.example.exflower;

import android.os.Bundle;
import android.view.Menu;

import com.example.exflower.ui.auth.viewmodel.AuthViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.exflower.databinding.ActivityMainBinding;

public class MainActivity  extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    private AuthViewModel authViewModel;

    // Danh sách các route được bảo vệ (yêu cầu đăng nhập)
    private final int[] protectedDestinations = new int[]{
            R.id.navigation_orders,
            R.id.navigation_notifications,
            R.id.navigation_profile
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupNavigation();
        setupAuthViewModel();
        setupNotificationHandler();
    }

    private void setupNavigation() {
        // Khởi tạo Bottom Navigation
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_orders,
                R.id.navigation_notifications,
                R.id.navigation_profile
        ).build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Xử lý navigation cho các màn hình được bảo vệ
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (isProtectedDestination(destination.getId()) && !authViewModel.isLoggedIn()) {
                // Lưu destination muốn đến
                Bundle args = new Bundle();
                args.putInt("returnToDestination", destination.getId());
                // Chuyển đến màn hình login
                navController.navigate(R.id.navigation_login, args);
            }
        });
    }

    private void setupAuthViewModel() {
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        // Observe login state changes
        authViewModel.getLoginState().observe(this, isLoggedIn -> {
            // Cập nhật UI dựa trên trạng thái đăng nhập
            updateUIForAuthState(isLoggedIn);
        });
    }

    private void setupNotificationHandler() {
        // Xử lý notification khi app được mở từ notification
        if (getIntent().hasExtra("notification_type")) {
            handleNotificationNavigation(
                    getIntent().getStringExtra("notification_type"),
                    getIntent().getExtras()
            );
        }
    }

    private boolean isProtectedDestination(int destinationId) {
        for (int id : protectedDestinations) {
            if (id == destinationId) return true;
        }
        return false;
    }

    private void handleNotificationNavigation(String type, Bundle data) {
        if (type == null) return;

        switch (type) {
            case "Notifications":
                navController.navigate(R.id.navigation_notifications);
                break;
            case "new-auction-bid":
                if (data != null && data.containsKey("flowerId")) {
                    Bundle args = new Bundle();
                    args.putString("id", data.getString("flowerId"));
                    navController.navigate(R.id.navigation_detail, args);
                }
                break;
            case "order-success":
                if (data != null && data.containsKey("orderCode")) {
                    Bundle args = new Bundle();
                    args.putString("orderCode", data.getString("orderCode"));
                    navController.navigate(R.id.navigation_order_detail, args);
                }
                break;
        }
    }

    private void updateUIForAuthState(boolean isLoggedIn) {
        // Cập nhật UI dựa trên trạng thái đăng nhập
        Menu menu = binding.navView.getMenu();
        menu.findItem(R.id.navigation_orders).setVisible(isLoggedIn);
        menu.findItem(R.id.navigation_notifications).setVisible(isLoggedIn);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}