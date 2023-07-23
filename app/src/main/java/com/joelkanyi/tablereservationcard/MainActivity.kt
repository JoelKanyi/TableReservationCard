package com.joelkanyi.tablereservationcard

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joelkanyi.tablereservationcard.ui.theme.LightGrey
import com.joelkanyi.tablereservationcard.ui.theme.TableReservationCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TableReservationCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFf6886e),
                ) {
                    var numberOfPeople by remember { mutableStateOf(0) }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp),
                        shape = MaterialTheme.shapes.large,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.45f)
                                    .background(LightGrey),
                            ) {
                                BoxWithConstraints(
                                    modifier = Modifier.padding(32.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(100.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.surface),
                                    )
                                    if (numberOfPeople >= 1) {
                                        Image(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .rotate(180f)
                                                .offset(y = (-50).dp),
                                            painter = painterResource(id = R.drawable.person_green),
                                            contentDescription = null,
                                        )
                                    }
                                    if (numberOfPeople >= 2) {
                                        Image(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .rotate(if (numberOfPeople == 3) -60f else 0f)
                                                .offset(y = (-50).dp),
                                            painter = painterResource(id = R.drawable.person_blue),
                                            contentDescription = null,
                                        )
                                    }
                                    if (numberOfPeople >= 3) {
                                        Image(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .rotate(if (numberOfPeople == 3) 70f else 90f)
                                                .offset(y = (-50).dp),
                                            painter = painterResource(id = R.drawable.person_purple),
                                            contentDescription = null,
                                        )
                                    }
                                    if (numberOfPeople >= 4) {
                                        Image(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .rotate(-90f)
                                                .offset(y = (-50).dp),
                                            painter = painterResource(id = R.drawable.person_red),
                                            contentDescription = null,
                                        )
                                    }
                                }
                            }

                            Column(
                                modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                            ) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Table Reservation",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontSize = 14.sp,
                                    ),
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = "Party of",
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 18.sp,
                                        ),
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        CircleButton(
                                            onClick = {
                                                if (numberOfPeople > 0) {
                                                    numberOfPeople -= 1
                                                }
                                            },
                                            icon = Icons.Default.Remove,
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))

                                        Text(
                                            text = "$numberOfPeople",
                                            style = MaterialTheme.typography.headlineMedium.copy(
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 20.sp,
                                            ),
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))

                                        CircleButton(
                                            onClick = {
                                                if (numberOfPeople < 4) {
                                                    numberOfPeople += 1
                                                }
                                            },
                                            icon = Icons.Default.Add,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun CircleButton(
        onClick: () -> Unit,
        icon: ImageVector,
    ) {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(LightGrey)
                .clickable {
                    onClick.withSound(context)()
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = icon,
                contentDescription = null,
            )
        }
    }
}

fun (() -> Unit).withSound(context: Context): () -> Unit = {
    (context.getSystemService(Context.AUDIO_SERVICE) as AudioManager)
        .playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD)
    this()
}
