package com.reishandy.grisaiacharacters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.reishandy.grisaiacharacters.model.Character
import com.reishandy.grisaiacharacters.model.Characters
import com.reishandy.grisaiacharacters.ui.theme.GrisaiaCharactersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GrisaiaCharactersTheme {
                GrisaiaApp()
            }
        }
    }
}

@Composable
fun GrisaiaApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.statusBarsPadding(),
            topBar = { GrisaiaTopBar() }
        ) { it ->
            LazyColumn(
                contentPadding = it
            ) {
                items(Characters.characters) { character ->
                    GrisaiaCard(character)
                }
            }
        }
    }
}

@Composable
fun GrisaiaTopBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Composable
fun GrisaiaCard(
    character: Character,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.inverseSurface
        else MaterialTheme.colorScheme.surface,
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(
            horizontal = dimensionResource(R.dimen.padding),
            vertical = dimensionResource(R.dimen.padding_small)
            )
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        onClick = { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.card_elevation))
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding)),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(character.characterType),
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = stringResource(character.nameRes),
                style = MaterialTheme.typography.headlineLarge
            )

            Image(
                painter = painterResource(character.imageRes),
                contentDescription = stringResource(character.nameRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )


            if (expanded) {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding)))

                Text(
                    text = stringResource(character.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GrisaiaPreview() {
    GrisaiaCharactersTheme(darkTheme = false) {
        GrisaiaApp()
    }
}