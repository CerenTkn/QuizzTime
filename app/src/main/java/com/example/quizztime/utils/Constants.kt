package com.example.quizztime.utils

import com.example.quizztime.R
import com.example.quizztime.model.Question

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val flagQuestionList = ArrayList<Question>()

        //1
        val que1 = Question(
            1, "Which country does this flag belong to?", R.drawable.bahamas,
            "Dominician",
            "Argentina",
            "El Salvador",
            "Bahamas",
            4
        )
        flagQuestionList.add(que1)

        //2
        val que2 = Question(
            2, "Which country does this flag belong to?", R.drawable.nepal,
            "Paraguay",
            "Nepal",
            "Mexico",
            "Rwanda",
            2
        )
        flagQuestionList.add(que2)

        //3
        val que3 = Question(
            3,"Which country does this flag belong to?", R.drawable.cyprus,
            "Peru",
            "Thailand",
            "Cyprus",
            "Chile",
            3
        )
        flagQuestionList.add(que3)

        //4
        val que4 = Question(
            4,"Which country does this flag belong to?", R.drawable.switzerland,
            "Ethiopia",
            "Switzerland",
            "Finland",
            "Iran",
            2
        )
        flagQuestionList.add(que4)

        //5
        val que5 = Question(
            5,"Which country does this flag belong to?", R.drawable.dominican,
            "Dominican",
            "Ghana",
            "Nauru",
            "Nigeria",
            1
        )
        flagQuestionList.add(que5)

        //6
        val que6 = Question(
            6, "Which country does this flag belong to?", R.drawable.mexico,
            "Ireland",
            "Mexico",
            "Niger",
            "Palau",
            2
        )
        flagQuestionList.add(que6)

        //7
        val que7 = Question(
            7, "Which country does this flag belong to?", R.drawable.cuba,
            "San Marino",
            "Poland",
            "Cuba",
            "Qatar",
            3
        )
        flagQuestionList.add(que7)

        //8
        val que8 = Question(
            8, "Which country does this flag belong to?", R.drawable.rwanda,
            "Rwanda",
            "Nepal",
            "Saint Lucia",
            "Singapore",
            1
        )
        flagQuestionList.add(que8)

        //9
        val que9 = Question(
            9, "Which country does this flag belong to?", R.drawable.paraguay,
            "Vietnam",
            "Somalia",
            "Uzbekistan",
            "Paraguay",
            4
        )
        flagQuestionList.add(que9)

        //10
        val que10 = Question(
            10, "Which country does this flag belong to?", R.drawable.elsalvador,
            "Vatican City",
            "El Salvador",
            "Taiwan",
            "Senegal",
            2
        )
        flagQuestionList.add(que10)

        //11
        val que11 = Question(
            11, "Which country does this flag belong to?", R.drawable.northkorea,
            "South Korea",
            "North Korea",
            "East Korea",
            "West Korea",
            2
        )
        flagQuestionList.add(que11)

        //12
        val que12 = Question(
            12, "Which country does this flag belong to?", R.drawable.finland,
            "Romania",
            "Micronesia",
            "Finland",
            "Jamaica",
            3
        )
        flagQuestionList.add(que12)

        //13
        val que13 = Question(
            13, "Which country does this flag belong to?", R.drawable.sanmarino,
            "San Marino",
            "Kuwait",
            "Libya",
            "Maldives",
            1
        )
        flagQuestionList.add(que13)

        //14
        val que14 = Question(
            14, "Which country does this flag belong to?", R.drawable.kyrgyzstan,
            "Mali",
            "Niger",
            "Poland",
            "Kyrgyzstan",
            4
        )
        flagQuestionList.add(que14)

        //15
        val que15 = Question(
            15, "Which country does this flag belong to?", R.drawable.peru,
            "Philippines",
            "Romania",
            "Peru",
            "Uruguay",
            3
        )
        flagQuestionList.add(que15)


        return flagQuestionList
    }


}