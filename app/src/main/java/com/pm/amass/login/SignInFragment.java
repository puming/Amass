package com.pm.amass.login;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.basics.base.BaseFragment;
import com.pm.amass.MainActivity;
import com.pm.amass.R;
import com.pm.amass.bean.Token;

/**
 * @author pmcho
 */
public class SignInFragment extends BaseFragment {
    private static final String TAG = "SignInFragment";

    private SignInViewModel mViewModel;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_in_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_sign_up).setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_signIn_to_signUp)
        );

        RadioGroup mRadioGroup = view.findViewById(R.id.rg);
        ViewGroup mViewGroupSignIn = view.findViewById(R.id.sign_in_form);
        ViewGroup mViewGroupSignUp = view.findViewById(R.id.sign_up_form);
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbtn_sign_in) {
                mViewGroupSignIn.setVisibility(View.VISIBLE);
                mViewGroupSignUp.setVisibility(View.GONE);
            } else if (checkedId == R.id.rbtn_sign_up) {
                mViewGroupSignIn.setVisibility(View.GONE);
                mViewGroupSignUp.setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.btn_sign_in).setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MainActivity.class));
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        subscribeViewModel();
    }

    private void subscribeViewModel() {
        mViewModel.getToken().observe(this, tokenResource -> {
            switch (tokenResource.status) {
                case SUCCEED:
                    Token token = tokenResource.data;
                    String tokenString = token.getData().getToken().getInfo();
                    Log.d(TAG, "subscribeViewModel: tokenString" + tokenString);
                    break;
                default:
                    break;
            }
        });
    }

}
