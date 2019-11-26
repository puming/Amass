package com.pm.amass.ui.growth.content;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.basics.base.BaseAdapter;
import com.basics.base.BaseViewHolder;
import com.google.android.flexbox.FlexboxLayout;
import com.pm.amass.R;
import com.pm.amass.bean.ArticleResult.Article;
import com.pm.amass.bean.Moudle;
import com.pm.amass.ui.growth.details.ArticleDetailsActivity;
import com.pm.amass.utils.ImageLoaderUtils;
import com.pm.amass.utils.StringUtil;

import java.util.List;

/**
 * @author pmcho
 */
public class ContentListAdapter extends BaseAdapter<Article, ContentListAdapter.ListViewHolder> {
    public ContentListAdapter(Context context, List<Article> datas) {
        super(context, datas);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListViewHolder holder = new ListViewHolder(parent, R.layout.item_content_artcle);
//        ListViewHolder holder = new ListViewHolder(parent, R.layout.item_article_html);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Article article = mDatas.get(position);
        TextView textView = holder.getItemView(R.id.tv_article_title);
        TextView label = holder.getItemView(R.id.tv_article_label);
        FlexboxLayout box = holder.getItemView(R.id.fl_article_image_group);
        String images = article.getImages();

        String[] urlArray = StringUtil.generateSubString(images);
        for (int i = 0; i < box.getChildCount(); i++) {
            View child = box.getChildAt(i);
            if (child instanceof ImageView) {
                ImageView imageView = (ImageView) child;
                String url = "";
                if (i < urlArray.length) {
                    url = urlArray[i];
                } else {
                    url = urlArray[0];
                }
                ImageLoaderUtils.bindImage(imageView, url, new Size(106, 72));
            }
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(content, 0));
        } else {
            textView.setText(Html.fromHtml(content));
        }*/
        textView.setText(article.getTitle());
        label.setText("阅读" + article.getRead_num() + "评论" + article.getComment_num());
        int articleId = article.getId();
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ArticleDetailsActivity.class);
            intent.putExtra("articleId", String.valueOf(articleId));
            mContext.startActivity(intent);
        });
    }

    static class ListViewHolder extends BaseViewHolder<Article> {
        public ListViewHolder(View itemView) {
            super(itemView);
        }

        public ListViewHolder(ViewGroup parent, int res) {
            super(parent, res);
        }
    }
}
