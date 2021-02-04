 fun testComplex()
 {
     val a = ComplexNumber(5.0, 3.0)
     val b = ComplexNumber(4.0, 6.0)
     a.sum(b).show()
     a.mul(b).show()
     a.div(b).show()
     a.sub(b).show()
     a.toAlgebra()
     a.toTrigonometric()
     b.toAlgebra()
     b.toTrigonometric()
 }

 @Throws(Matrix.IncorrectSizeOfMatrix::class)
 fun testMatrix()
 {
     val matrixData = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))
     val a = Matrix(matrixData)
     a.show()
     val b = Matrix(3, 3)
     b.show()
     val c = a.sum(b)
     c.show()
     val d = a.sub(b)
     d.show()
     val e = a.mul(b)
     e.show()
     val f = a.transpose()
     f.show()
 }

 @Throws(ComplexMatrix.IncorrectSizeOfMatrix::class)
 fun testComplexMatrix()
 {
     val a = ComplexMatrix(arrayOf(doubleArrayOf(1.0, 2.0, 3.0), doubleArrayOf(4.0, 5.0, 6.0), doubleArrayOf(7.0, 8.0, 9.0)))
     a.show()
     val b = ComplexMatrix(3, 3)
     b.show()
     val c = a.sum(b)
     c.show()
     val d = a.sub(b)
     d.show()
     val e = a.mul(b)
     e.show()
     val f = a.transpose()
     f.show()
 }

 @Throws(Matrix.IncorrectSizeOfMatrix::class, ComplexMatrix.IncorrectSizeOfMatrix::class)
 fun main()
 {
     testComplex()
     testMatrix()
     testComplexMatrix()
 }