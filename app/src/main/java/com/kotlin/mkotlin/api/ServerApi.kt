package com.kotlin.mkotlin.api

import com.kotlin.mkotlin.base.BaseResponse
import com.kotlin.mkotlin.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/4
 * 作用:
 */
interface ServerApi {

    /**
     * 登录
     */
    @POST("user/login")
    fun login(@QueryMap param: Map<String, String>): Observable<BaseResponse<LoginBean>>
}