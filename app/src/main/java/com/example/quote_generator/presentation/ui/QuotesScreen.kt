package com.example.quote_generator.presentation.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.quote_generator.presentation.QuotesViewModel
import com.example.quote_generator.R
import kotlinx.coroutines.delay
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuotesScreen(
    viewModel: QuotesViewModel = hiltViewModel(),
    onNavigateToList: () -> Unit,
    modifier: Modifier = Modifier
) {
    val quote by viewModel.currentQuote.collectAsState()
    var isAnimating by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF667eea),
                            Color(0xFF764ba2)
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(800)) + scaleIn(
                    initialScale = 0.8f,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                ),
                exit = fadeOut() + scaleOut(targetScale = 0.8f)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.88f)
                        .aspectRatio(3.2f / 4.5f),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 28.dp,
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(36.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_format_quote),
                            contentDescription = null,
                            modifier = Modifier
                                .size(44.dp)
                                .padding(bottom = 20.dp),
                            tint = Color(0xFF4A90E2)
                        )

                        Text(
                            text = quote.text,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.W500,
                                lineHeight = 1.4.em
                            ),
                            textAlign = TextAlign.Center,
                            color = Color(0xFF1A1A1A),
                            modifier = Modifier.padding(bottom = 28.dp)
                        )

                        Text(
                            text = "— ${quote.author}",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.W600
                            ),
                            color = Color(0xFF4A90E2)
                        )
                    }
                }
            }
        }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(32.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(
                    visible = true,
                    enter = scaleIn() + fadeIn()
                ) {
                    FloatingActionButton(
                        onClick = onNavigateToList,
                        modifier = Modifier.size(64.dp),
                        containerColor = Color.White,
                        contentColor = Color(0xFF667eea),
                        elevation = FloatingActionButtonDefaults.elevation()
                    ) {
                        Icon(
                            Icons.Default.List,
                            contentDescription = "Список цитат",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                FloatingActionButton(
                    onClick = {
                        isAnimating = true
                        viewModel.onNextQuoteClick()
                    },
                    modifier = Modifier
                        .size(72.dp)
                        .scale(if (isAnimating) 1.1f else 1f),
                    containerColor = Color(0xFF50C878),
                    elevation = FloatingActionButtonDefaults.elevation()
                ) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = "Новая цитата",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        LaunchedEffect(isAnimating) {
            if (isAnimating) {
                delay(150)
                isAnimating = false
            }
        }
    }
