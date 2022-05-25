package CreationalPattern

import org.junit.Test
import java.io.File

class Dialog {

    fun setTitle(text: String) = println("Setting Title Text $text")
    fun setTitleColor(color: String) = println("Setting Tittle Color $color")
    fun setMessage(text: String) = println("Setting Message $text")
    fun setMessageColor(color: String) = println("Setting Message Color $color")
    fun setImage(bitmapBytes: ByteArray) = println("Setting Image with Size ${bitmapBytes.size}")

    fun show() = println("Showing Dialog $this")
}

class DialogBuilder() {
    constructor(init: DialogBuilder.() -> Unit) : this() {
        init()
    }

    private var titleHolder: TextView? = null
    private var messageHolder: TextView? = null
    private var imageHolder: File? = null

    fun title(attributes: TextView.() -> Unit) {
        titleHolder = TextView().apply {
            attributes()
        }
    }

    fun message(attributes: TextView.() -> Unit) {
        messageHolder = TextView().apply {
            attributes()
        }
    }

    fun image(attributes: () -> File) {
        imageHolder = attributes()
    }

    fun build(): Dialog {
        println("Build")
        val dialog = Dialog()

        titleHolder?.apply {
            dialog.setTitle(text)
            dialog.setTitleColor(color)
        }

        messageHolder?.apply {
            dialog.setMessage(text)
            dialog.setMessageColor(color)
        }

        imageHolder?.apply {
            dialog.setImage(readBytes())
        }

        return dialog
    }

    class TextView {
        var text: String = ""
        var color: String = "#00000"
    }
}

fun dialog(init: DialogBuilder.() -> Unit): Dialog =
    DialogBuilder(init).build()

class BuilderTest {

    @Test
    fun Builder() {
        println("Build Dialog")

        val dialog: Dialog =
            dialog {
                title {
                    text = "Dialog Title"
                    color = "#454242"
                }
                message {
                    text = "Dialog Message"
                    color = "#333333"
                }
                image {
                    File.createTempFile("image", "jpg")
                }
            }

        println("Show Dialog")
        dialog.show()
    }
}