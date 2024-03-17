package io.rsquare.nexacube.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.rsquare.nexacube.ui.helper.NoRippleInteractionSource
import io.rsquare.nexacube.ui.theme.CheckMarkColor
import io.rsquare.nexacube.ui.theme.DisabledColor
import io.rsquare.nexacube.ui.theme.NexaCubeUILibraryTheme
import io.rsquare.nexacube.ui.theme.SelectedColor
import io.rsquare.nexacube.ui.theme.UnSelectedColor

@Composable
fun NexaCubeCheckBox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean = true,
    text: String
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
//                role = Role.Checkbox
//            )
        ,
        verticalAlignment = Alignment.CenterVertically
        ,

        ) {
        Checkbox(
            checked = checked,
            onCheckedChange = null,
            enabled = enabled,
            colors = CheckboxDefaults.colors(
                checkedColor = SelectedColor,
                uncheckedColor = UnSelectedColor,
                checkmarkColor = CheckMarkColor,
                disabledCheckedColor = DisabledColor,
                disabledUncheckedColor = DisabledColor
            ),
            modifier = Modifier.padding(start = 10.dp),
        )

        Text(
            modifier = Modifier.padding(start = 4.dp, end = 16.dp),
            text = text,
            color = if (checked && enabled) {
                SelectedColor
            } else if (!enabled) {
                DisabledColor
            } else {
                UnSelectedColor
            },
        )
    }
}

@Preview
@Composable
fun CheckBoxPreview(
    enabled: Boolean = true
) {
    NexaCubeUILibraryTheme {
        NexaCubeCheckBox(
            checked = true,
            onCheckedChange = {},
            text = "Custom CheckBox Sample",
            enabled = enabled
        )
    }
}

@Preview
@Composable
fun DisableCheckBoxPreview() {
    CheckBoxPreview(enabled = false)
}