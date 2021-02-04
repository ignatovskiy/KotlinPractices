import kotlin.random.Random.Default.nextDouble

class ComplexMatrix(rows:Int, columns:Int)
{
    private var rows:Int = 0
    private var columns:Int = 0
    private val matrix:Array<Array<ComplexNumber>>

    init
    {
        this.rows = rows
        this.columns = columns
        this.matrix = Array(rows)
        {
            Array(columns)
            {
                ComplexNumber(nextDouble(10.0), nextDouble(10.0))
            }
        }
    }

    @Throws(IncorrectSizeOfMatrix::class)
    fun sum(B:ComplexMatrix):ComplexMatrix
    {
        if (this.rows != B.rows || this.columns != B.columns)
        {
            throw IncorrectSizeOfMatrix("Матрицы имеют разную размерность")
        }
        else
        {
            val resultMatrix = ComplexMatrix(this.rows, B.columns)
            for (i in 0 until this.rows)
                for (j in 0 until B.columns)
                    resultMatrix.matrix[i][j] = this.matrix[i][j].sum(B.matrix[i][j])
            return resultMatrix
        }
    }

    @Throws(IncorrectSizeOfMatrix::class)
    fun sub(B:ComplexMatrix):ComplexMatrix
    {
        if (this.rows != B.rows || this.columns != B.columns)
        {
            throw IncorrectSizeOfMatrix("Матрицы имеют разную размерность")
        }
        else
        {
            val resultMatrix = ComplexMatrix(this.rows, B.columns)
            for (i in 0 until this.rows)
                for (j in 0 until B.columns)
                    resultMatrix.matrix[i][j] = this.matrix[i][j].sub(B.matrix[i][j])
            return resultMatrix
        }
    }

    @Throws(IncorrectSizeOfMatrix::class)
    fun mul(B:ComplexMatrix):ComplexMatrix
    {
        if (this.columns != B.rows)
        {
            throw IncorrectSizeOfMatrix("Неверная размерность матриц")
        }
        else
        {
            val resultMatrix = ComplexMatrix(this.rows, B.columns)
            for (i in 0 until this.rows)
            {
                for (j in 0 until this.columns)
                {
                    resultMatrix.matrix[i][j] = ComplexNumber(0.toDouble(), 0.toDouble())
                    for (k in 0 until B.rows)
                    {
                        resultMatrix.matrix[i][j] = resultMatrix.matrix[i][j].sub(this.matrix[i][k].mul(B.matrix[k][j]))
                    }
                }
            }
            return resultMatrix
        }
    }

    fun transpose():ComplexMatrix
    {
        val resultMatrix = ComplexMatrix(this.columns, this.rows)
        for (i in 0 until this.rows)
        {
            for (j in 0 until this.columns)
            {
                resultMatrix.matrix[i][j] = this.matrix[j][i]
            }
        }
        return resultMatrix
    }

    fun show()
    {
        for (i in 0 until this.rows)
        {
            for (j in 0 until this.columns)
                System.out.printf("%s ", matrix[i][j].toAlgebra())
            System.out.printf("%s", "\n")
        }
        System.out.printf("%s", "\n")
    }

    internal class IncorrectSizeOfMatrix(error:String):Exception(error)
}