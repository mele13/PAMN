import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sirius.R
import com.example.sirius.ui.theme.SiriusTheme

@Composable
fun DonationsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DonationsText(
            textResourceId = R.string.donationsTitle,
            style = MaterialTheme.typography.headlineSmall
        )
        DonationsText(
            textResourceId = R.string.donationsText,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.weight(0.2f))
        DonationButton(
            onClick = { /* Handle PayPal donation */ },
            imageResIdLeft = R.drawable.paypal_logo,
            buttonText = "Donate with PayPal"
        )
        DonationButton(
            onClick = { /* Handle debit/credit card donation */ },
            imageResIdLeft = R.drawable.mastercard_logo,
            buttonText = "Donate with Debit or Credit Card"
        )
        DonationButton(
            onClick = { /* Handle Bizum donation */ },
            imageResIdLeft = R.drawable.bizum_logo,
            buttonText = "Donate with Bizum"
        )
        Spacer(modifier = Modifier.weight(1f))
        DonationsText(
            textResourceId = R.string.donationsEnd,
            style = MaterialTheme.typography.bodyLarge
        )
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
            .height(80.dp)
            .padding(bottom = 16.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFFFA500))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
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
fun DonationsText(@StringRes textResourceId: Int, style: TextStyle) {
    Text(
        text = stringResource(id = textResourceId),
        style = style,
        color = Color.Black
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
