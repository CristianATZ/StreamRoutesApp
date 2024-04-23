package net.streamroutes.sreamroutesapp.ui.routes_screens.menu.help

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.streamroutes.sreamroutesapp.R
import net.streamroutes.sreamroutesapp.utils.MyViewModel


data class HelpItem(
    @StringRes val name: Int,
    @StringRes val desc: Int,
    val configuration: HelpSelection,
    val composable: @Composable () -> Unit
)

enum class HelpSelection{
    NONE,
    COMMENTS,
    CONTACT,
    ABOUT
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HelpScreen(myViewModel: MyViewModel = MyViewModel(), onBack: () -> Unit) {

    var selection by remember {
        mutableStateOf(HelpSelection.NONE)
    }

    val helpItems = listOf(
        HelpItem(name = R.string.lblCommentTitle, desc = R.string.lblCommentSubtitle,configuration = HelpSelection.COMMENTS, composable = { CommentOptions() }),
        HelpItem(name = R.string.lblContactTitle, desc = R.string.lblContactSubtitle,configuration = HelpSelection.CONTACT, composable = { ContactOptions() }),
        HelpItem(name = R.string.lblAboutTitle, desc = R.string.lblAboutSubtitle,configuration = HelpSelection.ABOUT, composable = { AboutOptions() })
    )

    Scaffold(
        topBar = { HelpTopBar(onBack = onBack) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            helpItems.forEach { item ->
                Options(name = item.name, desc = item.desc, isSelected = selection == item.configuration) {
                    selection = if(selection == item.configuration)
                        HelpSelection.NONE
                    else
                        item.configuration
                }

                if(selection == item.configuration){
                    item.composable()
                    Divider()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpTopBar(onBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.lblHelpTopbar)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun Options(
    @StringRes name: Int,
    @StringRes desc: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val background = if(isSelected){
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.background
    }

    val textColor = if(isSelected){
        MaterialTheme.colorScheme.onSurfaceVariant
    } else {
        MaterialTheme.colorScheme.onBackground
    }

    val fontWeight = if(isSelected){
        FontWeight.Bold
    } else {
        FontWeight.Normal
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(background)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Text(
                text = stringResource(id = name), // texto
                style = typography.bodyLarge,
                color = textColor,
                fontWeight = fontWeight
            )

            Text(
                text = stringResource(id = desc), // texto
                style = typography.bodySmall,
                color = textColor,
                fontWeight = fontWeight
            )
        }
    }
}

@Composable
private fun CommentOptions() {
    CommentScreen()
}

@Composable
private fun ContactOptions() {
    ContactScreen()
}

@Composable
private fun AboutOptions() {
    AboutScreen()
}