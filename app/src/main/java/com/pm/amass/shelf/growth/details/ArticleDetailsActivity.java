package com.pm.amass.shelf.growth.details;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.ViewModelProviders;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.google.android.flexbox.FlexboxLayout;
import com.pm.amass.R;
import com.pm.amass.shelf.growth.GrowthViewModel;
import com.pm.amass.shelf.growth.comment.CommentActivity;

/**
 * @author pmcho
 * 文章
 */
public class ArticleDetailsActivity extends AppBarActivity implements View.OnClickListener {

    private GrowthViewModel mViewModel;
    /**
     * 说点什么吧
     */
    private AppCompatEditText mEtComment;
    private ImageView mIvBtnComment;
    private ImageView mIvBtnCollect;
    private ImageView mIvBtnShare;
    private FlexboxLayout mFlexArticleDetails;
    /**
     * 展开全文
     */
    private Button mBtnOpenMore;

    private boolean select = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        initView();
        mViewModel = ViewModelProviders.of(this).get(GrowthViewModel.class);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String articleId = intent.getStringExtra("articleId");
        if (TextUtils.isEmpty(articleId)) {
            return;
        }
        mViewModel.getArticleDetails(articleId)
                .observe(this, articleDetailsResource -> {
                    switch (articleDetailsResource.status) {
                        case LOADING:
                            showDialog();
                            break;
                        case SUCCEED:
                            hideDialog();
                            break;
                        case ERROR:
                            hideDialog();
                            break;
                        default:
                            break;
                    }
                });
    }

    @Override
    protected void initAppBar(AppBar appBar) {
        appBar.showAppbarRightContainer(true)
                .showAppbarTitle(false)
                .showAppbarMenuIcon(true)
                .showAppbarBackText(false)
                .setAppbarMenuIcon(R.drawable.ic_more_vector);
    }


    private void initView() {
        mEtComment = (AppCompatEditText) findViewById(R.id.et_comment);
        mEtComment.setOnClickListener(this);
        mIvBtnComment = (ImageView) findViewById(R.id.iv_btn_comment);
        mIvBtnComment.setOnClickListener(this);
        mIvBtnCollect = (ImageView) findViewById(R.id.iv_btn_collect);
        mIvBtnCollect.setOnClickListener(this);
        mIvBtnShare = (ImageView) findViewById(R.id.iv_btn_share);
        mIvBtnShare.setOnClickListener(this);
        mFlexArticleDetails = (FlexboxLayout) findViewById(R.id.flex_article_details);
        mFlexArticleDetails.setOnClickListener(this);
        mBtnOpenMore = (Button) findViewById(R.id.btn_open_more);
        mBtnOpenMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.et_comment:
                break;
            case R.id.iv_btn_comment:
                startActivity(new Intent(this, CommentActivity.class));
                break;
            case R.id.iv_btn_collect:
                select = !select;
                mIvBtnCollect.setSelected(select);
                break;
            case R.id.iv_btn_share:
                break;
            case R.id.btn_open_more:
                break;
        }
    }
}
