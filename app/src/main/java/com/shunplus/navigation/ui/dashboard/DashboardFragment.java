package com.shunplus.navigation.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.shunplus.navigation.MsgEvent;
import com.shunplus.navigation.R;
import com.shunplus.navigation.utils.LiveDataBusX;

import org.greenrobot.eventbus.EventBus;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.ac_activity_test);
                LiveDataBusX.get().with("data", MsgEvent.class).setValue(new MsgEvent(3));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            for (int i = 0; i < 10; i++) {
                                Thread.sleep(2000);
                                LiveDataBusX.get().with("data", MsgEvent.class).postValue(new MsgEvent(2));
                            }


                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().post(new MsgEvent(1));
    }
}
