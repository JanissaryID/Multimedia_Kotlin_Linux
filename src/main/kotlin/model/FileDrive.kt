package model

import java.io.File
import java.util.ArrayList

data class FileDrive(
    var listFileAll: ArrayList<File> = arrayListOf(),
    var listMusic: ArrayList<File> = arrayListOf(),
    var listImage: ArrayList<File> = arrayListOf(),
    var listVideos: ArrayList<File> = arrayListOf()
)
