import Operation.ADDITION
import Operation.MULTIPLICATION
import Operation.SQUARE
import java.math.BigInteger

enum class Operation {
    ADDITION, MULTIPLICATION, SQUARE
}

data class Monkey(
    val id: Int,
    var items: MutableList<BigInteger> = mutableListOf(),
    val operation: Operation = SQUARE,
    val operand: Int? = 0,
    val divisor: Int? = 1,
    var inspections: Int = 0,
    val monkeyPals: Set<Int> = setOf()
) {
    fun inspect(isControlledWorry: Boolean = true, getMonkeyPal: (id: Int) -> Monkey) {
        items.forEach {
            var newWorryLevel = fetchWorryLevel(it)
            inspections++
            // worry level reduction after inspection
            if (isControlledWorry) {
                newWorryLevel /= BigInteger.valueOf(3)
            }
            // test worry level
            if (newWorryLevel.mod(divisor!!.toBigInteger()) == BigInteger.ZERO) {
            //throw item to monkey pal 1
            val pal = getMonkeyPal(monkeyPals.first())
            pal.items = pal.items.plus(newWorryLevel).toMutableList()
        } else {
            // throw item to monkey pal 2
            val pal = getMonkeyPal(monkeyPals.last())
            pal.items = pal.items.plus(newWorryLevel).toMutableList()
        }
        }
        //clear the item list
        items.clear()
    }

    private fun fetchWorryLevel(it: BigInteger) = when (operation) {
        ADDITION -> it.plus(operand!!.toBigInteger())
        MULTIPLICATION -> it.multiply(operand!!.toBigInteger())
        SQUARE -> it.multiply(it)
    }
}
