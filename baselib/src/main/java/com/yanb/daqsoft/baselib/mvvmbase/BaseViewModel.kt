package com.yanb.daqsoft.baselib.mvvmbase
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.yanb.daqsoft.baselib.mvvmbase.bus.evennt.SingleLiveEvent
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * ViewModel的基类
 * 数据绑定
 */
class BaseViewModel<M :BaseModel> :AndroidViewModel,IBaseViewModel, Consumer<Disposable> {
    protected var model: M? = null
    constructor(application: Application):super(application){

    }
    override fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerRxBus() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeRxBus() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun accept(t: Disposable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    inner class UIChangeLiveData : SingleLiveEvent<Any>() {
        private var showDialogEvent: SingleLiveEvent<String>? = null
        private var dismissDialogEvent: SingleLiveEvent<Void>? = null
        private var startActivityEvent: SingleLiveEvent<Map<String, Any>>? = null
        private var startContainerActivityEvent: SingleLiveEvent<Map<String, Any>>? = null
        private var finishEvent: SingleLiveEvent<Void>? = null
        private var onBackPressedEvent: SingleLiveEvent<Void>? = null

        fun getShowDialogEvent(): SingleLiveEvent<String> {
            return showDialogEvent = createLiveData(showDialogEvent)
        }

        fun getDismissDialogEvent(): SingleLiveEvent<Void> {
            return dismissDialogEvent = createLiveData(dismissDialogEvent)
        }

        fun getStartActivityEvent(): SingleLiveEvent<Map<String, Any>> {
            return startActivityEvent = createLiveData(startActivityEvent)
        }

        fun getStartContainerActivityEvent(): SingleLiveEvent<Map<String, Any>> {
            return startContainerActivityEvent = createLiveData(startContainerActivityEvent)
        }

        fun getFinishEvent(): SingleLiveEvent<Void> {
            return finishEvent = createLiveData(finishEvent)
        }

        fun getOnBackPressedEvent(): SingleLiveEvent<Void> {
            return onBackPressedEvent = createLiveData(onBackPressedEvent)
        }

        private fun createLiveData(liveData: SingleLiveEvent<Any>?): SingleLiveEvent<Any> {
            var liveData = liveData
            if (liveData == null) {
                liveData = SingleLiveEvent()
            }
            return liveData
        }

        fun observe(owner: LifecycleOwner, observer: Observer<*>) {
            super.observe(owner, observer)
        }
    }


}