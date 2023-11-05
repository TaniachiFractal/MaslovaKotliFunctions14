import com.sun.xml.internal.fastinfoset.util.StringArray
fun main(args: Array<String>)
{
    while(true)
    {
        println("Домашняя работа Масловой Т. по теме Функции. Введите номер задания 1 или 4, или любую клавишу для выхода")
        val act = readln()
        if (act == "1") main1()
        else if (act == "4") main4()
        else
        {
            println("Выход")
            break
        }
    }

}



fun main1()
{
    println("Камень, Ножницы, Бумага?\nДля выбора введите первую букву варианта")
    var des = readln()
    des=des.uppercase()
    var PC = (0..2).random()
    var nameUser: String
    var namePC: String
    when (des)
    {
        "К" -> nameUser = "Камень";
        "Н" -> nameUser = "Ножницы";
        "Б" -> nameUser = "Бумагу";
        else -> nameUser = "Бутылку лимонада";
    }
    when (PC)
    {
        0 -> namePC = "Камень"
        1 -> namePC = "Ножницы"
        2 -> namePC = "Бумагу"
        else -> namePC = "Бутылку лимонада"
    }
    var winner = didPCwin(des,PC)
    when(winner)
    {
        1 -> println("Вы выбрали $nameUser, Компьютер выбрал $namePC. Победил компьютер")
        0 -> println("Вы выбрали $nameUser, Компьютер выбрал $namePC. ВЫ ВЫИГРАЛИ!!!")
        -1 -> println("Вы выбрали $nameUser, Компьютер выбрал $namePC. Ничья")
    }

    println()
    return;
}
fun didPCwin(user:String,intPC:Int):Int
{
    var intUser =0
    when (user)
    {
        "К" -> intUser = 0
        "Н" -> intUser = 1
        "Б" -> intUser = 2
        else -> intUser = -1
    }
    if (intUser == -1) return 1;
    if (intUser == intPC) return -1;
    if ((intUser - intPC == 1) || (intUser - intPC == -2)) return 1;
    else return 0;
}
//для символа 'А' используются строки от "000" до "068", так как вероятность
// появления символа 'А' равна 0.069. Для символа 'Б' используются строки от "069" до "081"
// и т.д. Для символа 'Я' используются строки от "837" до "853". Для символа ПРОБЕЛ
// используются строки от "854" до "999".
fun main4()
{
    val alphLtr_ = "?АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ ?"
    val alphLtr = alphLtr_.toCharArray()
    val alphCode = arrayOf(0,
        69,13,38,14,24,71,7,16,64,10,29,39,27,57,94,26,42,46,54,23,3,8,
        5,12,6,4,1,15,13,2,5,17,146, 0
    )
    val n=alphLtr_.length
    val codePlus = Array(n,{i->i.toInt()})
    for (i in 0..n-1) codePlus[i]=alphCode[i]

    var sum = 0
    for (i in 0..n-1)
    {
        codePlus[i]+=sum
        sum=codePlus[i]
    }
    val table = Array(n){IntArray(150)}

    fun LtrToCode(ltr: Char):Int
    {
        var code = alphLtr.indexOf(ltr)
        if (code==-1) return 0;
        if (codePlus[code]==0) return 1;
        return codePlus[code]
    }
    fun letterNum(ltr: Char):Int
    {
        var code = alphLtr.indexOf(ltr)
        if (code==-1) return 0;
        return code
    }
    fun ltrToCodePercent(ltr:Char):Int
    {
        var code = alphLtr.indexOf(ltr)
        if (code==-1) return 0;

        return alphCode[code]
    }
    fun CodeToLtr(code: Int):Char
    {
        var i=0
        while(i<32)
        {
            if (codePlus[i] < code && codePlus[i + 1] > code) break; i++
        }
        if (i==n-1) return '?'
        return alphLtr[i+1]
    }

    println("Введите 1, чтобы зашифровать текст, 2, чтобы дешифровать")
    var des = readln()
    if (des=="1")
    {
        println("Введите слово")
        var str = readln()
        str=str.uppercase()

        var curr=0
        println("Закодированная строка")
        for (i in 0..str.length-1)
        {
            if (str[i] == 'Ё')
                str = str.replace('Ё','Е')
            curr = LtrToCode(str[i])
            if (ltrToCodePercent(str[i])>0)
            curr -= (1..ltrToCodePercent(str[i])).random()

            table[letterNum(str[i])][i] = curr

            if (curr<10) print("00$curr ")
            else if (curr<100) print("0$curr ")
            else print("$curr " )
        }
        println("\nШифровальная таблица")
        var exists_ = false
        for (i in 0..n-1)
        {
            //проверка наличия значения в ячейках
            for (j in 0..149)
                if (table[i][j]>0)
                {
                    exists_=true
                    break
                }

            if (exists_)
            {
                if (alphLtr[i] == ' ') print("Пробел");
                if (alphLtr[i] == 'Е') print("Ё, ");
                print(alphLtr[i] ); print(" ")
                for (j in 0..149)
                    if (table[i][j]>0)
                    {
                        curr = table[i][j]
                        if (curr<10) print("00$curr ")
                        else if (curr<100) print("0$curr ")
                        else print("$curr ")
                    }
                println()
            }
            exists_=false

        }
    }
    else
    {
        println("Введите код")
        var strNum= readln()
        val SarrNum = strNum.split(' ')
        var temp=0
        println("Исходная строка")
        for (i in 0..SarrNum.size-1)
        {
           temp = SarrNum[i].toInt()
            print(CodeToLtr(temp))
           // println(SarrNum[i])
        }
    }


    println()
    return
}

fun main2_()
{
    val alphLtr_ = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
    val alphLtr = alphLtr_.toCharArray()
    val alphCode = arrayOf(
        21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15,
        11, 26, 5, 30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17
    )
    fun LtrToCode(ltr: Char):Int
    {
        var code = alphLtr.indexOf(ltr)
        if (code == -1) return 0;
        return alphCode[code];
    }
    fun CodeToLtr(code: Int):Char
    {
        var ltr = alphCode.indexOf(code)
        if (ltr == -1) return ' ';
        return alphLtr[ltr];
    }
    println("Введите ключевое слово")
    var key = readln();
    key = key.uppercase()
    println("Введите текст")
    var word = readln();
    word = word.uppercase()
    val n = word.length
    val wordOut = word.toCharArray()

    println("Введите 1, чтобы зашифровать текст, любую клавишу, чтобы дешифровать")
    val act = readln();
    var ltrOut: Char
    var shift = 0
    var keyI = 0
    for (i in 0..word.length-1)
    {
        if (keyI>=key.length) keyI=0

        if(act=="1")
        {
            shift = LtrToCode(word[i]) + LtrToCode(key[keyI])
            while (shift>33) shift-=33
        }
        else
        {
            shift = LtrToCode(word[i]) - LtrToCode(key[keyI])
            while (shift < 1) shift += 33
        }

        wordOut[i] = CodeToLtr(shift)
        keyI++
        print(wordOut[i])
    }
    println()
    return;
}
