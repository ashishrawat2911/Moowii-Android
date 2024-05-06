package com.ashish.moowii.utils

interface Mapper<D, E> {
    fun map(type: D): E
}
