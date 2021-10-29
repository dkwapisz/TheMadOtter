# The Mad Otter
> High-level Programming Languages Project
## Table of Contents
- [General](#General)
- [Features](#Features)
- [Technologies Used](#technologies-Used)
- [Screenshots](#Screenshots)
- [How to open](#how-to-open)
- [Acknowledgments](#Acknowledgments)

## General
"The Mad Otter" is a rougelike game based on "The Binding of Isaac" mechanics. As befits rougelike games, the game is very difficult. The game was created to:
- better understanding of the JavaFX library
- get better acquainted with the idea of object-oriented programming (creating a simple game engine)
- experiment with mechanics based on simple RNG

## Features
The game contains many interesting mechanics such as:
- randomly generated map based on previously created templates
- basic animation e.g. destruction of blocks
- 15 different weapons like shotgun, rocket launcher, ak47
- time-limited powerups
- 13 various enemies with different behaviours
- 4 floors with different textures
- map and interface

## Technologies Used
- JavaFX 15.0.1
- FXML (SceneBuilder)

## Screenshots
![screenshot1](https://github.com/dkwapisz/TheMadOtter/blob/ProjektJPWP2021/src/graphics/image1.png)
![screenshot2](https://github.com/dkwapisz/TheMadOtter/blob/ProjektJPWP2021/src/graphics/image2.png)

## How to open 
### IntelliJ IDEA
1. File -> Project Structure -> Libraries -> Add (+) -> javafx-sdk-15.0.1/lib -> OK
2. File -> Project Structure -> Modules -> lib -> OK
3. Run -> Edit configuration -> VM options:

Windows: ``` --module-path "..\TheGame\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml ```  
Linux: ``` --module-path ../TheGame/javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml ```

## Acknowledgments
- Project was created in cooperation with [Maksymilian Kowalik](https://github.com/kowaleuro)
- Movement handling was based on [this](https://github.com/ashish2199/Aidos)
