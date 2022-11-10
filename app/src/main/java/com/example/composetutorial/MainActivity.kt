
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.R
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import com.example.composetutorial.ui.theme.shapeScheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                Surface(modifier = Modifier.fillMaxSize()) { //Estilo de la letra
                    MessageCard(Message("Android", "Jetpack Compose"))
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.camposoto_principal),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp) //Tamaño de la imagen
                .clip(CircleShape) //Forma de la imagen en circulo
                .border(
                    1.5.dp,
                    MaterialTheme.colorScheme.primary,
                    CircleShape
                ) //Añade un borde redondo que rodea la imagen
        )
        Spacer(modifier = Modifier.width(8.dp)) //Espacio que hay entre una columna y otra

        var isExpanded by remember { mutableStateOf(false) } //Por defecto, el mensaje no esta expandido
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        ) //El color que se usara es el que tiene el movil por defecto en la superficie
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary, //Pone un color al titulo
                style = MaterialTheme.typography.titleSmall
            ) //Pone el tamaño de la letra que se vea como un titulo pequeño
            Spacer(modifier = Modifier.height(4.dp)) //Espacio entre autor y el cuerpo
            Surface(shape = MaterialTheme.shapeScheme.medium, shadowElevation = 1.dp,color = surfaceColor,modifier = Modifier.animateContentSize().padding(1.dp)) { //Muestra un rectangulo que rodea el texto
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),maxLines = if (isExpanded) Int.MAX_VALUE else 1, //Si se expande con un click se pondra en el maximo valor para que se pueda visualizar el mensaje
                    style = MaterialTheme.typography.bodyMedium
                ) //Pone el tamaño de la letra que se vea como un cuerpo mediano
            }
        }
    }
}
@Preview()
@Preview( //Esta Preview se ve los mensajes dependiendo de si el movil esta en Dark Mode o no, y si quieres
    //Que se cambie el tema del programa entonces tienes que irte a theme.xml
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

@Preview
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}


/*@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Surface {
            MessageCard(
                msg = Message("Rafa", "Echa un vistazo Jet Pack Compose, es divertido!")
            )
        }
    }
}*/

  