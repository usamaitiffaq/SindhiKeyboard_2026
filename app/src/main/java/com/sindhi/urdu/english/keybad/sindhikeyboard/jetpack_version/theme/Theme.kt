package com.sindhi.urdu.english.keybad.sindhikeyboard.jetpack_version.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    background = KeyboardBackground_dark,
    tertiary = KeyBackground_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_dark,
    onSurfaceVariant = KeyTextColor_dark,
    tertiaryContainer = SpecialKeyBackground_dark,
    onTertiaryContainer = Popup_dark,
    onErrorContainer = Ripple_dark,
    errorContainer = SpecialTextColor_dark,
    inverseOnSurface = KeyboardBackground_dark,
    inverseSurface = KeyboardBackground_dark,
    error = SpecialTextColor_dark
)

private val LightColorScheme = lightColorScheme(
    background = KeyboardBackground_light,
    tertiary = KeyBackground_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_light,
    onSurfaceVariant = KeyTextColor_light,
    tertiaryContainer = SpecialKeyBackground_light,
    onTertiaryContainer = Popup_light,
    onErrorContainer = Ripple_light,
    errorContainer = SpecialTextColor_light,
    inverseSurface = KeyboardBackground_light,
    inverseOnSurface = KeyboardBackground_light,
    error = SpecialTextColor_light
)

private val SelColorScheme = darkColorScheme(
    background = KeyboardBackground_sel,
    tertiary = KeyBackground_sel,
    secondary = boxColor,
    onTertiary = KeyTextColor_sel,
    onSurfaceVariant = KeyTextColor_sel,
    tertiaryContainer = SpecialKeyBackground_sel,
    onTertiaryContainer = Popup_sel,
    onErrorContainer = Ripple_sel,
    errorContainer = SpecialTextColor_sel,
    inverseOnSurface = GradientColor2_sel,
    inverseSurface = GradientColor_sel,
    error = CandidateIconColor_sel
)

private val ReaColorScheme = darkColorScheme(
    background = KeyboardBackground_rea,
    tertiary = KeyBackground_rea,
    secondary = boxColor,
    onTertiary = KeyTextColor_rea,
    onSurfaceVariant = KeyTextColor_rea,
    tertiaryContainer = SpecialKeyBackground_rea,
    onTertiaryContainer = Popup_rea,
    onErrorContainer = Ripple_rea,
    errorContainer = SpecialTextColor_rea,
    inverseOnSurface = GradientColor2_rea,
    inverseSurface = GradientColor_rea,
    error = CandidateIconColor_rea
)

private val NebulaColorScheme = darkColorScheme(
    background = KeyboardBackground_nebula,
    tertiary = KeyBackground_nebula,
    secondary = boxColor,
    onTertiary = KeyTextColor_nebula,
    onSurfaceVariant = KeyTextColor_nebula,
    tertiaryContainer = SpecialKeyBackground_nebula,
    onTertiaryContainer = Popup_nebula,
    onErrorContainer = Ripple_nebula,
    errorContainer = SpecialTextColor_nebula,
    inverseOnSurface = GradientColor2_nebula,
    inverseSurface = GradientColor_nebula,
    error = CandidateIconColor_nebula
)

private val ThanosColorScheme = darkColorScheme(
    background = KeyboardBackground_thanos,
    tertiary = KeyBackground_thanos,
    secondary = boxColor,
    onTertiary = KeyTextColor_thanos,
    onSurfaceVariant = KeyTextColor_thanos,
    tertiaryContainer = SpecialKeyBackground_thanos,
    onTertiaryContainer = Popup_thanos,
    onErrorContainer = Ripple_thanos,
    errorContainer = SpecialTextColor_thanos,
    inverseOnSurface = GradientColor2_thanos,
    inverseSurface = GradientColor_thanos,
    error = CandidateIconColor_thanos
)

private val TempleColorScheme = darkColorScheme(
    background = KeyboardBackground_temple,
    tertiary = KeyBackground_temple,
    secondary = boxColor,
    onTertiary = KeyTextColor_temple,
    onSurfaceVariant = KeyTextColor_temple,
    tertiaryContainer = SpecialKeyBackground_temple,
    onTertiaryContainer = Popup_temple,
    onErrorContainer = Ripple_temple,
    errorContainer = SpecialTextColor_temple,
    inverseOnSurface = GradientColor2_temple,
    inverseSurface = GradientColor_temple,
    error = CandidateIconColor_temple
)

private val MemarianiColorScheme = darkColorScheme(
    background = KeyboardBackground_Memariani,
    tertiary = KeyBackground_Memariani,
    secondary = boxColor,
    onTertiary = KeyTextColor_Memariani,
    onSurfaceVariant = KeyTextColor_Memariani,
    tertiaryContainer = SpecialKeyBackground_Memariani,
    onTertiaryContainer = Popup_Memariani,
    onErrorContainer = Ripple_Memariani,
    errorContainer = SpecialTextColor_Memariani,
    inverseOnSurface = GradientColor2_Memariani,
    inverseSurface = GradientColor_Memariani,
    error = CandidateIconColor_Memariani
)

private val MetallicToadColorScheme = darkColorScheme(
    background = KeyboardBackground_MetallicToad,
    tertiary = KeyBackground_MetallicToad,
    secondary = boxColor,
    onTertiary = KeyTextColor_MetallicToad,
    onSurfaceVariant = CandidateTextColor_MetallicToad,
    tertiaryContainer = SpecialKeyBackground_MetallicToad,
    onTertiaryContainer = Popup_MetallicToad,
    onErrorContainer = Ripple_MetallicToad,
    errorContainer = SpecialTextColor_MetallicToad,
    inverseOnSurface = GradientColor2_MetallicToad,
    inverseSurface = GradientColor_MetallicToad,
    error = CandidateIconColor_MetallicToad
)

private val SublimeLightColorScheme = darkColorScheme(
    background = KeyboardBackground_SublimeLight,
    tertiary = KeyBackground_SublimeLight,
    secondary = boxColor,
    onTertiary = KeyTextColor_SublimeLight,
    onSurfaceVariant = CandidateTextColor_SublimeLight,
    tertiaryContainer = SpecialKeyBackground_SublimeLight,
    onTertiaryContainer = Popup_SublimeLight,
    onErrorContainer = Ripple_SublimeLight,
    errorContainer = SpecialTextColor_SublimeLight,
    inverseOnSurface = GradientColor2_SublimeLight,
    inverseSurface = GradientColor_SublimeLight,
    error = CandidateIconColor_SublimeLight
)

private val LemonTwistColorScheme = darkColorScheme(
    background = KeyboardBackground_LemonTwist,
    tertiary = KeyBackground_LemonTwist,
    secondary = boxColor,
    onTertiary = KeyTextColor_LemonTwist,
    onSurfaceVariant = KeyTextColor_LemonTwist,
    tertiaryContainer = SpecialKeyBackground_LemonTwist,
    onTertiaryContainer = Popup_LemonTwist,
    onErrorContainer = Ripple_LemonTwist,
    errorContainer = SpecialTextColor_LemonTwist,
    inverseOnSurface = GradientColor2_LemonTwist,
    inverseSurface = GradientColor_LemonTwist,
    error = CandidateIconColor_LemonTwist
)

private val SindhiDefaultColorScheme = darkColorScheme(
    background = KeyboardBackground_SindhiDefault,
    tertiary = KeyBackground_SindhiDefault,
    secondary = boxColor,
    onTertiary = KeyTextColor_SindhiDefault,
    onSurfaceVariant = KeyTextColor_SindhiDefault,
    tertiaryContainer = SpecialKeyBackground_SindhiDefault,
    onTertiaryContainer = Popup_SindhiDefault,
    onErrorContainer = Ripple_SindhiDefault,
    errorContainer = SpecialTextColor_SindhiDefault,
    inverseOnSurface = GradientColor2_SindhiDefault,
    inverseSurface = GradientColor_SindhiDefault,
    error = CandidateIconColor_SindhiDefault
)

private val SolidSimpleColorScheme = darkColorScheme(
    background = KeyboardBackground_SolidSimple,
    tertiary = KeyBackground_SolidSimple,
    secondary = boxColor,
    onTertiary = KeyTextColor_SolidSimple,
    onSurfaceVariant = KeyTextColor_SolidSimple,
    tertiaryContainer = SpecialKeyBackground_SolidSimple,
    onTertiaryContainer = Popup_SolidSimple,
    onErrorContainer = Ripple_SolidSimple,
    errorContainer = SpecialTextColor_SolidSimple,
    inverseOnSurface = GradientColor2_SolidSimple,
    inverseSurface = GradientColor_SolidSimple,
    error = CandidateIconColor_SolidSimple
)

private val Gradient1ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient1,
    tertiary = KeyBackground_gradient1,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient1,
    onSurfaceVariant = KeyTextColor_gradient1,
    tertiaryContainer = SpecialKeyBackground_gradient1,
    onTertiaryContainer = Popup_gradient1,
    onErrorContainer = Ripple_gradient1,
    errorContainer = SpecialTextColor_gradient1,
    inverseOnSurface = GradientColor2_gradient1,
    inverseSurface = GradientColor_gradient1,
    error = CandidateIconColor_gradient1
)

private val Gradient2ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient2,
    tertiary = KeyBackground_gradient2,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient2,
    onSurfaceVariant = KeyTextColor_gradient2,
    tertiaryContainer = SpecialKeyBackground_gradient2,
    onTertiaryContainer = Popup_gradient2,
    onErrorContainer = Ripple_gradient2,
    errorContainer = SpecialTextColor_gradient2,
    inverseOnSurface = GradientColor2_gradient2,
    inverseSurface = GradientColor_gradient2,
    error = CandidateIconColor_gradient2
)

private val Gradient3ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient3,
    tertiary = KeyBackground_gradient3,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient3,
    onSurfaceVariant = KeyTextColor_gradient3,
    tertiaryContainer = SpecialKeyBackground_gradient3,
    onTertiaryContainer = Popup_gradient3,
    onErrorContainer = Ripple_gradient3,
    errorContainer = SpecialTextColor_gradient3,
    inverseOnSurface = GradientColor2_gradient3,
    inverseSurface = GradientColor_gradient3,
    error = CandidateIconColor_gradient3
)

private val Gradient4ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient4,
    tertiary = KeyBackground_gradient4,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient4,
    onSurfaceVariant = KeyTextColor_gradient4,
    tertiaryContainer = SpecialKeyBackground_gradient4,
    onTertiaryContainer = Popup_gradient4,
    onErrorContainer = Ripple_gradient4,
    errorContainer = SpecialTextColor_gradient4,
    inverseOnSurface = GradientColor2_gradient4,
    inverseSurface = GradientColor_gradient4,
    error = CandidateIconColor_gradient4
)

private val Gradient5ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient5,
    tertiary = KeyBackground_gradient5,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient5,
    onSurfaceVariant = KeyTextColor_gradient5,
    tertiaryContainer = SpecialKeyBackground_gradient5,
    onTertiaryContainer = Popup_gradient5,
    onErrorContainer = Ripple_gradient5,
    errorContainer = SpecialTextColor_gradient5,
    inverseOnSurface = GradientColor2_gradient5,
    inverseSurface = GradientColor_gradient5,
    error = CandidateIconColor_gradient5
)

private val Gradient6ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient6,
    tertiary = KeyBackground_gradient6,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient6,
    onSurfaceVariant = KeyTextColor_gradient6,
    tertiaryContainer = SpecialKeyBackground_gradient6,
    onTertiaryContainer = Popup_gradient6,
    onErrorContainer = Ripple_gradient6,
    errorContainer = SpecialTextColor_gradient6,
    inverseOnSurface = GradientColor2_gradient6,
    inverseSurface = GradientColor_gradient6,
    error = CandidateIconColor_gradient6
)

private val Gradient7ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient7,
    tertiary = KeyBackground_gradient7,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient7,
    onSurfaceVariant = KeyTextColor_gradient7,
    tertiaryContainer = SpecialKeyBackground_gradient7,
    onTertiaryContainer = Popup_gradient7,
    onErrorContainer = Ripple_gradient7,
    errorContainer = SpecialTextColor_gradient7,
    inverseOnSurface = GradientColor2_gradient7,
    inverseSurface = GradientColor_gradient7,
    error = CandidateIconColor_gradient7
)

private val Gradient8ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient8,
    tertiary = KeyBackground_gradient8,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient8,
    onSurfaceVariant = KeyTextColor_gradient8,
    tertiaryContainer = SpecialKeyBackground_gradient8,
    onTertiaryContainer = Popup_gradient8,
    onErrorContainer = Ripple_gradient8,
    errorContainer = SpecialTextColor_gradient8,
    inverseOnSurface = GradientColor2_gradient8,
    inverseSurface = GradientColor_gradient8,
    error = CandidateIconColor_gradient8
)

private val Gradient9ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient9,
    tertiary = KeyBackground_gradient9,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient9,
    onSurfaceVariant = KeyTextColor_gradient9,
    tertiaryContainer = SpecialKeyBackground_gradient9,
    onTertiaryContainer = Popup_gradient9,
    onErrorContainer = Ripple_gradient9,
    errorContainer = SpecialTextColor_gradient9,
    inverseOnSurface = GradientColor2_gradient9,
    inverseSurface = GradientColor_gradient9,
    error = CandidateIconColor_gradient9
)

private val Gradient10ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient10,
    tertiary = KeyBackground_gradient10,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient10,
    onSurfaceVariant = KeyTextColor_gradient10,
    tertiaryContainer = SpecialKeyBackground_gradient10,
    onTertiaryContainer = Popup_gradient10,
    onErrorContainer = Ripple_gradient10,
    errorContainer = SpecialTextColor_gradient10,
    inverseOnSurface = GradientColor2_gradient10,
    inverseSurface = GradientColor_gradient10,
    error = CandidateIconColor_gradient10
)

private val Gradient11ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient11,
    tertiary = KeyBackground_gradient11,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient11,
    onSurfaceVariant = KeyTextColor_gradient11,
    tertiaryContainer = SpecialKeyBackground_gradient11,
    onTertiaryContainer = Popup_gradient11,
    onErrorContainer = Ripple_gradient11,
    errorContainer = SpecialTextColor_gradient11,
    inverseOnSurface = GradientColor2_gradient11,
    inverseSurface = GradientColor_gradient11,
    error = CandidateIconColor_gradient11
)

private val Gradient12ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient12,
    tertiary = KeyBackground_gradient12,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient12,
    onSurfaceVariant = KeyTextColor_gradient12,
    tertiaryContainer = SpecialKeyBackground_gradient12,
    onTertiaryContainer = Popup_gradient12,
    onErrorContainer = Ripple_gradient12,
    errorContainer = SpecialTextColor_gradient12,
    inverseOnSurface = GradientColor2_gradient12,
    inverseSurface = GradientColor_gradient12,
    error = CandidateIconColor_gradient12
)

private val Gradient13ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient13,
    tertiary = KeyBackground_gradient13,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient13,
    onSurfaceVariant = KeyTextColor_gradient13,
    tertiaryContainer = SpecialKeyBackground_gradient13,
    onTertiaryContainer = Popup_gradient13,
    onErrorContainer = Ripple_gradient13,
    errorContainer = SpecialTextColor_gradient13,
    inverseOnSurface = GradientColor2_gradient13,
    inverseSurface = GradientColor_gradient13,
    error = CandidateIconColor_gradient13
)

private val Gradient14ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient14,
    tertiary = KeyBackground_gradient14,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient14,
    onSurfaceVariant = KeyTextColor_gradient14,
    tertiaryContainer = SpecialKeyBackground_gradient14,
    onTertiaryContainer = Popup_gradient14,
    onErrorContainer = Ripple_gradient14,
    errorContainer = SpecialTextColor_gradient14,
    inverseOnSurface = GradientColor2_gradient14,
    inverseSurface = GradientColor_gradient14,
    error = CandidateIconColor_gradient14
)

private val Gradient15ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient15,
    tertiary = KeyBackground_gradient15,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient15,
    onSurfaceVariant = KeyTextColor_gradient15,
    tertiaryContainer = SpecialKeyBackground_gradient15,
    onTertiaryContainer = Popup_gradient15,
    onErrorContainer = Ripple_gradient15,
    errorContainer = SpecialTextColor_gradient15,
    inverseOnSurface = GradientColor2_gradient15,
    inverseSurface = GradientColor_gradient15,
    error = CandidateIconColor_gradient15
)

private val Gradient16ColorScheme = darkColorScheme(
    background = KeyboardBackground_gradient16,
    tertiary = KeyBackground_gradient16,
    secondary = boxColor,
    onTertiary = KeyTextColor_gradient16,
    onSurfaceVariant = KeyTextColor_gradient16,
    tertiaryContainer = SpecialKeyBackground_gradient16,
    onTertiaryContainer = Popup_gradient16,
    onErrorContainer = Ripple_gradient16,
    errorContainer = SpecialTextColor_gradient16,
    inverseOnSurface = GradientColor2_gradient16,
    inverseSurface = GradientColor_gradient16,
    error = CandidateIconColor_gradient16
)

private val SolidLight1ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid1_light,
    tertiary = KeyBackground_Solid1_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid1_light,
    onSurfaceVariant = KeyTextColor_Solid1_light,
    tertiaryContainer = SpecialKeyBackground_Solid1_light,
    onTertiaryContainer = Popup_Solid1_light,
    onErrorContainer = Ripple_Solid1_light,
    errorContainer = SpecialTextColor_Solid1_light,
    inverseOnSurface = GradientColor2_Solid1_light,
    inverseSurface = GradientColor_Solid1_light,
    error = CandidateIconColor_Solid1_light
)

private val SolidLight2ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid2_light,
    tertiary = KeyBackground_Solid2_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid2_light,
    onSurfaceVariant = KeyTextColor_Solid2_light,
    tertiaryContainer = SpecialKeyBackground_Solid2_light,
    onTertiaryContainer = Popup_Solid2_light,
    onErrorContainer = Ripple_Solid2_light,
    errorContainer = SpecialTextColor_Solid2_light,
    inverseOnSurface = GradientColor2_Solid2_light,
    inverseSurface = GradientColor_Solid2_light,
    error = CandidateIconColor_Solid2_light
)

private val SolidLight3ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid3_light,
    tertiary = KeyBackground_Solid3_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid3_light,
    onSurfaceVariant = KeyTextColor_Solid3_light,
    tertiaryContainer = SpecialKeyBackground_Solid3_light,
    onTertiaryContainer = Popup_Solid3_light,
    onErrorContainer = Ripple_Solid3_light,
    errorContainer = SpecialTextColor_Solid3_light,
    inverseOnSurface = GradientColor2_Solid3_light,
    inverseSurface = GradientColor_Solid3_light,
    error = CandidateIconColor_Solid3_light
)

private val SolidLight4ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid4_light,
    tertiary = KeyBackground_Solid4_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid4_light,
    onSurfaceVariant = KeyTextColor_Solid4_light,
    tertiaryContainer = SpecialKeyBackground_Solid4_light,
    onTertiaryContainer = Popup_Solid4_light,
    onErrorContainer = Ripple_Solid4_light,
    errorContainer = SpecialTextColor_Solid4_light,
    inverseOnSurface = GradientColor2_Solid4_light,
    inverseSurface = GradientColor_Solid4_light,
    error = CandidateIconColor_Solid4_light
)

private val SolidLight5ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid5_light,
    tertiary = KeyBackground_Solid5_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid5_light,
    onSurfaceVariant = KeyTextColor_Solid5_light,
    tertiaryContainer = SpecialKeyBackground_Solid5_light,
    onTertiaryContainer = Popup_Solid5_light,
    onErrorContainer = Ripple_Solid5_light,
    errorContainer = SpecialTextColor_Solid5_light,
    inverseOnSurface = GradientColor2_Solid5_light,
    inverseSurface = GradientColor_Solid5_light,
    error = CandidateIconColor_Solid5_light
)

private val SolidLight6ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid6_light,
    tertiary = KeyBackground_Solid6_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid6_light,
    onSurfaceVariant = KeyTextColor_Solid6_light,
    tertiaryContainer = SpecialKeyBackground_Solid6_light,
    onTertiaryContainer = Popup_Solid6_light,
    onErrorContainer = Ripple_Solid6_light,
    errorContainer = SpecialTextColor_Solid6_light,
    inverseOnSurface = GradientColor2_Solid6_light,
    inverseSurface = GradientColor_Solid6_light,
    error = CandidateIconColor_Solid6_light
)

private val SolidLight7ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid7_light,
    tertiary = KeyBackground_Solid7_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid7_light,
    onSurfaceVariant = KeyTextColor_Solid7_light,
    tertiaryContainer = SpecialKeyBackground_Solid7_light,
    onTertiaryContainer = Popup_Solid7_light,
    onErrorContainer = Ripple_Solid7_light,
    errorContainer = SpecialTextColor_Solid7_light,
    inverseOnSurface = GradientColor2_Solid7_light,
    inverseSurface = GradientColor_Solid7_light,
    error = CandidateIconColor_Solid7_light
)

private val SolidLight8ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid8_light,
    tertiary = KeyBackground_Solid8_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid8_light,
    onSurfaceVariant = KeyTextColor_Solid8_light,
    tertiaryContainer = SpecialKeyBackground_Solid8_light,
    onTertiaryContainer = Popup_Solid8_light,
    onErrorContainer = Ripple_Solid8_light,
    errorContainer = SpecialTextColor_Solid8_light,
    inverseOnSurface = GradientColor2_Solid8_light,
    inverseSurface = GradientColor_Solid8_light,
    error = CandidateIconColor_Solid8_light
)

private val SolidLight9ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid9_light,
    tertiary = KeyBackground_Solid9_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid9_light,
    onSurfaceVariant = KeyTextColor_Solid9_light,
    tertiaryContainer = SpecialKeyBackground_Solid9_light,
    onTertiaryContainer = Popup_Solid9_light,
    onErrorContainer = Ripple_Solid9_light,
    errorContainer = SpecialTextColor_Solid9_light,
    inverseOnSurface = GradientColor2_Solid9_light,
    inverseSurface = GradientColor_Solid9_light,
    error = CandidateIconColor_Solid9_light
)

private val SolidLight10ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid10_light,
    tertiary = KeyBackground_Solid10_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid10_light,
    onSurfaceVariant = KeyTextColor_Solid10_light,
    tertiaryContainer = SpecialKeyBackground_Solid10_light,
    onTertiaryContainer = Popup_Solid10_light,
    onErrorContainer = Ripple_Solid10_light,
    errorContainer = SpecialTextColor_Solid10_light,
    inverseOnSurface = GradientColor2_Solid10_light,
    inverseSurface = GradientColor_Solid10_light,
    error = CandidateIconColor_Solid10_light
)

private val SolidLight11ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid11_light,
    tertiary = KeyBackground_Solid11_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid11_light,
    onSurfaceVariant = KeyTextColor_Solid11_light,
    tertiaryContainer = SpecialKeyBackground_Solid11_light,
    onTertiaryContainer = Popup_Solid11_light,
    onErrorContainer = Ripple_Solid11_light,
    errorContainer = SpecialTextColor_Solid11_light,
    inverseOnSurface = GradientColor2_Solid11_light,
    inverseSurface = GradientColor_Solid11_light,
    error = CandidateIconColor_Solid11_light
)

private val SolidLight12ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid12_light,
    tertiary = KeyBackground_Solid12_light,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid12_light,
    onSurfaceVariant = KeyTextColor_Solid12_light,
    tertiaryContainer = SpecialKeyBackground_Solid12_light,
    onTertiaryContainer = Popup_Solid12_light,
    onErrorContainer = Ripple_Solid12_light,
    errorContainer = SpecialTextColor_Solid12_light,
    inverseOnSurface = GradientColor2_Solid12_light,
    inverseSurface = GradientColor_Solid12_light,
    error = CandidateIconColor_Solid12_light
)

private val SolidDark1ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid1_dark,
    tertiary = KeyBackground_Solid1_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid1_dark,
    onSurfaceVariant = KeyTextColor_Solid1_dark,
    tertiaryContainer = SpecialKeyBackground_Solid1_dark,
    onTertiaryContainer = Popup_Solid1_dark,
    onErrorContainer = Ripple_Solid1_dark,
    errorContainer = SpecialTextColor_Solid1_dark,
    inverseOnSurface = GradientColor2_Solid1_dark,
    inverseSurface = GradientColor_Solid1_dark,
    error = CandidateIconColor_Solid1_dark
)

private val SolidDark2ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid2_dark,
    tertiary = KeyBackground_Solid2_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid2_dark,
    onSurfaceVariant = KeyTextColor_Solid2_dark,
    tertiaryContainer = SpecialKeyBackground_Solid2_dark,
    onTertiaryContainer = Popup_Solid2_dark,
    onErrorContainer = Ripple_Solid2_dark,
    errorContainer = SpecialTextColor_Solid2_dark,
    inverseOnSurface = GradientColor2_Solid2_dark,
    inverseSurface = GradientColor_Solid2_dark,
    error = CandidateIconColor_Solid2_dark
)

private val SolidDark3ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid3_dark,
    tertiary = KeyBackground_Solid3_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid3_dark,
    onSurfaceVariant = KeyTextColor_Solid3_dark,
    tertiaryContainer = SpecialKeyBackground_Solid3_dark,
    onTertiaryContainer = Popup_Solid3_dark,
    onErrorContainer = Ripple_Solid3_dark,
    errorContainer = SpecialTextColor_Solid3_dark,
    inverseOnSurface = GradientColor2_Solid3_dark,
    inverseSurface = GradientColor_Solid3_dark,
    error = CandidateIconColor_Solid3_dark
)

private val SolidDark4ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid4_dark,
    tertiary = KeyBackground_Solid4_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid4_dark,
    onSurfaceVariant = KeyTextColor_Solid4_dark,
    tertiaryContainer = SpecialKeyBackground_Solid4_dark,
    onTertiaryContainer = Popup_Solid4_dark,
    onErrorContainer = Ripple_Solid4_dark,
    errorContainer = SpecialTextColor_Solid4_dark,
    inverseOnSurface = GradientColor2_Solid4_dark,
    inverseSurface = GradientColor_Solid4_dark,
    error = CandidateIconColor_Solid4_dark
)

private val SolidDark5ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid5_dark,
    tertiary = KeyBackground_Solid5_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid5_dark,
    onSurfaceVariant = KeyTextColor_Solid5_dark,
    tertiaryContainer = SpecialKeyBackground_Solid5_dark,
    onTertiaryContainer = Popup_Solid5_dark,
    onErrorContainer = Ripple_Solid5_dark,
    errorContainer = SpecialTextColor_Solid5_dark,
    inverseOnSurface = GradientColor2_Solid5_dark,
    inverseSurface = GradientColor_Solid5_dark,
    error = CandidateIconColor_Solid5_dark
)

private val SolidDark6ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid6_dark,
    tertiary = KeyBackground_Solid6_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid6_dark,
    onSurfaceVariant = KeyTextColor_Solid6_dark,
    tertiaryContainer = SpecialKeyBackground_Solid6_dark,
    onTertiaryContainer = Popup_Solid6_dark,
    onErrorContainer = Ripple_Solid6_dark,
    errorContainer = SpecialTextColor_Solid6_dark,
    inverseOnSurface = GradientColor2_Solid6_dark,
    inverseSurface = GradientColor_Solid6_dark,
    error = CandidateIconColor_Solid6_dark
)

private val SolidDark7ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid7_dark,
    tertiary = KeyBackground_Solid7_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid7_dark,
    onSurfaceVariant = KeyTextColor_Solid7_dark,
    tertiaryContainer = SpecialKeyBackground_Solid7_dark,
    onTertiaryContainer = Popup_Solid7_dark,
    onErrorContainer = Ripple_Solid7_dark,
    errorContainer = SpecialTextColor_Solid7_dark,
    inverseOnSurface = GradientColor2_Solid7_dark,
    inverseSurface = GradientColor_Solid7_dark,
    error = CandidateIconColor_Solid7_dark
)

private val SolidDark8ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid8_dark,
    tertiary = KeyBackground_Solid8_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid8_dark,
    onSurfaceVariant = KeyTextColor_Solid8_dark,
    tertiaryContainer = SpecialKeyBackground_Solid8_dark,
    onTertiaryContainer = Popup_Solid8_dark,
    onErrorContainer = Ripple_Solid8_dark,
    errorContainer = SpecialTextColor_Solid8_dark,
    inverseOnSurface = GradientColor2_Solid8_dark,
    inverseSurface = GradientColor_Solid8_dark,
    error = CandidateIconColor_Solid8_dark
)

private val SolidDark9ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid9_dark,
    tertiary = KeyBackground_Solid9_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid9_dark,
    onSurfaceVariant = KeyTextColor_Solid9_dark,
    tertiaryContainer = SpecialKeyBackground_Solid9_dark,
    onTertiaryContainer = Popup_Solid9_dark,
    onErrorContainer = Ripple_Solid9_dark,
    errorContainer = SpecialTextColor_Solid9_dark,
    inverseOnSurface = GradientColor2_Solid9_dark,
    inverseSurface = GradientColor_Solid9_dark,
    error = CandidateIconColor_Solid9_dark
)

private val SolidDark10ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid10_dark,
    tertiary = KeyBackground_Solid10_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid10_dark,
    onSurfaceVariant = KeyTextColor_Solid10_dark,
    tertiaryContainer = SpecialKeyBackground_Solid10_dark,
    onTertiaryContainer = Popup_Solid10_dark,
    onErrorContainer = Ripple_Solid10_dark,
    errorContainer = SpecialTextColor_Solid10_dark,
    inverseOnSurface = GradientColor2_Solid10_dark,
    inverseSurface = GradientColor_Solid10_dark,
    error = CandidateIconColor_Solid10_dark
)

private val SolidDarK11ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid11_dark,
    tertiary = KeyBackground_Solid11_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid11_dark,
    onSurfaceVariant = KeyTextColor_Solid11_dark,
    tertiaryContainer = SpecialKeyBackground_Solid11_dark,
    onTertiaryContainer = Popup_Solid11_dark,
    onErrorContainer = Ripple_Solid11_dark,
    errorContainer = SpecialTextColor_Solid11_dark,
    inverseOnSurface = GradientColor2_Solid11_dark,
    inverseSurface = GradientColor_Solid11_dark,
    error = CandidateIconColor_Solid11_dark
)

private val SolidDark12ColorScheme = darkColorScheme(
    background = KeyboardBackground_Solid12_dark,
    tertiary = KeyBackground_Solid12_dark,
    secondary = boxColor,
    onTertiary = KeyTextColor_Solid12_dark,
    onSurfaceVariant = KeyTextColor_Solid12_dark,
    tertiaryContainer = SpecialKeyBackground_Solid12_dark,
    onTertiaryContainer = Popup_Solid12_dark,
    onErrorContainer = Ripple_Solid12_dark,
    errorContainer = SpecialTextColor_Solid12_dark,
    inverseOnSurface = GradientColor2_Solid12_dark,
    inverseSurface = GradientColor_Solid12_dark,
    error = CandidateIconColor_Solid12_dark
)

@Composable
fun KeyboardTheme(
    theme: AppTheme = AppTheme.AUTO,
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        AppTheme.AUTO -> when {
            isSystemInDarkTheme() -> SindhiDefaultColorScheme
            else -> SindhiDefaultColorScheme
        }

        AppTheme.LIGHT -> LightColorScheme
        AppTheme.DARK -> DarkColorScheme
        AppTheme.SolidSimple -> SolidSimpleColorScheme
        AppTheme.SEL -> SelColorScheme
        AppTheme.REA -> ReaColorScheme
        AppTheme.TEMPLE -> TempleColorScheme
        AppTheme.THANOS -> ThanosColorScheme
        AppTheme.NEBULA -> NebulaColorScheme
        AppTheme.MEMARIANI -> MemarianiColorScheme
        AppTheme.Gradient1 -> Gradient1ColorScheme
        AppTheme.Gradient2 -> Gradient2ColorScheme
        AppTheme.Gradient3 -> Gradient3ColorScheme
        AppTheme.Gradient4 -> Gradient4ColorScheme
        AppTheme.Gradient5 -> Gradient5ColorScheme
        AppTheme.Gradient6 -> Gradient6ColorScheme
        AppTheme.Gradient7 -> Gradient7ColorScheme
        AppTheme.Gradient8 -> Gradient8ColorScheme
        AppTheme.Gradient9 -> Gradient9ColorScheme
        AppTheme.Gradient10 -> Gradient10ColorScheme
        AppTheme.Gradient11 -> Gradient11ColorScheme
        AppTheme.Gradient12 -> Gradient12ColorScheme
        AppTheme.Gradient13 -> Gradient13ColorScheme
        AppTheme.Gradient14 -> Gradient14ColorScheme
        AppTheme.Gradient15 -> Gradient15ColorScheme
        AppTheme.Gradient16 -> Gradient16ColorScheme
        AppTheme.SOLID_DARK_1 -> SolidDark1ColorScheme
        AppTheme.SOLID_DARK_2 -> SolidDark2ColorScheme
        AppTheme.SOLID_DARK_3 -> SolidDark3ColorScheme
        AppTheme.SOLID_DARK_4 -> SolidDark4ColorScheme
        AppTheme.SOLID_DARK_5 -> SolidDark5ColorScheme
        AppTheme.SOLID_DARK_6 -> SolidDark6ColorScheme
        AppTheme.SOLID_DARK_7 -> SolidDark7ColorScheme
        AppTheme.SOLID_DARK_8 -> SolidDark8ColorScheme
        AppTheme.SOLID_DARK_9 -> SolidDark9ColorScheme
        AppTheme.SOLID_DARK_10 -> SolidDark10ColorScheme
        AppTheme.SOLID_DARK_11 -> SolidDarK11ColorScheme
        AppTheme.SOLID_DARK_12 -> SolidDark12ColorScheme
        AppTheme.LEMON_TWIST -> LemonTwistColorScheme
        AppTheme.METALLIC_TOAD -> MetallicToadColorScheme
        AppTheme.SUBLIME_LIGHT -> SublimeLightColorScheme
        AppTheme.SOLID_LIGHT_1 -> SolidLight1ColorScheme
        AppTheme.SOLID_LIGHT_2 -> SolidLight2ColorScheme
        AppTheme.SOLID_LIGHT_3 -> SolidLight3ColorScheme
        AppTheme.SOLID_LIGHT_4 -> SolidLight4ColorScheme
        AppTheme.SOLID_LIGHT_5 -> SolidLight5ColorScheme
        AppTheme.SOLID_LIGHT_6 -> SolidLight6ColorScheme
        AppTheme.SOLID_LIGHT_7 -> SolidLight7ColorScheme
        AppTheme.SOLID_LIGHT_8 -> SolidLight8ColorScheme
        AppTheme.SOLID_LIGHT_9 -> SolidLight9ColorScheme
        AppTheme.SOLID_LIGHT_10 -> SolidLight10ColorScheme
        AppTheme.SOLID_LIGHT_11 -> SolidLight11ColorScheme
        AppTheme.SOLID_LIGHT_12 -> SolidLight12ColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

enum class AppTheme() {
    AUTO,
    LIGHT,
    DARK,
    SolidSimple,
    SEL,
    REA,
    TEMPLE,
    THANOS,
    NEBULA,
    METALLIC_TOAD,
    SUBLIME_LIGHT,
    LEMON_TWIST,
    MEMARIANI,
    Gradient1,
    Gradient2,
    Gradient3,
    Gradient4,
    Gradient5,
    Gradient6,
    Gradient7,
    Gradient8,
    Gradient9,
    Gradient10,
    Gradient11,
    Gradient12,
    Gradient13,
    Gradient14,
    Gradient15,
    Gradient16,
    SOLID_DARK_1,
    SOLID_DARK_2,
    SOLID_DARK_3,
    SOLID_DARK_4,
    SOLID_DARK_5,
    SOLID_DARK_6,
    SOLID_DARK_7,
    SOLID_DARK_8,
    SOLID_DARK_9,
    SOLID_DARK_10,
    SOLID_DARK_11,
    SOLID_DARK_12,
    SOLID_LIGHT_1,
    SOLID_LIGHT_2,
    SOLID_LIGHT_3,
    SOLID_LIGHT_4,
    SOLID_LIGHT_5,
    SOLID_LIGHT_6,
    SOLID_LIGHT_7,
    SOLID_LIGHT_8,
    SOLID_LIGHT_9,
    SOLID_LIGHT_10,
    SOLID_LIGHT_11,
    SOLID_LIGHT_12;
}

