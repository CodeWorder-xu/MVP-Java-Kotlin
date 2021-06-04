package com.xhf.wholeproject.rxbus;


import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import io.reactivex.rxjava3.processors.PublishProcessor;

/**
 * RxBus
 * Created by qiaoyan on 2017/8/21.
 */
public final class RxBus {

    private final FlowableProcessor<Object> mBus;

    private RxBus() {
        // toSerialized method made bus thread safe
        mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus get() {
        return Holder.BUS;
    }

    public void post(String code, Object obj) {

        mBus.onNext(new RxEvent(code, obj));
    }

    public void post(Object obj) {
        mBus.onNext(obj);
    }

    public <T> Flowable<T> toFlowable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public Flowable<Object> toFlowable() {
        return mBus;
    }

    /**
     *
     */
    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }

    /**
     *
     */
    public static class RxEvent {
        private String code;
        private Object obj;

        public RxEvent(String code, Object obj) {
            this.code = code;
            this.obj = obj;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }

        @Override
        public String toString() {
            return "RxEvent{"
                    + "code='" + code + '\''
                    + ", obj=" + obj
                    + '}';
        }
    }


}

