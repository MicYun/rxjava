package com.run.rxjava.kotlin.rxbus

import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by MicYun on 2018/7/9.
 */
class RxBus private constructor(){

    private object RxBusHolder {
        val holder = RxBus()
    }

    companion object {
        val instance = RxBusHolder.holder
    }

    private val mSubjectMap = ConcurrentHashMap<Any, MutableList<Subject<*>>>()


    fun <T> register(@NonNull tag: Any, @Nullable cls: Class<T>): Observable<T> {
        var subjectList = mSubjectMap[tag]
        if (subjectList == null) {
            subjectList = mutableListOf()
            mSubjectMap[tag] = subjectList
        }

        var mSubject: Subject<T> = PublishSubject.create()
        subjectList.add(mSubject)

        return mSubject
    }


    fun unregister(@NonNull tag: Any, observable: Observable<*>): RxBus {

        val subjectList = mSubjectMap[tag]
        if (subjectList != null) {
            subjectList.remove(observable)
            if (isEmpty(subjectList)) {
                mSubjectMap.remove(tag)
            }
        }

        return this
    }

    fun unregisterAllForTag(@NonNull tag: Any) {
        val subjectList = mSubjectMap[tag]
        subjectList?.let {
            mSubjectMap.remove(it)
        }
    }

    fun postWithClassNameAsTag(@NonNull content: Any) {

    }

    fun <T> post(@NonNull tag: Any, @NonNull content: T) {
        val subjectList = mSubjectMap[tag]
        subjectList?.let {
            for (subject in subjectList) {
                //TODO(协变、型变)
            }
        }
    }

    private fun <T> isEmpty(@NonNull collection: MutableCollection<T>): Boolean {
        return collection == null || collection.isEmpty()
    }
}