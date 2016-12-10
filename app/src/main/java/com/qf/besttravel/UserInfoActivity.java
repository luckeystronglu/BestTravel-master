package com.qf.besttravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.qf.contacts.Contact;
import com.qf.entity.UserInfoEntity;
import com.qf.fragment.MineFragment;
import com.qf.fragment.UserNoteFragment;
import com.qf.fragment.UserNoteFragment2;
import com.qf.widget.GlideCircleTransform;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/11/16.
 */
public class UserInfoActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, DownUtil.OnDownListener {
    @Bind(R.id.user_circle_img)
    ImageView userCircleImg;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.user_focus_num)
    TextView userFocusNum;
    @Bind(R.id.user_fans_num)
    TextView userFansNum;
    @Bind(R.id.user_backg_img)
    ImageView userBackgImg;

    private ViewPager vp;
    private TabLayout tabLayout;
    //    private int [] img_title = {R.drawable.tab_user_trip_grid_highlight,R.drawable.tab_user_trip_list_highlight,R.drawable.tab_user_trip_text_highlight};
    private int[] img_cover = {R.drawable.selector_1, R.drawable.selector_2, R.drawable.selector_3};
    private int useimgid;

    @Override
    public int getContentViewId() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void init() {
        Intent in = getIntent();
        useimgid = in.getIntExtra("useimgid", -1);

        vp = findViewByIds(R.id.user_viewpager);
        tabLayout = findViewByIds(R.id.user_tablayout);
        initViewPager(vp);
        tabLayout.setupWithViewPager(vp);
        vp.setOffscreenPageLimit(2);
        tabLayout.setOnTabSelectedListener(this);
        tabLayout.getTabAt(0).setIcon(img_cover[0]);
        tabLayout.getTabAt(1).setIcon(img_cover[1]);
        tabLayout.getTabAt(2).setIcon(img_cover[2]);
        tabLayout.getTabAt(0).select();

        if (useimgid != -1) {
            String url = String.format(Contact.USER_INFO, useimgid);
            new DownUtil().setOnDownListener(this).downJSON(url);
        }
    }

    private void initViewPager(ViewPager vp) {
        TravelPagerAdapter adapter = new TravelPagerAdapter(getSupportFragmentManager());
        UserNoteFragment fragment = new UserNoteFragment();
        adapter.addFragment(fragment);
        fragment.setuserid(useimgid);

        UserNoteFragment2 userNoteFragment2 = new UserNoteFragment2();
        adapter.addFragment(userNoteFragment2);
        userNoteFragment2.setuserid(useimgid);

        adapter.addFragment(new MineFragment());
        vp.setAdapter(adapter);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tab.setIcon(img_cover[tab.getPosition()]);
        vp.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.setIcon(img_cover[tab.getPosition()]);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, UserInfoEntity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        UserInfoEntity userInfoEntity = (UserInfoEntity) object;
//        new DownloadImageTask().execute(userInfoEntity.getData().getHeader_photo().getPhoto_url());
       Glide.with(this)
               .load(userInfoEntity.getData().getHeader_photo().getPhoto_url())
               .diskCacheStrategy(DiskCacheStrategy.ALL)
               .thumbnail(0.1f)
               .crossFade(500)
               .into(userBackgImg);

        Glide.with(this)
                .load(userInfoEntity.getData().getPhoto_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(this))
//                .placeholder(R.drawable.image_app)
                .thumbnail(0.1f)
                .crossFade(500)
                .into(userCircleImg);
        userName.setText(userInfoEntity.getData().getName());
        userFocusNum.setText(userInfoEntity.getData().getFollowings_count() + "");
        userFansNum.setText(userInfoEntity.getData().getFollowers_count() + "");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.user_info_back_logo)
    public void onClick() {
        finish();
    }


    public class TravelPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mfragment;//一个fragment的集合

        public TravelPagerAdapter(FragmentManager fm) {
            super(fm);
            mfragment = new ArrayList<>();

        }

        public void addFragment(Fragment fragment) {
            mfragment.add(fragment);

        }


        @Override
        public Fragment getItem(int position) {
            return mfragment.get(position);
        }

        @Override
        public int getCount() {
            return mfragment.size();
        }
    }

//    private class DownloadImageTask extends AsyncTask<String, Void, Drawable> {
//        protected Drawable doInBackground(String... urls) {
//            return loadImageFromNet(urls[0]);
//        }
//
//        protected void onPostExecute(Drawable result) {
//            re02.setBackgroundDrawable(result);
//        }
//    }

//    private Drawable loadImageFromNet(String url) {
//        Drawable drawable = null;
//        try {
//            // 可以在这里通过第二个参数(文件名)来判断，是否本地有此图片
//            drawable = Drawable.createFromStream(new URL(url).openStream(), null);
//        } catch (IOException e) {
//            Log.d("skythinking", e.getMessage());
//        }
//        if (drawable == null) {
//            Log.d("skythinking", "null drawable");
//        } else {
//            Log.d("skythinking", "not null drawable");
//        }
//
//        return drawable;
//    }

}
