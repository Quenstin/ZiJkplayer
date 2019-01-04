package com.kotlin.mkotlin.base

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/1/4
 * 作用:
 */
data class BaseResponse<T>(val error:String,val errorCode:String,var data: T) {
}