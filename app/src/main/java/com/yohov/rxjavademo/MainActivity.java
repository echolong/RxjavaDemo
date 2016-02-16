package com.yohov.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button rxBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxBtn = (Button) findViewById(R.id.btn_rxjava);
        rxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test4();
            }
        });
    }

    private void test() {

        Observable<Integer> observableString = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                for (int i = 0; i < 5; i++) {
                    observer.onNext(i);
                }
                observer.onCompleted();
            }
        });

        Subscription subscription = observableString.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.i("rxjava", "完成");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("rxjava", "data_" + integer);
            }
        });
    }

    public void test2() {
        Observable<String> observable = Observable.just(helloWorld());
        Subscription subscription = observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("rxjava", "完成");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("rxjava", "失败");
            }

            @Override
            public void onNext(String s) {
                Log.i("rxjava", s);
            }
        });
    }

    private String helloWorld() {
        return "hello world";
    }


    public void test3() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };
        //观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };
        //被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hhe");
            }
        });

        Observable<String> observable1 = Observable.just("ha", "ha", "da");
        //添加订阅
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }

    public void test4() {
        String[] strings = new String[]{"n", "a", "m", "e"};
        Observable.from(strings).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("rxjava", s);
            }
        });

    }
}
