package com.daqsoft.videoplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.dueeeke.videocontroller.StandardVideoController
import com.dueeeke.videoplayer.ijk.IjkPlayerFactory
import com.dueeeke.videoplayer.listener.OnVideoViewStateChangeListener
import com.dueeeke.videoplayer.player.VideoView
import com.yanb.daqsoft.baselib.utils.KLog
import com.yanb.daqsoft.baselib.utils.titlebar.CommonTitleBar
import kotlinx.android.synthetic.main.activity_video_normal.*

/**
 * 常规视频播放器
 */
class VideoNormalActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * 视频播放地址
     */
    val videoUrl = "http://vfx.mtime.cn/Video/2019/03/12/mp4/190312143927981075.mp4"
    // 是否是直播
    val isLive = false
    val videoTitle = "视频播放"
    var controller: StandardVideoController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_normal)
        initView()
    }

    /**
     * 初始化数据
     */
    private fun initView() {
        initListener()
        video_normal_title.setListener { v, action, extra ->
            if (action == CommonTitleBar.ACTION_LEFT_BUTTON || action == CommonTitleBar.ACTION_LEFT_TEXT) {
                finish()
            }
        }
        controller = StandardVideoController(this)
        if (isLive) {
            controller?.setLive()
        }
        controller?.setTitle(videoTitle)
        video_normal_player.run {
            setVideoController(controller)
            setUrl(videoUrl)
            addOnVideoViewStateChangeListener(object : OnVideoViewStateChangeListener {
                override fun onPlayStateChanged(playState: Int) {
                    when (playState) {
                        VideoView.STATE_IDLE ->
                            KLog.e("STATE_IDLE")
                        VideoView.STATE_PREPARING ->
                            KLog.e("STATE_PREPARING")
                        VideoView.STATE_PREPARED ->
                            KLog.e("视频宽${videoSize[0]}-->视频高${videoSize[1]}")
                        VideoView.STATE_PLAYING ->
                            KLog.e("STATE_PLAYING")
                        VideoView.STATE_PAUSED ->
                            KLog.e("STATE_PAUSED")
                        VideoView.STATE_BUFFERING ->
                            KLog.e("STATE_BUFFERING")
                        VideoView.STATE_BUFFERED ->
                            KLog.e("STATE_BUFFERED")
                        VideoView.STATE_PLAYBACK_COMPLETED ->
                            KLog.e("STATE_PLAYBACK_COMPLETED")
                        VideoView.STATE_ERROR ->
                            KLog.e("STATE_ERROR")
                        else ->
                            KLog.e("默认")
                    }
                }

                override fun onPlayerStateChanged(playerState: Int) {
                    when (playerState) {
                        // 小屏
                        VideoView.PLAYER_NORMAL ->
                            KLog.e("小屏")
                        VideoView.PLAYER_FULL_SCREEN ->
                            KLog.e("全屏")
                        else ->
                            KLog.e("默认")
                    }
                }

            })
            //使用IjkPlayer解码
            setPlayerFactory(IjkPlayerFactory.create(this@VideoNormalActivity))
            start()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        video_normal_player.pause()
    }

    override fun onResume() {
        super.onResume()
        video_normal_player.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        video_normal_player.release()
    }

    override fun onBackPressed() {
        if (!video_normal_player.onBackPressed()) {
            super.onBackPressed()
        }
    }

    var i1 = 0
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.video_btn_normal ->
                video_normal_player.setScreenScale(VideoView.SCREEN_SCALE_DEFAULT)
            R.id.video_btn_16_9 ->
                video_normal_player.setScreenScale(VideoView.SCREEN_SCALE_16_9)
            R.id.video_btn_4_3 ->
                video_normal_player.setScreenScale(VideoView.SCREEN_SCALE_4_3)
            R.id.video_btn_ben ->
                video_normal_player.setScreenScale(VideoView.SCREEN_SCALE_ORIGINAL)
            R.id.video_btn_tian ->
                video_normal_player.setScreenScale(VideoView.SCREEN_SCALE_MATCH_PARENT)
            R.id.video_btn_cai ->
                video_normal_player.setScreenScale(VideoView.SCREEN_SCALE_CENTER_CROP)
            // 播放速度0.5f,0.75f,1.0f,1.5f,2.0f
            R.id.video_btn_speed_20 ->
                video_normal_player.setSpeed(2.0f)
            //R.id.video_btn_xuan ->
                //video_normal_player.setMirrorRotation(i1 % 2 == 0)
                //i1=9
            else ->
                KLog.e("默认")

        }
    }
    /**
     * 监听
     */
    private fun initListener() {
        video_btn_normal.setOnClickListener(this)
        video_btn_16_9.setOnClickListener(this)
        video_btn_4_3.setOnClickListener(this)
        video_btn_ben.setOnClickListener(this)
        video_btn_tian.setOnClickListener(this)
        video_btn_cai.setOnClickListener(this)
        video_btn_speed_20.setOnClickListener(this)
    }
}