# What is Phy6?
Phy6 is a game project that I'm working on for my AP Computer Science class.

The purpose of the game is to simulate cellular automata or gravity on objects.
This is inspired by the game "Noita" which has an engine called "Falling Sand".

# Game Plan
- ~~Setup LWGL~~ 
- ~~Get basic utilities (Delta Time, Etc.)~~
- ~~Create Pixel interface/class~~
- ~~Add logic to pixel classes~~
- ~~Sand Pixel~~
- Water Pixel
- Dirt Pixel
- Wood Pixel
- Fire Pixel

# How to compile?
To compile, you will need to have Maven (AND INTELLIJ) installed. If you cannot build, I will have a distribution of the JAR file on my webserver, which I will post here when I have the free time.

(Click on "Maven" on the right hand side of the IntelliJ bar, a tab with Lifecycle and Profiles will pop up.)
32 Bit Java:

- Switch your profile to lwjgl-natives-windows-x86
- Double click on "package"
- Double click on "install"
- Now there should be a jar file inside target/, which you will be able to run. If you wish to move this to another directory, make sure to take the lib/ folder with it!

64 Bit Java:
- Switch your profile to lwjgl-natives-windows-amd64
- Double click on "package"
- Double click on "install"
- Now there should be a jar file inside target/, which you will be able to run. If you wish to move this to another directory, make sure to take the lib/ folder with it!
