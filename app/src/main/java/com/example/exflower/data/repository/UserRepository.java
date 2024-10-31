package com.example.exflower.data.repository;

import com.example.exflower.data.repository.local.SharedPrefsManager;

public class UserRepository  {
    private SharedPrefsManager sharedPrefsManager;

    public UserRepository() {
        // Initialize dependencies
    }

    public boolean hasValidToken() {
        // Kiểm tra token trong SharedPreferences
        return false; // Temporary return
    }
}
