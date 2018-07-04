package kr.hs.dimigo.meal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.hs.dimigo.meal.util.DateGenerator;
import kr.hs.dimigo.meal.R;
import kr.hs.dimigo.meal.communication.MealPojo;
import kr.hs.dimigo.meal.communication.ParseApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YesterdayMealViewFragment extends Fragment{

    TextView textBreakfast;
    TextView textLunch;
    TextView textDinner;
    TextView textSnack;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textBreakfast = getActivity().findViewById(R.id.textBreakfast);
        textLunch = getActivity().findViewById(R.id.textLunch);
        textDinner = getActivity().findViewById(R.id.textDinner);
        textSnack = getActivity().findViewById(R.id.textSnack);

        DateGenerator dateGenerator = new DateGenerator();

        ParseApi.apiService.getMealInfo(dateGenerator.getYesterday()).enqueue(new Callback<MealPojo>() {
            @Override
            public void onResponse(Call<MealPojo> call, Response<MealPojo> response) {
                if(response.body() != null) {
                    textBreakfast.setText(response.body().getBreakfast());
                    textLunch.setText(response.body().getLunch());
                    textDinner.setText(response.body().getDinner());
                    textSnack.setText(response.body().getSnack());
                }
            }

            @Override
            public void onFailure(Call<MealPojo> call, Throwable t) {

            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yesterday_meal_view_fragment, container, false);
    }
}