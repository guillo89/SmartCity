package es.pamplona.smartcity.domain.commands

/**
 * Created by Gio on 07/02/2018.
 */

interface Command<out T> {
    fun execute(): T
}