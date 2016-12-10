package com.qf.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.qf.adapter.UserNoteAdapter1;
import com.qf.besttravel.R;
import com.qf.contacts.Contact;
import com.qf.entity.UserNoteEntity;
import com.qfkf.base.BaseFragment;
import com.qfkf.util.DownUtil;

import java.util.List;

/**
 * Created by lenovo on 2016/11/17.
 */
public class UserNoteFragment extends BaseFragment implements DownUtil.OnDownListener {
    private RecyclerView user_note_rv1;
    private UserNoteAdapter1 adapter;
    private UserNoteEntity userNoteEntity;
    private List<UserNoteEntity.DataBean> dataBeanList;

    private int useimgid;

    public UserNoteFragment() {
        super();
    }

    public void setuserid(int id){
        this.useimgid = id;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_usernote1;
    }

    @Override
    protected void init(View view) {
        //解决recyclerView滑动卡顿(前3行)
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);



        user_note_rv1 = (RecyclerView) view.findViewById(R.id.usernote_rv1);
//        user_note_rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        //解决recyclerView滑动卡顿(后3行)
        user_note_rv1.setLayoutManager(layoutManager);
        user_note_rv1.setHasFixedSize(true);
        user_note_rv1.setNestedScrollingEnabled(false);

        adapter = new UserNoteAdapter1(getActivity());
        user_note_rv1.setAdapter(adapter);

    }


    @Override
    protected void loadDatas() {
        String user_rv_url = String.format(Contact.USER_NOTE1,useimgid);
        new DownUtil().setOnDownListener(this).downJSON(user_rv_url);
        Log.d("print", "user_rv_url2: "+user_rv_url);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json,UserNoteEntity.class);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            userNoteEntity = (UserNoteEntity) object;
            dataBeanList = userNoteEntity.getData();
            adapter.setDatas(dataBeanList);
        }
    }


}
