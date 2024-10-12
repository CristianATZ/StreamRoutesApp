package net.streamroutes.sreamroutesapp.utils

object ListUtils {
    fun <T> moveItemUp(list: MutableList<T>, index: Int) {
        if (index > 0) {
            list.swap(index, index - 1)
        }
    }

    fun <T> moveItemDown(list: MutableList<T>, index: Int) {
        if (index < list.size - 1) {
            list.swap(index, index + 1)
        }
    }

    fun <T> removeItem(list: MutableList<T>, index: Int) {
        if (index >= 0 && index < list.size) {
            list.removeAt(index)
        }
    }

    private fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
        val temp = this[index1]
        this[index1] = this[index2]
        this[index2] = temp
    }

}