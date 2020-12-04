package com.example.journal.utils

class Event<T> {

    private var mContent : T

    private var hasBeenHandled = false

    constructor(content: T) {
        if (content == null) {
            throw IllegalArgumentException("null values in Event are not allowed.");
        }
        mContent = content
    }

    fun  <T: Any> getContentIfNotHandled() : T?  {

        if (hasBeenHandled) {
            return null
        } else {
            hasBeenHandled = true
            return mContent as T
        }
    }

    fun getContent() = mContent

    fun hasBeenHandled() : Boolean {
        return hasBeenHandled
    }
}