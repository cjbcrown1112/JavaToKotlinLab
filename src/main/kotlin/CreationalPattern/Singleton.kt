package CreationalPattern

import junit.framework.Assert.assertEquals
import org.junit.Test

object PrinterDriver {
    init {
        println("Initializing with object: $this")
    }

    fun print(): PrinterDriver =
        apply {
            println("Printing with object: $this")
        }
}

class SingletonTest {
     @Test
     fun Singleton() {
         println("Start")
         val printerFirst = PrinterDriver.print()
         val printerSecond = PrinterDriver.print()

         assertEquals(printerFirst, PrinterDriver)
         assertEquals(printerSecond, PrinterDriver)
     }
}

