package io.rsquare.nexacube.ui.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.rsquare.nexacube.ui.helper.NoRippleInteractionSource
import io.rsquare.nexacube.ui.theme.DisabledColor
import io.rsquare.nexacube.ui.theme.NexaCubeUILibraryTheme
import io.rsquare.nexacube.ui.theme.SelectedColor
import io.rsquare.nexacube.ui.theme.UnSelectedColor

@Composable
fun NexaCubeRadioButton(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean = true,
    text: String,
    icon: ImageVector? = null
) {
    Row (
        modifier = Modifier
            .padding(4.dp)
            .height(40.dp)
            .background(
                color = if (checked) Color(0xFFE3F6F0) else Color(0xFFF2F2F2),
                shape = RoundedCornerShape(size = 20.dp)
            )
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .clickable {
                if (enabled) {
                    onCheckedChange?.let { onChange -> onChange(!checked) }
                }
            }
//            .toggleable(
//                value = checked,
//                onValueChange = { onCheckedChange?.let { onChange -> onChange(!checked) } },
//                role = Role.RadioButton
//            )
        ,
        verticalAlignment = Alignment.CenterVertically
        ,

        ) {
        if (icon == null) {
            RadioButton(
                selected = checked,
                onClick = null,
                enabled = enabled,
                colors = RadioButtonDefaults.colors(
                    selectedColor = SelectedColor,
                    unselectedColor = UnSelectedColor,
                    disabledSelectedColor = DisabledColor,
                    disabledUnselectedColor = DisabledColor
                ),
                modifier = Modifier.padding(start = 10.dp),
            )
        } else {
            Image(
                imageVector = icon, contentDescription = null,
                modifier = Modifier.padding(start = 10.dp),
                colorFilter = ColorFilter.tint(
                    if (checked && enabled) {
                        SelectedColor
                    } else {
                        UnSelectedColor
                    }
                )
            )
        }

        Text(
            modifier = Modifier.padding(start = 4.dp, end = 10.dp),
            text = text,
            color = if (checked && enabled) {
                SelectedColor
            } else if (!enabled) {
                DisabledColor
            } else {
                UnSelectedColor
            }
        )
    }
}

@Preview
@Composable
fun RadioButtonPreview(
    enabled: Boolean = true,
    icon: ImageVector? = null
) {
    NexaCubeUILibraryTheme {
        NexaCubeRadioButton(
            checked = true,
            onCheckedChange = {},
            text = "Custom RadioButton Sample",
            enabled = enabled,
            icon = icon
        )
    }
}

@Preview
@Composable
fun DisableRadioButtonPreview() {
    RadioButtonPreview(enabled = false)
}

@Preview
@Composable
fun IconRadioButtonPreview() {
    RadioButtonPreview(icon = Icons.Filled.Person)
}

@Preview
@Composable
fun DisabledIconRadioButtonPreview() {
    RadioButtonPreview(icon = Icons.Filled.Person, enabled = false)
}