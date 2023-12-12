package com.example.sirius.tools

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import java.time.Year

/**
 * Calculates the age based on the provided birth date.
 *
 * @param birthDate The birth date in the format "YYYY-MM-DD".
 * @return The calculated age.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun calculateAge(birthDate: String): Int {
    val birthYear = birthDate.substring(0, 4).toInt()
    val currentYear = Year.now().value

    return (currentYear - birthYear)
}

/**
 * Formats a numerical value with the appropriate singular or plural label.
 *
 * @param value The numerical value to be formatted.
 * @param singular The singular label to be used when the value is equal to 1.
 * @param plural The plural label to be used when the value is not equal to 1.
 * @return The formatted string.
 */
private fun formatPluralizedAge(value: Int, singular: String, plural: String): String {
    return if (value == 1) "$value $singular" else "$value $plural"
}

/**
 * Builds a formatted age text based on the provided age and birth date.
 *
 * @param age The age value.
 * @param birthDate The birth date in the format "YYYY-MM-DD".
 * @param isShorten Indicates whether to use shortened labels (e.g., "mo" for months).
 * @return The formatted age text.
 */
@Composable
fun buildAnAgeText(age: Int, birthDate: String, isShorten: Boolean = false): String {
    return when {
        age == 0 -> formatPluralizedAge(birthDate.substring(6, 7).toInt(), if (!isShorten) "month" else "(mo)", if (!isShorten) "months" else "(mo)")
        else -> formatPluralizedAge(age, if (!isShorten) "year" else "", if (!isShorten) "years" else "")
    }
}