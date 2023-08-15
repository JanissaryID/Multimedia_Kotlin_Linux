package classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MenuNavigation {
    val listMenuIcon: List<String> = listOf("images.svg", "videos.svg", "musics.svg")
    val listMenuCaption: List<String> = listOf("Images", "Videos", "Music")

    var menuIndex : Int by mutableStateOf(-1)

    var menuFocus : Int by mutableStateOf(-1)
    var focus : Boolean by mutableStateOf(true)
}