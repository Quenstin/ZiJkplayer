package com.shehuan.wanandroid.base.net.convert

import com.kotlin.mkotlin.base.BaseResponse
import com.shehuan.wanandroid.base.net.exception.ApiException
import io.reactivex.functions.Function

class ResponseConvert<E> : Function<BaseResponse<E>, E> {
    override fun apply(baseResponse: BaseResponse<E>): E {
        if ("0" != baseResponse.errorCode) {
            throw ApiException(baseResponse.errorCode, baseResponse.error)
        }
        if (baseResponse.data == null) {
            baseResponse.data = "" as E
        }
        return baseResponse.data
    }
}