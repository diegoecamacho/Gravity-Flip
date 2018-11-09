# Gravity-Flip
Gravity Flip Android Game

Layout: 
Forced Landscape

Game Logic:
    Endless runner where you must flip gravity in order to edge obstacles and incoming enemies.

Possibility:
    Player Cannot touch platforms at all they have to switch gravity constantly


UI Screens:
(Order of Importance)
Menu Scene:
    1.  Game Title
    2.  Play
    3. Options
    4. High Score
    
Game Scene:
    1. Touch Screen to flip gravity
    2. Power Up ButtoN
    3. Options Button
    
Game Over:
    1. Try Again
    2. Return to Main
    
Player Controller:
 - Gravity Flip
 - Endless Runner
 - Item Pick-Up
 - Enemy Damage

Power Ups:
(In order of preference)
1. Pick up item and Store on demand replaces if another power up is picked up
    - Or don’t get it if you run over
2. Pick up power up and activate immidietly
3. Pick up player and Store and Activate on demand (Could have more than 1 power up)

Types:
  Player Assist
  (Order of Importance)
  - Times Invisibility shield
  - Nuke
  - Slow Time

  Player Negate:
  -  Speed up time.
  -  Parabola Missile
  -  Gravity touch inout reversed


Score System:
  Distance Traveled increases score
    - Multiplier(Possibility)

  Destroy Enemies increase score
    - The more enemies destroyed the higher the multiplier.

Enemies:
  - Missiles
  - Tracker:
      - Finds tracker when instantiated.
  - Static Mines
      - Scroll with background.

Level Generator: 
        Spawn Top and Bottom of level
        Level Snippets must connect to each other.

Enemy Spawner -> Enemies
        Enemies should spawn inside the Level Volume
        
Item Spawner - > Items
        Items should spawn

Scripts:
Game Manager - Singleton

Base Scene  - Base Class

Splash Scene:

Main Menu Scene:
MainMenuScene Script - Base Scene
    Button - Base Class
* Play Button - Button Inherit
* Options Button - Button Inherits

Game Scene:
    Game Scene - Base Scene
