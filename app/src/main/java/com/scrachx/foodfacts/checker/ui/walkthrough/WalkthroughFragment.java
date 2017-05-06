package com.scrachx.foodfacts.checker.ui.walkthrough;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scrachx.foodfacts.checker.R;

/**
 * Created by scots on 06/05/2017.
 */
@SuppressLint("ValidFragment")
public class WalkthroughFragment extends Fragment {

    int mWizardPagePosition;

    public WalkthroughFragment(int position) {
        this.mWizardPagePosition = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layout_id = R.layout.fragment_walkthrough;

        View view = inflater.inflate(layout_id, container, false);
        String url = "https://s3-us-west-2.amazonaws.com/material-ui-template/walkthrough/style-15/Welcome-15.png";
        ImageView img = (ImageView) view.findViewById(R.id.image_page);
        loadImageRequest(img, url);

        return view;
    }

    private void loadImageRequest(ImageView img, String url) {
        Glide.with(getActivity())
                .load(url)
                .into(img);
    }

}
