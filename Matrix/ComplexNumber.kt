import kotlin.math.abs
import kotlin.math.sqrt

class ComplexNumber(real:Double, imaginary:Double)
{
    private var real:Double = 0.0
    private var imaginary:Double = 0.0

    init
    {
        this.real = real
        this.imaginary = imaginary
    }

    fun sum(second_number:ComplexNumber):ComplexNumber
    {
        return ComplexNumber(
            this.real + second_number.real,
            this.imaginary + second_number.imaginary)
    }

    fun sub(second_number:ComplexNumber):ComplexNumber
    {
        return ComplexNumber(
            this.real - second_number.real,
            this.imaginary - second_number.imaginary)
    }

    fun mul(second_number:ComplexNumber):ComplexNumber
    {
        return ComplexNumber(
            this.real * second_number.real - this.imaginary * second_number.imaginary,
            this.real * second_number.imaginary + this.imaginary * second_number.real)
    }

    fun div(second_number:ComplexNumber):ComplexNumber
    {
        return ComplexNumber(
            this.real / second_number.real - this.imaginary / second_number.imaginary,
            this.real / second_number.imaginary + this.imaginary / second_number.real)
    }

    override fun toString():String
    {
        return ("ComplexNumber{" +
                "real=" + real +
                ", imaginary=" + imaginary +
                '}'.toString())
    }

    fun show()
    {
        System.out.printf("%s", this.toAlgebra() + "\n")
    }

    fun toAlgebra():String
    {
        return if (imaginary < 0) {
            real.toString() + "-" + abs(imaginary) + "i"
        } else
            real.toString() + "+" + imaginary + "i"
    }

    fun toTrigonometric():String
    {
        return ("z = " + sqrt(real * real + imaginary * imaginary) +
                "(" + real / (sqrt(real * real + imaginary * imaginary)) +
                " + i*" + imaginary / (sqrt(real * real + imaginary * imaginary)) + ")")
    }
}