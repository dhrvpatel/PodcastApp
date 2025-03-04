## Changes & Minor Bug Fixes

| **Issue / Change** | **Resolution** |
|--------------------|---------------|
| Multiple details screens opening on fast taps | Implemented a **`click debounce`** |
| Back button alignment issue | Used **`Alignment.Start`** and proper padding |
| Ripple effect on list items and back button | Disabled using **`interactionSource` & `indication = null`** |
| Loading indicator covering full screen | Used **`Box(contentAlignment = Alignment.Center)`** for proper placement |
| Podcast description cutting off | Wrapped text in **`verticalScroll(rememberScrollState())`** |
| Favourite button shape issue | Applied **`RoundedCornerShape(12.dp)`** for a rounded rectangle look |
| List item divider pressed state issue | Applied `clickable` only to **Row**, not Divider |
| Multiple clicks causing duplicate navigation | **Blocked rapid taps** using `LaunchedEffect` |
| Back button not clickable properly | Moved `clickable` to **Row containing icon & text** |
| Text not scrollable when overflowing | Wrapped **only text** in `Box().verticalScroll()` |
