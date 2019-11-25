package com.pm.amass.ui.growth.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.pm.amass.R;

/**
 * @author pmcho
 * 文章
 */
public class ArticleDetailsActivity extends AppBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        appBar.showAppbarRightContainer(true)
                .showAppbarTitle(false)
                .showAppbarMenuIcon(true)
                .showAppbarBackText(false)
                .setAppbarMenuIcon(R.drawable.ic_more_vector);
    }
}
