package com.example.quizapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf("Quel type de modèle Hary Lala RABENAMANA a conçu pour classifier des images ?",
                                    "Qui a peint la Joconde ?",
                                    "Quel événement majeur a marqué le début de l'année 2025 dans le domaine de la technologie ?",
                                    "Quelle avancée scientifique majeure a été annoncée en janvier 2025 ?")

    private val options = arrayOf(arrayOf("RNN", "CNN", "SVM"),
                                arrayOf("Vincent van Gogh", "Claude Monet", "Léonard de Vinci"),
                                arrayOf("Fusion de deux géants de la tech", "Introduction d'une nouvelle réglementation sur l'IA", "Lancement du premier smartphone quantique"),
                                arrayOf("Vaccin universel contre la grippe", "Téléportation quantique humaine", "Fusion nucléaire contrôlée")
    )

    private val correctAnswers = arrayOf(1, 2, 0, 2)

    private var currentQuestionIndex = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestions()

        binding.option1Button.setOnClickListener{
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }
        binding.restartButton.setOnClickListener{
            restartQuiz()
        }
    }

    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        Toast.makeText(this, "Votre score : $score sur ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled = true
    }

    private fun displayQuestions(){
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1Button.text = options[currentQuestionIndex][0]
        binding.option2Button.text = options[currentQuestionIndex][1]
        binding.option3Button.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex )
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < questions.size) {
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestions()}, 1000)
        } else {
            showResults()
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestions()
        binding.restartButton.isEnabled = false
    }
}