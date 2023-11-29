import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sirius.R
import com.example.sirius.ui.theme.SiriusTheme
import com.example.sirius.viewmodel.ContactsViewModel

@SuppressLint("RememberReturnType")
@Composable
fun DonationsScreen() {
    val viewModelContacts = remember { ContactsViewModel() }

    Log.d("DonationsScreen", "DonationsScreen recomposed")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            DonationsText(
                textResourceId = R.string.donationsTitle,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
        item {
            DonationsText(
                textResourceId = R.string.donationsText,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp)) // Ajusta la altura del Spacer según sea necesario
        }
        item {
            DonationButton(
                onClick = {
                    viewModelContacts.changeBackgroundColor()
                },
                imageResIdLeft = R.drawable.paypal_logo,
                buttonText = "Donate with PayPal"
            )
        }

        item {
            DonationButton(
                onClick = { /* Handle debit/credit card donation */ },
                imageResIdLeft = R.drawable.mastercard_logo,
                buttonText = "Donate with Debit or Credit Card"
            )
        }
        item {
            DonationButton(
                onClick = { /* Handle Bizum donation */ },
                imageResIdLeft = R.drawable.bizum_logo,
                buttonText = "Donate with Bizum"
            )
        }
        item {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            DonationsText(
                textResourceId = R.string.donationsEnd,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun DonationButton(
    onClick: () -> Unit,
    imageResIdLeft: Int,
    buttonText: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp) // Establece una altura mínima
            .wrapContentHeight(Alignment.CenterVertically) // Hace que la altura se ajuste al contenido
            .padding(bottom = 16.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFFFA500))
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DonationsImage(imageResIdLeft)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                buttonText,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun DonationsImage(imageId: Int, size: Dp = 40.dp) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
    )
}

@Composable
fun DonationsText(@StringRes textResourceId: Int, style: androidx.compose.ui.text.TextStyle, textAlign: TextAlign) {
    Text(
        text = stringResource(id = textResourceId),
        style = style,
        textAlign = textAlign
    )
}

@Preview
@Composable
fun DonationsScreenPreview() {
    SiriusTheme {
        Surface {
            DonationsScreen()
        }
    }
}
