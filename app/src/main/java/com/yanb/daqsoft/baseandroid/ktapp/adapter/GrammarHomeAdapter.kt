package com.yanb.daqsoft.baseandroid.ktapp.adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.hazz.kotlinmvp.view.recyclerview.adapter.CommonAdapter
import com.yanb.daqsoft.baseandroid.R
import com.yanb.daqsoft.baseandroid.ktapp.mvp.model.bean.HomeBean
import com.yanb.daqsoft.baselib.utils.ToastUtils
import com.yanb.daqsoft.baselib.utils.banner.Banner
import com.yanb.daqsoft.baselib.utils.glide.GlideApp
import com.yanb.daqsoft.baselib.utils.glide.GlideImageLoader
import io.reactivex.Observable

/**
 * kt首页适配器
 * @author 严博
 * @date 2019-6-24.15:21
 * @version 1.0.0
 * @since JDK 1.8
 */
class GrammarHomeAdapter(context : Context,data :ArrayList<HomeBean.Issue.Item>)
    :CommonAdapter<HomeBean.Issue.Item>(context,data,-1){
    var bannerIteamSize = 0

    /**
     * 此为伴生对象，可通过外部类访问他
     */
    companion object{
        // banner类型
        private const val ITEM_TYPE_BANNER = 1
        private const val ITEM_TYPE_TEXTHEADER = 2// 有文字的
        private const val ITEM_TYPE_CONTENT = 3// item
    }

    /**
     * 设置banner的大小
     */
    fun setBannerSize(count:Int){
        bannerIteamSize = count
    }
    fun addItemData(itemList:ArrayList<HomeBean.Issue.Item>){
        this.mData.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when{
            position==0->
                    ITEM_TYPE_BANNER
            mData[position+bannerIteamSize-1].type=="textHeader"->
                    ITEM_TYPE_TEXTHEADER
            else->
                    ITEM_TYPE_CONTENT
        }
    }

    /**
     * 得到Item数量banner作为一个item
     */
    override fun getItemCount(): Int {
        return when{
            mData.size>bannerIteamSize->
                    mData.size-bannerIteamSize+1
            mData.isEmpty()->0
            else->1
        }
    }

    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {
        when(getItemViewType(position)){
            ITEM_TYPE_BANNER->{
                val bannerList:ArrayList<HomeBean.Issue.Item> = mData.take(bannerIteamSize).toCollection(ArrayList())
                val bannerImgList = ArrayList<String>()
                val bannerTitleList = ArrayList<String>()
                // 取出banner显示的img和name
                Observable.fromIterable(bannerList)
                        .subscribe { list ->
                            bannerImgList.add(list.data?.cover?.feed?:"")
                            bannerTitleList.add(list.data?.title?:"")
                        }
                with(holder){
                    getView<Banner>(R.id.header_banner).run {
                        setImages(bannerImgList)
                                .setImageLoader(GlideImageLoader())
                                .setOnBannerListener { position -> ToastUtils.showCenterShort("你点击了$position")  }
                                .start()
                    }
                }
            }
            ITEM_TYPE_TEXTHEADER->
                holder.setText(R.id.tvHeader, mData[position + bannerIteamSize - 1].data?.text ?: "")
        //content
            ITEM_TYPE_CONTENT -> {
                setVideoItem(holder, mData[position + bannerIteamSize - 1])
            }
        }
    }

    /**
     * 创建布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            ITEM_TYPE_BANNER ->
                ViewHolder(inflaterView(R.layout.header_banner, parent))
            ITEM_TYPE_TEXTHEADER ->
                ViewHolder(inflaterView(R.layout.item_kt_home_header, parent))
            else ->
                ViewHolder(inflaterView(R.layout.item_home_content, parent))
        }
    }
    /**
     * 加载布局
     */
    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return view ?: View(parent.context)
    }

    /**
     * 加载 content item
     */
    private fun setVideoItem(holder: ViewHolder, item: HomeBean.Issue.Item) {
        val itemData = item.data

        val defAvatar = R.mipmap.common_icon_account
        val cover = itemData?.cover?.feed
        var avatar = itemData?.author?.icon
        var tagText: String? = "#"

        // 作者出处为空，就显获取提供者的信息
        if (avatar.isNullOrEmpty()) {
            avatar = itemData?.provider?.icon
        }
        // 加载封页图
        GlideApp.with(mContext)
                .load(cover)
                .placeholder(R.mipmap.icon_state_img_loading)
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.getView(R.id.iv_cover_feed))

        // 如果提供者信息为空，就显示默认
        if (avatar.isNullOrEmpty()) {
            GlideApp.with(mContext)
                    .load(defAvatar)
                    .placeholder(R.mipmap.icon_state_img_loading).circleCrop()
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(holder.getView(R.id.iv_avatar))

        } else {
            GlideApp.with(mContext)
                    .load(avatar)
                    .placeholder(R.mipmap.icon_state_img_loading).circleCrop()
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(holder.getView(R.id.iv_avatar))
        }
        holder.setText(R.id.tv_title, itemData?.title ?: "")

        //遍历标签
        itemData?.tags?.take(4)?.forEach {
            tagText += (it.name + "/")
        }
        // 格式化时间
        val timeFormat = durationFormat(itemData?.duration)

        tagText += timeFormat

        holder.setText(R.id.tv_tag, tagText!!)

        holder.setText(R.id.tv_category, "#" + itemData?.category)

        holder.setOnItemClickListener(listener = View.OnClickListener {
            //goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_cover_feed), item)
        })


    }

    fun durationFormat(duration: Long?): String {
        val minute = duration!! / 60
        val second = duration % 60
        return if (minute <= 9) {
            if (second <= 9) {
                "0$minute' 0$second''"
            } else {
                "0$minute' $second''"
            }
        } else {
            if (second <= 9) {
                "$minute' 0$second''"
            } else {
                "$minute' $second''"
            }
        }
    }

}
