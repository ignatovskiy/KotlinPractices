import java.util.Random
class Matrix
{
    private var rows:Int = 0
    private var columns:Int = 0
    private val matrix:Array<IntArray>

    constructor(rows:Int, columns:Int)
    {
        this.rows = rows
        this.columns = columns
        this.matrix = Array(rows) { IntArray(columns) }
        for (i in 0 until rows)
            for (j in 0 until columns)
                this.matrix[i][j] = Random().nextInt(10)
    }

    constructor(matrix:Array<IntArray>)
    {
        this.rows = matrix.size
        this.columns = matrix[0].size
        this.matrix = Array(this.rows) { IntArray(this.columns) }
        for (i in 0 until this.rows)
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, this.columns)
    }

    @Throws(IncorrectSizeOfMatrix::class)
    fun sum(B:Matrix):Matrix
    {
        if (this.rows != B.rows || this.columns != B.columns)
        {
            throw IncorrectSizeOfMatrix("Матрицы имеют разную размерность")
        }
        else
        {
            val resultMatrix = Matrix(this.rows, B.columns)
            for (i in 0 until this.rows)
                for (j in 0 until B.columns)
                    resultMatrix.matrix[i][j] = this.matrix[i][j] + B.matrix[i][j]
            return resultMatrix
        }
    }

    @Throws(IncorrectSizeOfMatrix::class)
    fun sub(B:Matrix):Matrix
    {
        if (this.rows != B.rows || this.columns != B.columns)
        {
            throw IncorrectSizeOfMatrix("Матрицы имеют разную размерность")
        }
        else
        {
            val resultMatrix = Matrix(this.rows, B.columns)
            for (i in 0 until this.rows)
                for (j in 0 until B.columns)
                    resultMatrix.matrix[i][j] = this.matrix[i][j] - B.matrix[i][j]
            return resultMatrix
        }
    }

    @Throws(IncorrectSizeOfMatrix::class)
    fun mul(B:Matrix):Matrix
    {
        if (this.columns != B.rows)
        {
            throw IncorrectSizeOfMatrix("Неверная размерность матриц")
        }
        else
        {
            val resultMatrix = Matrix(this.rows, B.columns)
            for (i in 0 until this.rows)
            {
                for (j in 0 until this.columns)
                {
                    resultMatrix.matrix[i][j] = 0
                    for (k in 0 until B.rows)
                    {
                        resultMatrix.matrix[i][j] += this.matrix[i][k] * B.matrix[k][j]
                    }
                }
            }
            return resultMatrix
        }
    }

    fun transpose():Matrix
    {
        val resultMatrix = Matrix(this.columns, this.rows)
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
                System.out.printf("%d ", matrix[i][j])
            System.out.printf("%s", "\n")
        }
        System.out.printf("%s", "\n")
    }

    internal class IncorrectSizeOfMatrix(error:String):Exception(error)
}
