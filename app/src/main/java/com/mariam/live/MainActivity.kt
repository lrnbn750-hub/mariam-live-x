package com.mariam.live

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Purple = Color(0xFF7048FF)
private val Background = Color(0xFFF6F5FA)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MariamApp() }
    }
}

@Composable
fun MariamApp() {
    var selected by remember { mutableIntStateOf(0) }

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Purple,
            background = Background,
            surface = Color.White
        )
    ) {
        Scaffold(
            containerColor = Background,
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    val items = listOf(
                        Icons.Default.Home to "الرئيسية",
                        Icons.Default.Groups to "الغرف",
                        Icons.Default.Chat to "المحادثات",
                        Icons.Default.Person to "حسابي"
                    )
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selected == index,
                            onClick = { selected = index },
                            icon = { Icon(item.first, contentDescription = item.second) },
                            label = { Text(item.second) }
                        )
                    }
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 18.dp)
            ) {
                item {
                    Spacer(Modifier.height(22.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Mariam Live", fontSize = 27.sp, fontWeight = FontWeight.Bold, color = Purple)
                            Text("عالمك، صوتك، وأصدقاؤك", color = Color.Gray, fontSize = 14.sp)
                        }
                        Surface(shape = CircleShape, color = Color.White) {
                            IconButton(onClick = {}) {
                                Icon(Icons.Default.Notifications, contentDescription = "الإشعارات", tint = Purple)
                            }
                        }
                    }
                    Spacer(Modifier.height(24.dp))
                    Text("مرحباً بك 👋", fontSize = 23.sp, fontWeight = FontWeight.Bold)
                    Text("اكتشف الأصدقاء والغرف الصوتية", color = Color.Gray)
                    Spacer(Modifier.height(20.dp))
                    Text("الغرف الصوتية المباشرة", fontSize = 19.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                }

                items(5) { index ->
                    RoomCard(index + 1)
                }

                item {
                    Spacer(Modifier.height(18.dp))
                    Text("Mariam Live • الإصدار الأول", color = Color.Gray, modifier = Modifier.align(Alignment.CenterHorizontally))
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun RoomCard(number: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(55.dp).background(
                    Purple.copy(alpha = 0.12f), RoundedCornerShape(16.dp)
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Headphones, contentDescription = null, tint = Purple, modifier = Modifier.size(30.dp))
            }
            Spacer(Modifier.width(13.dp))
            Column(Modifier.weight(1f)) {
                Text("الغرفة الصوتية $number", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("انضم وتحدث مع الأصدقاء", color = Color.Gray, fontSize = 13.sp)
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text("دخول")
            }
        }
    }
}
