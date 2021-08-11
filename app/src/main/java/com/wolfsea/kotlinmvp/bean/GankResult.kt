package com.wolfsea.kotlinmvp.bean

/**
 *@desc  Gank实体类
 *@author liuliheng
 *@time 2021/8/11  23:44
 **/

data class Gank(
    val _id: String,
    val createdAt: String,
    val desc: String,
    val publishedAt: String,
    val source: String,
    val type: String,
    val url: String,
    val used: Boolean,
    val who: String
)

data class GankResult(
    val error: Boolean,
    val results: List<Gank>
)