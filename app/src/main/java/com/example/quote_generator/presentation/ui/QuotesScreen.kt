package com.example.quote_generator.presentation.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
    modifier: Modifier = Modifier,
    viewModel: QuotesViewModel = hiltViewModel(),
    onNavigateToList: () -> Unit,
) {
    val quote by viewModel.currentQuote.collectAsState()
    var isAnimating by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigateToList,
                icon = {
                    Icon(Icons.Default.List, contentDescription = null)
                },
                text = {
                    Text("Все цитаты")
                },
                elevation = FloatingActionButtonDefaults.elevation()
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = MaterialTheme.shapes.large,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                isAnimating = true
                                viewModel.onNextQuoteClick()
                            }
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "Новая цитата",
                                modifier = Modifier.scale(if (isAnimating) 1.1f else 1f),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.ic_format_quote),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = quote.text,
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 1.3.em
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = quote.author,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }
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



