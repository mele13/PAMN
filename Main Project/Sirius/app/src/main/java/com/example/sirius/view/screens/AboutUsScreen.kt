import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sirius.R
import com.example.sirius.ui.theme.SiriusTheme

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun Paragraph(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
    )
}

@Composable
fun RoundedImage(@DrawableRes imageRes: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = modifier
            .fillMaxHeight()
            .width(100.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun LocationCard(location: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        border = BorderStroke(width = 0.dp, color = Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp) // Reduce vertical space
        ) {
            Text(
                text = "Location",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = location,
                style = MaterialTheme.typography.labelLarge,
            )

            // Mapa de Google
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium), // Edge rounding
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.location_image),
                    contentDescription = null,
                    modifier = Modifier
                        .widthIn(0.8f.dp) // Adjust the width as needed
                        .aspectRatio(1f) // Maintain aspect ratio
                )
            }
        }
    }
}

@Composable
fun AboutUsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            SectionTitle("About Us")
            Paragraph("Welcome to our shelter! We are dedicated to providing a safe and caring environment for animals in need.")
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RoundedImage(imageRes = R.drawable.dog1, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(8.dp)) // Add space between images
                RoundedImage(imageRes = R.drawable.dog1, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(8.dp)) // Add space between images
                RoundedImage(imageRes = R.drawable.dog1, modifier = Modifier.weight(1f))
            }
        }

        item {
            LocationCard("Our shelter is located at XYZ Street, City, Country.")
        }

        item {
            SectionTitle("Schedule")
            Paragraph("Monday - Friday: 9 AM - 6 PM\nSaturday - Sunday: 10 AM - 4 PM")
        }

        item {
            SectionTitle("Shelter's Data")
            Paragraph("Established in 2010, our shelter has rescued and rehomed thousands of animals. We prioritize their well-being and work towards a future with no homeless pets.")
        }

        item {
            SectionTitle("Contact Information")
            Paragraph("Email: info@shelter.org\nPhone: +1 123 456 7890")
        }
    }
}

@Preview
@Composable
fun AboutUsScreenPreview() {
    SiriusTheme {
        Surface {
            AboutUsScreen()
        }
    }
}