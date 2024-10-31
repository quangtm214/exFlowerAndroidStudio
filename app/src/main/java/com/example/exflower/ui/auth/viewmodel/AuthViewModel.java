package com.example.exflower.ui.auth.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.exflower.data.repository.UserRepository;

public class AuthViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>(false);
    private final UserRepository userRepository;

    public AuthViewModel() {
        userRepository = new UserRepository();
        // Kiểm tra trạng thái đăng nhập khi khởi tạo
        checkLoginState();
    }

    public LiveData<Boolean> getLoginState() {
        return isLoggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn.getValue() != null && isLoggedIn.getValue();
    }

    private void checkLoginState() {
        // Kiểm tra token hoặc thông tin đăng nhập từ SharedPreferences
        isLoggedIn.setValue(userRepository.hasValidToken());
    }
}
