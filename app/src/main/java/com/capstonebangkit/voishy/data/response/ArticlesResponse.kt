package com.capstonebangkit.voishy.data.response

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItem(

	@field:SerializedName("times")
	val times: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("article")
	val article: String? = null
)
