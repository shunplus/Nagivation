package com.shunplus.navigation.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import com.shunplus.navigation.R;
import com.shunplus.navigation.base.BaseToolBarFragment;

public class HomeFragment extends BaseToolBarFragment {

    private HomeViewModel homeViewModel;

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//
//        homeViewModel=new HomeViewModel();
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.action_to_test);
//            }
//        });
//
//
//        return root;
//    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        homeViewModel=new HomeViewModel();
        final TextView textView = rootView.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_to_test);
            }
        });

        setTitleText("home2");
    }


    @Override
    public void onResume() {
        super.onResume();
//        EventBus.getDefault().post(new MsgEvent(0));
    }
}
