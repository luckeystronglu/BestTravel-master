package com.qf.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.qf.besttravel.R;
import com.qf.entity.MeetWorldEntity;
import com.qf.widget.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/30.
 */
public class MeetWorldAdapter extends RecyclerView.Adapter<MeetWorldAdapter.MeetHolder> {

    private Context context;
    private List<MeetWorldEntity.DataBean.ItemsBean> datas;

    public final SparseBooleanArray mCollapsedStatus;
    private Intent intent;
    private Intent textintent;

    public MeetWorldAdapter(Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
//        this.dataP = new ArrayList<>();
        this.mCollapsedStatus = new SparseBooleanArray();
    }

    public void setDatas(List<MeetWorldEntity.DataBean.ItemsBean> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    public void addDatas(List<MeetWorldEntity.DataBean.ItemsBean> data) {
        this.datas.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public MeetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_meetworld, parent, false);
        return new MeetHolder(view);
    }

    @Override
    public void onBindViewHolder(final MeetHolder holder, final int position) {
        /*

        TextView frame_morebtn;
        ImageView userimg, bigimg;


        LinearLayout ll_img;
        LinearLayout ll_marks;

        ExpandableTextView expandableTextView;//可扩展收缩的textview*/
        //设置数据
        holder.tv_user_name.setText(datas.get(position).getUser_activity().getUser().getName());
        holder.con_title.setText(datas.get(position).getTitle());
        holder.con_summary.setText(datas.get(position).getDescription());
        holder.tv_note_title.setText(datas.get(position).getUser_activity().getDescription());

        holder.expandableTextView.setText(datas.get(position).getUser_activity().getDescription(), mCollapsedStatus, position);
        holder.expandableTextView.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(final TextView textView, boolean isExpanded) {
                if (isExpanded) {

                    holder.frame_morebtn.setText("点击收缩文章");

                } else {
                    holder.frame_morebtn.setText("点击展开全文");

                }
            }
        });


//        holder.tv_note_summary.setText(datas.get(position).getActivity().getDescription());
//        holder.morebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean collapsed = holder.tv_note_summary.isCollapsed;
//                if (collapsed) {//如果折叠了,就展开
//                    holder.tv_note_summary.collapse();
//                    holder.morebtn.setText("收起文章");
//                } else {//如果没折叠,就折叠
//                    holder.tv_note_summary.collapse();
//                    holder.morebtn.setText("查看全文");
//                }
//            }
//        });
        holder.tv_likesnum.setText(datas.get(position).getUser_activity().getLikes_count() + "");
        holder.tv_commentnum.setText(datas.get(position).getUser_activity().getComments_count() + "");
        holder.tv_favoritenum.setText(datas.get(position).getUser_activity().getFavorites_count() + "");

        //添加标签
        holder.ll_marks.removeAllViews();//移除线性布局中的所有控件
        List<MeetWorldEntity.DataBean.ItemsBean.UserActivityBean.DistrictsBean> markslist = datas.get(position).getUser_activity().getDistricts();
        if (markslist.size() != 0) {
            for (int i = 0; i < markslist.size(); i++) {
                LinearLayout.LayoutParams llmarkparams = new LinearLayout
                        .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                llmarkparams.setMargins(10, 10, 2, 10);
                TextView tv = new TextView(context);
                tv.setText(markslist.get(i).getName());
                tv.setBackgroundColor(Color.parseColor("#dedada"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv.setTextColor(Color.GRAY);
                tv.setPadding(12, 4, 12, 4);
                tv.setBackgroundResource(R.drawable.tv_note_marks_shape);
                holder.ll_marks.addView(tv, llmarkparams);
            }
        }

        if (datas.get(position).getUser_activity().getPoi() != null && !datas.get(position).getUser_activity().getPoi().equals("null")) {
            if (datas.get(position).getUser_activity().getPoi() != null) {
                LinearLayout.LayoutParams llmarkparams2 = new LinearLayout
                        .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                llmarkparams2.setMargins(10, 10, 2, 10);
                TextView tv = new TextView(context);
                tv.setText(datas.get(position).getUser_activity().getPoi().getName());
                tv.setBackgroundColor(Color.parseColor("#dedada"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tv.setTextColor(Color.GRAY);
                tv.setPadding(12, 4, 12, 4);
                tv.setBackgroundResource(R.drawable.tv_note_marks_shape);
                holder.ll_marks.addView(tv, llmarkparams2);
            }

        }



        if (datas.get(position).getUser_activity().getMade_at() != null) {
            String made_at = datas.get(position).getUser_activity().getMade_at();
            String[] strings = made_at.split("-");
            String month = strings[1];
            LinearLayout.LayoutParams llmarkparams4 = new LinearLayout
                    .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            llmarkparams4.setMargins(10, 10, 2, 10);
            TextView tv = new TextView(context);
            tv.setBackgroundColor(Color.parseColor("#dedada"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            tv.setTextColor(Color.GRAY);
            tv.setPadding(12, 4, 12, 4);
            switch (month) {
                case "01":
                    tv.setText("一月");

                    break;
                case "02":
                    tv.setText("二月");

                    break;
                case "03":
                    tv.setText("三月");

                    break;
                case "04":
                    tv.setText("四月");

                    break;
                case "05":
                    tv.setText("五月");

                    break;
                case "06":
                    tv.setText("六月");

                    break;
                case "07":
                    tv.setText("七月");

                    break;
                case "08":
                    tv.setText("八月");

                    break;
                case "09":
                    tv.setText("九月");

                    break;
                case "10":
                    tv.setText("十月");

                    break;
                case "11":
                    tv.setText("十一月");

                    break;
                case "12":
                    tv.setText("十二月");

                    break;
                default:
                    tv.setText("");

                    break;

            }
            tv.setBackgroundResource(R.drawable.tv_note_marks_shape);
            holder.ll_marks.addView(tv, llmarkparams4);
        }

        //Glide下载列表中大视图图片
        Glide.with(context)
                .load(datas.get(position).getUser_activity().getContents().get(0).getPhoto_url())
                .crossFade(500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.avtar_placeholder_big)
                .thumbnail(0.1f)
                .into(holder.bigimg);

        List<MeetWorldEntity.DataBean.ItemsBean.UserActivityBean.ContentsBean> imgs = datas.get(position).getUser_activity().getContents();
//        intent = new Intent(context,LookPicsActivity.class);

       /* holder.bigimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("picnum",0);
                intent.putExtra("entity",datas.get(position));
                context.startActivity(intent);
            }
        });*/


        //添加scrollview图片
        holder.ll_img.removeAllViews();
        if (imgs.size() != 0) {

            for (int i = 1; i < imgs.size(); i++) {
                LinearLayout.LayoutParams llimgparams = new LinearLayout.LayoutParams(300, 220);
                llimgparams.rightMargin = 4;
                llimgparams.leftMargin = 6;
                ImageView img = new ImageView(context);
                img.setScaleType(ImageView.ScaleType.FIT_XY);

                Glide.with(context)
                        .load(imgs.get(i).getPhoto_url())
                        .crossFade(500)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .thumbnail(0.1f)
                        .into(img);

                img.setPadding(0, 10, 0, 0);
                img.setTag(i);

                img.setClickable(true);
                holder.ll_img.addView(img, llimgparams);


               /* img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int picnum = (int) v.getTag();

                        intent.putExtra("picnum", picnum);
                        intent.putExtra("entity", datas.get(position));
                        context.startActivity(intent);
                    }
                });*/
            }


        }


        //Glide下载用户头像
        Glide.with(context)
                .load(datas.get(position).getUser_activity().getUser().getPhoto_url())
                .crossFade(500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.home_user_place_holder)
                .transform(new GlideCircleTransform(context))
                .thumbnail(0.1f)
                .into(holder.userimg);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class MeetHolder extends RecyclerView.ViewHolder {
        TextView con_title,con_summary;
        TextView frame_morebtn;
        ImageView userimg, bigimg;
        TextView tv_user_name, tv_note_title;
        TextView tv_likesnum, tv_commentnum, tv_favoritenum;
        LinearLayout ll_img;
        LinearLayout ll_marks;

        ExpandableTextView expandableTextView;//可扩展收缩的textview

        public MeetHolder(View itemView) {
            super(itemView);
            this.con_title = (TextView) itemView.findViewById(R.id.meet_con_title);
            this.con_summary = (TextView) itemView.findViewById(R.id.meet_con_summary);
            this.userimg = (ImageView) itemView.findViewById(R.id.worldnote_user_img);
            this.bigimg = (ImageView) itemView.findViewById(R.id.travel_big_img);
            this.tv_user_name = (TextView) itemView.findViewById(R.id.worldnote_user_tv);
            this.tv_likesnum = (TextView) itemView.findViewById(R.id.likescount);
            this.tv_commentnum = (TextView) itemView.findViewById(R.id.commentcount);
            this.tv_favoritenum = (TextView) itemView.findViewById(R.id.favCount);
            this.ll_marks = (LinearLayout) itemView.findViewById(R.id.ll_travel_mark);
            this.ll_img = (LinearLayout) itemView.findViewById(R.id.ll_img);

            this.tv_note_title = (TextView) itemView.findViewById(R.id.pic_meet_title);
            this.expandableTextView = (ExpandableTextView) itemView.findViewById(R.id.expand_text_view);
            this.frame_morebtn = (TextView) itemView.findViewById(R.id.frame_morebtn);

        }
    }

}
