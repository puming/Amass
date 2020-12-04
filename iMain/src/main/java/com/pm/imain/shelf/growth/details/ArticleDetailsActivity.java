package com.pm.imain.shelf.growth.details;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.basics.base.AppBarActivity;
import com.common.widget.AppBar;
import com.google.android.flexbox.FlexboxLayout;
import com.pm.imain.R2;
import com.pm.imain.bean.ArticleDetailsResult;
import com.pm.imain.diglog.BottomEditDialog;
import com.pm.imain.shelf.growth.GrowthViewModel;
import com.pm.imain.shelf.growth.comment.CommentActivity;

/**
 * @author pmcho
 * 文章
 */
public class ArticleDetailsActivity extends AppBarActivity implements View.OnClickListener {
    private static final String TAG = "ArticleDetailsActivity";

    private GrowthViewModel mViewModel;
    /**
     * 说点什么吧
     */
    private Button mEtComment;
    private ImageView mIvBtnComment;
    private ImageView mIvBtnCollect;
    private ImageView mIvBtnShare;
    private FlexboxLayout mFlexArticleDetails;
    /**
     * 展开全文
     */
    private Button mBtnOpenMore;

    private boolean select = false;
    private WebView mWebArticleDetails;
    private String mArticleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R2.layout.activity_article_details);
        initView();
        mViewModel = ViewModelProviders.of(this).get(GrowthViewModel.class);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mArticleId = intent.getStringExtra("articleId");
        if (TextUtils.isEmpty(mArticleId)) {
            return;
        }
        mViewModel.getArticleDetails(mArticleId)
                .observe(this, articleDetailsResource -> {
                    switch (articleDetailsResource.status) {
                        case LOADING:
                            showDialog();
                            break;
                        case SUCCEED:
                            hideDialog();
                            ArticleDetailsResult data = articleDetailsResource.data;
                            ArticleDetailsResult.ArticleDetails.Details detail = data.getData().getDetail();
                            String url = detail.getH5_url();
//                            mWebArticleDetails.loadUrl("http://www.pmbloger.com/");
                            mWebArticleDetails.loadUrl("url");
                            break;
                        case ERROR:
                            hideDialog();
                            mWebArticleDetails.loadUrl("http://www.pmbloger.com/");
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
                .setAppbarMenuIcon(R2.drawable.ic_more_vector);
    }


    private void initView() {
        mEtComment = (Button) findViewById(R2.id.btn_et_comment);
        mEtComment.setOnClickListener(this);
        mIvBtnComment = (ImageView) findViewById(R2.id.iv_btn_comment);
        mIvBtnComment.setOnClickListener(this);
        mIvBtnCollect = (ImageView) findViewById(R2.id.iv_btn_collect);
        mIvBtnCollect.setOnClickListener(this);
        mIvBtnShare = (ImageView) findViewById(R2.id.iv_btn_share);
        mIvBtnShare.setOnClickListener(this);
        mFlexArticleDetails = (FlexboxLayout) findViewById(R2.id.flex_article_details);
        mFlexArticleDetails.setOnClickListener(this);
        mBtnOpenMore = (Button) findViewById(R2.id.btn_open_more);
        mBtnOpenMore.setOnClickListener(this);
        mWebArticleDetails = (WebView) findViewById(R2.id.web_article_details);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R2.id.btn_et_comment:
                BottomEditDialog editDialog = BottomEditDialog.newInstance();
                editDialog.showDialogFragment(this, editDialog, String.valueOf(editDialog.hashCode()));
                editDialog.setOnOnItemClickListener((content) -> {
                            Log.d(TAG, "onClick: content=" + content);
                            mViewModel.getCommentData(mArticleId, content)
                                    .observe(this, resultResource -> {
                                        switch (resultResource.status) {
                                            case SUCCEED:
                                                break;
                                            case ERROR:
                                                break;
                                            default:
                                                break;
                                        }
                                    });
                        }
                );
                break;
            case R2.id.iv_btn_comment:
                startActivity(new Intent(this, CommentActivity.class));
                break;
            case R2.id.iv_btn_collect:
                mViewModel.getActionData(mArticleId, "collect")
                        .observe(this, resultResource -> {
                            switch (resultResource.status) {
                                case SUCCEED:
                                    select = !select;
                                    mIvBtnCollect.setSelected(select);
                                    break;
                                case ERROR:
                                    break;
                                default:
                                    break;
                            }
                        });
                break;
            case R2.id.iv_btn_share:
                break;
            case R2.id.btn_open_more:
                break;
            case R2.id.flex_article_details:
                break;
        }
    }
}
