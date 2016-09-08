# Week 2

> There are several drawable directories options: xhdpi, hdpi, mdpi and ldpi. What are they used for? Which directory should you use for the drawables?

res/drawable voor alle dynamische drawables. De verschillende densities (hdpi, mdi, etc) horen bij apparaten met dezelfde grote, zodat de correcte schaal wordt weergegeven.

> If you look at the XML code, you might get a warning.
> Which warning is it?

[Accessibility] Missing contentDescription attribute on image

> You might get another warning. Which one?

Geen? Vermoedelijk dat de string naar strings.xml verplaatst moet worden.
