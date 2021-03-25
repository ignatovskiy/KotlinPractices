import java.io.File
import kotlin.math.abs
import kotlin.random.Random
import kotlinx.coroutines.*


fun main()
{
    val inputWordsFilePath = "words.txt"
    val outputWordsFilePath = "result.txt"

    val allWordsList = mutableListOf<String>()
    val neededWordsList = mutableListOf<String>()

    val minWordLength = 10

    runBlocking {
        File(inputWordsFilePath).useLines { lines -> lines.forEach { allWordsList.add(it) } }

        for (word in allWordsList)
        {
            if (word.length >= minWordLength)
            {
                neededWordsList.add(word)
            }
        }

        val wordRandomIndex = abs(Random.nextInt()) % neededWordsList.size
        val chosenWord =  neededWordsList[wordRandomIndex]

        val charactersOfWord = mutableMapOf<Char, Int>()

        for (character in chosenWord)
        {
            if (charactersOfWord[character] == null)
            {
                charactersOfWord[character] = 1
            }
            else
            {
                charactersOfWord[character] = charactersOfWord[character]!! + 1
            }
        }

        println("Добро пожаловать в игру со словами\n" +
                "Вам нужно составить как можно больше слов из заданного слова - $chosenWord \n" +
                "Перечислите эти слова в командной строке через пробел и нажмите клавишу Enter по завершению.")

        val inputString = readLine()

        if (inputString != null)
        {
            val inputWords = inputString.split(' ')

            val correctInputWords = mutableListOf<String>()

            for (word in inputWords)
            {
                var isCharactersCorrect = true
                val tempCharactersOfWord = charactersOfWord.toMutableMap()

                for (character in word)
                {
                    if (tempCharactersOfWord.containsKey(character))
                    {
                        tempCharactersOfWord[character] = tempCharactersOfWord[character]!! - 1

                        if (tempCharactersOfWord[character]!! < 0)
                        {
                            isCharactersCorrect = false
                            break
                        }
                    }
                    else
                    {
                        isCharactersCorrect = false
                        break
                    }

                }

                if (isCharactersCorrect)
                {
                    correctInputWords.add(word)
                }
                else
                {
                    println("В вашем слове $word используются символы, которых нет в заданном изначально слове.")
                }
            }

            val checkingWords = CoroutineScope(Dispatchers.Default).launch {
                var gameScore = 0

                for (word in correctInputWords)
                {
                    if (allWordsList.contains(word))
                    {
                        gameScore += word.length
                    } else
                    {
                        println("$word отсутствует в игровом источнике слов.")
                    }
                }

                println("Игра завершена. Набрано $gameScore очков.")
            }

            checkingWords.join()

            val writingWordsToFile = CoroutineScope(Dispatchers.Default).launch {
                File(outputWordsFilePath).writeText(correctInputWords.joinToString("\n"))
            }

            writingWordsToFile.join()
        }

    }
}