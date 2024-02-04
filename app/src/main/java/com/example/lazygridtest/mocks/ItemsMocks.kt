package com.example.lazygridtest.mocks

import com.example.lazygridtest.R
import com.example.lazygridtest.models.ItemModel

fun getItems(): List<ItemModel>{
    val result = mutableListOf<ItemModel>()
    repeat(100){
        val text = if(it % 2 == 0){
            "!ioajdiojii298jdojwqiodj jd sadklas sklamdlqio jsaid 82d jwkd jajsdd29d j2 nsjkdn sak"
        }else{
            "1902je19"
        }
        result.add(
            ItemModel(id = it.toString(),text, imageRes = androidx.core.R.drawable.ic_call_answer)
        )
    }
    return result
}