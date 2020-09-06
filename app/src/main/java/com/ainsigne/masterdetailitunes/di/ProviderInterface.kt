package com.ainsigne.masterdetailitunes.di

/**
 * Utility provider
 */
interface Provider<T> {
    /**
     * Utility get
     */
    fun get(): T
}