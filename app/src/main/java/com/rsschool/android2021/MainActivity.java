package com.rsschool.android2021;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String ADD_FIRST_FRAGMENT = "add_first_fragment";
    private static final String REPLACE_ON_SECOND_FRAGMENT = "replace_on_second_fragment";
    private static final String BACK_TO_FIRST_FRAGMENT = "back_to_first_fragment";
    private static final String TAG_SECOND_FRAGMENT = "tag_second_fragment";

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;

    private int randomValue = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(randomValue, ADD_FIRST_FRAGMENT);
    }

    @Override
    public void onBackPressed() {
        if (getSecondFragmentInContainerNow()!= null) {
            secondFragment.passRandomValue();
        } else {
            super.onBackPressed();
        }
    }

    private Fragment getSecondFragmentInContainerNow() {
        return getSupportFragmentManager().findFragmentByTag(TAG_SECOND_FRAGMENT);
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
                transaction.replace(R.id.container, secondFragment, TAG_SECOND_FRAGMENT);
                transaction.addToBackStack(null);
                break;
            case BACK_TO_FIRST_FRAGMENT:
                transaction.replace(R.id.container, firstFragment);
                getSupportFragmentManager().popBackStack();
                break;
        }
        transaction.commit();
    }

    private void setMinMaxValuesPassInterface() {
        firstFragment.setMinMaxValuesPassInterface((min, max) -> {
            if (Objects.equals(min, "") && Objects.equals(max, "")) {
                showToast("Min and Max values is empty");
            } else if (Objects.equals(min, "") && !Objects.equals(max, "")) {
                showToast("Min value is empty");
            } else if (!Objects.equals(min, "") && Objects.equals(max, "")) {
                showToast("Max value is empty");
            } else if (!Objects.equals(min, "") && !Objects.equals(max, "")) {
                formValuesFromInput(min, max);
            }
        });
    }

    private void formValuesFromInput(String min, String max) {
        try {
            int minValue = Integer.parseInt(min);
            int maxValue = Integer.parseInt(max);
            if (minValue > maxValue) {
                showToast("Min value is greater than Max");
            } else {
                openSecondFragment(minValue, maxValue);
            }
        } catch (NumberFormatException e) {
            showToast("One of the values is too large");
        }
    }

    private void setPreviousValuePassInterface() {
        secondFragment.setRandomValuePassInterface((previous) -> {
            randomValue = Integer.parseInt(previous);
            openFirstFragment(randomValue, BACK_TO_FIRST_FRAGMENT);
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
