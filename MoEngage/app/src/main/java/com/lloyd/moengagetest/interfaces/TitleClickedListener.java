package com.lloyd.moengagetest.interfaces;

import com.lloyd.moengagetest.models.ArticleItemModel;

/**
 * This interface is used to provide callback when title is clicked inorder to display article in browser.
 */
public interface TitleClickedListener {

    void onTitleClicked(int position, ArticleItemModel model);
}
