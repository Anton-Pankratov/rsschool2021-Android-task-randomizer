package com.rsschool.android2021;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String ADD_FIRST_FRAGMENT = "add_first_fragment";
    private static final String REPLACE_ON_SECOND_FRAGMENT = "replace_on_second_fragment";
    private static final String BACK_TO_FIRST_FRAGMENT = "back_to_first_fragment";

    private int previousValue = 0;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(previousValue, ADD_FIRST_FRAGMENT);
    }

    private void openFirstFragment(int previousNumber, String action) {
        firstFragment = FirstFragment.newInstance(previousNumber);
        makeFragmentTransaction(action);
        setMinMaxValuesPassInterface();
    }

    private void openSecondFragment(int min, int max) {
        secondFragment = SecondFragment.newInstance(min, max);
        makeFragmentTransaction(REPLACE_ON_SECOND_FRAGMENT);
        setPreviousValuePassInterface();
    }

    private void makeFragmentTransaction(String action) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (action) {
            case ADD_FIRST_FRAGMENT:
                transaction.add(R.id.container, firstFragment);
                break;
            case REPLACE_ON_SECOND_FRAGMENT:
                transaction.replace(R.id.container, secondFragment);
                break;
            case BACK_TO_FIRST_FRAGMENT:
                transaction.replace(R.id.container, firstFragment);
        }
        transaction.commit();
    }

    private void setMinMaxValuesPassInterface() {
        firstFragment.setMinMaxValuesPassInterface((min, max) -> {
            if (Objects.equals(min, "") && !Objects.equals(max, "")) {
                showToast("Минимальное значение не указано!");
            } else if (!Objects.equals(min, "") && Objects.equals(max, "")) {
                showToast("Maксимальное значение не указано!");
            } else if (Objects.equals(min, "") && Objects.equals(max, "")) {
                showToast("Минимальное и максимальное значение не указаны!");
            } else if (!Objects.equals(min, "") && !Objects.equals(max, "")) {
                int minValue = Integer.parseInt(min);
                int maxValue = Integer.parseInt(max);
                if (minValue > maxValue) {
                    showToast("Минимальное значение превышает максимальное!");
                } else {
                    openSecondFragment(minValue, maxValue);
                }
            }
        });
    }

    private void setPreviousValuePassInterface() {
        secondFragment.setPreviousValuePassInterface((previous) -> {
            previousValue = Integer.parseInt(previous);
            openFirstFragment(previousValue, BACK_TO_FIRST_FRAGMENT);
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
