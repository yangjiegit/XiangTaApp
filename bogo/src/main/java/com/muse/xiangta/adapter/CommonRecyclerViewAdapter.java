package com.muse.xiangta.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.muse.xiangta.R;
import com.muse.xiangta.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommonRecyclerViewHolder> {
    public static final int TYPE_HEADER = 1000000001;
    public static final int TYPE_NORMAL = 1000000002;
    private static final int TYPE_FOOTER = 1000000003;
    //是否可加载更多
    private boolean canloadMore = true;
    //是否正在加载数据
    private boolean isLoading = false;

    /**
     * 条目的点击事件的监听接口
     */
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    /**
     * 条目里面的控件的点击事件的监听接口
     */
    private OnViewInItemClickListener onViewInItemClickListener;

    /**
     * item中的需要监听的view的id
     */
    private int[] viewIdsInItem;

    /**
     *
     */
    private int[] itemTypesForItem;

    /**
     *
     */
    private int[] itemTypesForViewInItem;

    /**
     * 上下文对象
     */
    protected Context context;

    /**
     * 要显示的数据
     */
    protected List<T> mDatas;


    /**
     * 记录当前的position
     */
    private int mCurrentPosition = -1;

    /**
     * 头部的试图
     */
    private ArrayList<View> headers = new ArrayList<View>();
    /**
     * 是否带有上拉加载更多的功能
     */
    private boolean loadmore;
    /**
     * 外部设置加载更多开关
     */
    private boolean setloadmore;

    /**
     * 加载更多监听
     */
    private OnLoadMoreListener onLoadMoreListener;

    /**
     * 添加头部的试图,默认不通知适配器插入了一个条目
     *
     * @param view
     */
    public void addHeaderView(View view) {
        addHeaderView(view, false);
    }

    /**
     * 添加头部的试图,默认不通知适配器插入了一个条目
     *
     * @param setloadMore
     */
    public void setLoadMore(boolean setloadMore) {
        this.setloadmore = setloadMore;
    }

    /**
     * 添加头部的试图
     *
     * @param view
     * @param isNotify 是否通知插入一个条目
     */
    public void addHeaderView(View view, boolean isNotify) {
        if (view == null) {
            throw new NullPointerException("the header view can not be null");
        }
        headers.add(view);
        if (isNotify) {
            notifyItemInserted(headers.size() - 1);
        }
    }

    /**
     * 删除一个试图,默认不通知删除一个条目
     *
     * @param position
     */
    public void removeHeaderView(int position) {
        removeHeaderView(position, false);
    }

    /**
     * 删除一个试图
     *
     * @param position
     */
    public void removeHeaderView(int position, boolean isNotify) {
        headers.remove(position);
        if (isNotify) {
            notifyItemRemoved(position);
        }
    }

    /**
     * 获取头部试图的个数
     *
     * @return
     */
    public int getHeaderCounts() {
        return headers.size();
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param data    显示的数据
     */
    public CommonRecyclerViewAdapter(Context context, List<T> data) {
        this.mDatas = data;
        this.context = context;
    }

    /**
     * viewType 是通过{@link RecyclerView.Adapter#getItemViewType(int)}获取到的
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == TYPE_HEADER) {
            //头部
            view = headers.get(mCurrentPosition);
        } else if (viewType == TYPE_FOOTER) {
            //尾部
            view = LayoutInflater.from(context).inflate(R.layout.layout_footerview, parent, false);
        } else {
            view = View.inflate(context, getLayoutViewId(viewType), null);
        }
        CommonRecyclerViewHolder vh = new CommonRecyclerViewHolder(view);
        //试图被创建的时候调用
        viewCreated(vh, viewType);
        return vh;
    }

    /**
     * 获取条目的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        mCurrentPosition = position;
        int size = headers.size();
        if (position < size) return TYPE_HEADER;
        if (loadmore && position == getItemCount() - 1) return TYPE_FOOTER;
        return getItemType(position - size);
    }

    /**
     * 获取条目的类型
     *
     * @return
     */
    public int getItemType(int position) {
        return 0;
    }


    /**
     * 获取某个位置的数据
     *
     * @return
     */
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public void onBindViewHolder(CommonRecyclerViewHolder holder, int position) {
        int size = headers.size();
        if (getItemViewType(position) == TYPE_HEADER) {
            //如果是头部,不处理
        } else if (getItemViewType(position) == TYPE_FOOTER) {
            //尾部不处理
            ProgressBar progressBar = holder.itemView.findViewById(R.id.progressBar);
            TextView tv_footerview = holder.itemView.findViewById(R.id.tv_footview);
            if (onLoadMoreListener != null && !isLoading && canloadMore) {
                //加载更多
                progressBar.setVisibility(View.VISIBLE);
                tv_footerview.setText("正在加载数据...");
                UIUtils.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //正在加载中 每次刷新一条数据
                        isLoading = true;
                        onLoadMoreListener.onloadmore();
                    }
                }, 500);
            } else {
                progressBar.setVisibility(View.GONE);
                tv_footerview.setText("————我是有底线的————");
            }
        } else {
            position = position - size;
            if (isNeedSetClickListener(position)) { //如果需要设置监听
                holder.itemView.setOnClickListener(new MyItemClickListenerAdapter(holder));
            }
            if (viewIdsInItem != null && viewIdsInItem.length > 0 && isNeedSetViewInItemClickListener(position)) {
                for (int i = 0; i < viewIdsInItem.length; i++) {
                    View v = holder.getView(viewIdsInItem[i]);
                    if (v != null) {
                        v.setOnClickListener(new MyViewInItemClickListenerAdapter(holder, viewIdsInItem[i]));
                    }
                }
            }
            if (position == mDatas.size()) {
                position = position - 1;
            }
            convert(holder, mDatas.get(position), position);
        }

    }

    /**
     * 判断是否需要设置监听的
     *
     * @param position
     * @return
     */
    private boolean isNeedSetClickListener(int position) {
        if (itemTypesForItem == null || itemTypesForItem.length == 0) {
            return true;
        }
        int itemType = getItemType(position);
        for (int i = 0; i < itemTypesForItem.length; i++) {
            if (itemType == itemTypesForItem[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否需要设置item内的控件的监听事件
     *
     * @param position
     * @return
     */
    private boolean isNeedSetViewInItemClickListener(int position) {
        if (itemTypesForViewInItem == null || itemTypesForViewInItem.length == 0) {
            return true;
        }
        int itemType = getItemType(position);
        for (int i = 0; i < itemTypesForViewInItem.length; i++) {
            if (itemType == itemTypesForViewInItem[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 实现列表的显示
     *
     * @param holder   RecycleView的ViewHolder
     * @param entity   实体对象
     * @param position 当前的下标
     */
    public abstract void convert(CommonRecyclerViewHolder holder, T entity, int position);

    /**
     * 布局文件被转化成View的时候调用
     *
     * @param vh
     * @param viewType
     */
    public void viewCreated(CommonRecyclerViewHolder vh, int viewType) {

    }

    /**
     * @param viewType 返回值就是根据这个值进行判断返回的
     *                 对头部不起作用
     * @return
     */
    public abstract int getLayoutViewId(int viewType);


    /**
     * 集合的长度和头部试图的个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return loadmore ? mDatas.size() + headers.size() + 1 : mDatas.size() + headers.size();
    }

    /**
     * 设置所有的条目的监听事件
     *
     * @param onRecyclerViewItemClickListener
     */
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    /**
     * 设置条目的监听,具有一个筛选作用<br>
     * 针对{@link CommonRecyclerViewAdapter#getItemType(int)}}的值在
     *
     * @param onRecyclerViewItemClickListener
     * @param itemTypes                       只有通过
     */
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener, int... itemTypes) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
        this.itemTypesForItem = itemTypes;
    }


    /**
     * 每一个item由于都是一样的,那么里面的有些控件有时候需要点击事件,那么这里框架代为处理
     */
    public interface OnViewInItemClickListener {

        /**
         * 回调的方法
         *
         * @param v
         * @param position
         */
        public void onViewInItemClick(View v, int position);

    }

    /**
     * 设置条目里面的控件的监听事件
     *
     * @param onViewInItemClickListener 回掉接口
     * @param viewIdsInItem             item中需要监听的view的id数组,可以为null
     */
    public void setOnViewInItemClickListener(OnViewInItemClickListener onViewInItemClickListener, int... viewIdsInItem) {
        this.viewIdsInItem = viewIdsInItem;
        this.onViewInItemClickListener = onViewInItemClickListener;
    }

    /**
     * 设置item里面的控件的点击事件起作用的ItemType
     *
     * @param itemTypes
     */
    public void setItemTypesInItem(int... itemTypes) {
        this.itemTypesForViewInItem = itemTypes;
    }

    /**
     * 条目的点击事件
     */
    public interface OnRecyclerViewItemClickListener {

        /**
         * 回调的方法
         *
         * @param v
         * @param position
         */
        public void onItemClick(View v, int position);

    }

    /**
     * 实现点击的接口,每一个ViewItem都对应一个这个类,每一个都不一样的对象
     */
    private class MyItemClickListenerAdapter implements View.OnClickListener {

        /**
         * 条目的下标
         */
        private CommonRecyclerViewHolder h;

        public MyItemClickListenerAdapter(CommonRecyclerViewHolder h) {
            this.h = h;
        }

        @Override
        public void onClick(View v) {
            if (onRecyclerViewItemClickListener != null) {
                //回调方法
                onRecyclerViewItemClickListener.onItemClick(v, h.getAdapterPosition() - headers.size());
            }
        }

    }

    /**
     * 实现点击的接口,每一个ViewInItem都对应一个这个类,每一个都不一样的对象
     */
    private class MyViewInItemClickListenerAdapter implements View.OnClickListener {

        /**
         * 条目的下标
         */
        private CommonRecyclerViewHolder h;

        private int viewId;

        public MyViewInItemClickListenerAdapter(CommonRecyclerViewHolder h, int viewId) {
            this.h = h;
            this.viewId = viewId;
        }

        @Override
        public void onClick(View v) {
            if (onViewInItemClickListener != null) {
                //回调方法
                onViewInItemClickListener.onViewInItemClick(v, h.getAdapterPosition() - headers.size());
            }
        }

    }


    /**
     * 全文地址请点击：https://blog.csdn.net/qibin0506/article/details/49716795?utm_source=copy
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 全文地址请点击：https://blog.csdn.net/qibin0506/article/details/49716795?utm_source=copy
     */
    @Override
    public void onViewAttachedToWindow(CommonRecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }


    //加载更多完成(没有更多数据了)
    public void loadMoreComplete(boolean b) {
        canloadMore = !b;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onloadmore();
    }


    public void refreshDatas(List<T> data, boolean isClear) {
        isLoading = false;
        if (isClear) {
            mDatas.clear();
            if (data != null && data.size() > 0) {
                loadmore = setloadmore;
            }
        }
        if (data != null && data.size() > 0) {
            mDatas.addAll(data);
        } else {
            loadMoreComplete(true);
        }
        notifyDataSetChanged();
    }
}