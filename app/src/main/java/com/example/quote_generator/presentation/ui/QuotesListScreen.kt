package com.example.quote_generator.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quote_generator.domain.model.Quote
import com.example.quote_generator.presentation.QuotesViewModel
import com.example.quote_generator.R
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesListScreen(
    onBackClick: () -> Unit,
    viewModel: QuotesViewModel = hiltViewModel(),
) {
    val quotes by viewModel.allQuotes.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Все цитаты (${quotes.size})") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Назад"
                        )
                    }
                },
                actions = {
                    val favoritesCount = quotes.count { it.isFavorite }
                    if (favoritesCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    Text(favoritesCount.toString())
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_favorite),
                                contentDescription = "Избранные"
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(quotes, key = { it.id }) { quote ->
                QuoteItemCard(
                    quote = quote,
                    onFavoriteClick = { id -> viewModel.onToggleFavorite(id) }
                )
            }
        }
    }
}

@Composable
fun QuoteItemCard(
    quote: Quote,
    onFavoriteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(
                        color = Color(0xFF4A90E2).copy(alpha = 0.12f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = quote.author.firstOrNull()?.uppercase() ?: "?",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF4A90E2),
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = quote.text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF0F172A),
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "— ${quote.author}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF4A90E2),
                    fontWeight = FontWeight.Medium
                )
            }

            IconButton(onClick = { onFavoriteClick(quote.id) }) {
                Icon(
                    painter = painterResource(
                        if (quote.isFavorite) R.drawable.ic_favorite_check
                        else R.drawable.ic_favorite
                    ),
                    contentDescription = "Избранное",
                    tint = if (quote.isFavorite) Color(0xFFEF4444) else Color(0xFF94A3B8)
                )
            }
        }
    }
}