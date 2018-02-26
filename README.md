Can You Wump the Wumpus?
========================

## Background

This program was a final exam project which was assigned to me when I first began programming back in my sophormore year of high school (2006?). It was an Intro to Java course and despite my desire to do well and passion for computers, I sucked at it. Like horribly bad. I did all my homework assignments and studied concepts but when it came down to programming, I just couldn't do it.

I am now a Computer Engineer with several years of programming experience for both application level and embedded programming. My parents moved from their house I've been in since 12 years old and as a result of helping them move, I found this exam which I have kept in binders stored in the attic.

I failed this final exam and it has loomed over me ever since. I was extremely bad at programming back then, even though I was passionate about it. On the due date, I submitted a source code file with zero lines of source code. It was merely a block comment saying how sorry I was that I couldn't complete this assignement and explaining that I tried and failed. None of the source code I wrote made sense, so I deleted it.

My teacher should have failed me, at least getting a D or something close to that for the course. But beyond my belief he gave me a B-. Still today, I think because I came to him almost every day for extra help and constantly had one on one sessions with him to talk about my difficulties. I think he saw my passion and took pity on me.

Because he did this, it didn't shatter my hopes and dreams of going into a computer field. Completing this assignment, finally, was done as tribute to him and his kindness.

## Assignment

The original assignment instructions is in this project repo as a PDF. It essentially boils down to the following...

You are in a cave of 20 rooms. 10 Rooms connected in a circle and 10 rooms connected in an outter circle. Randomly placed, you must navigate your way to an exit. However there are obstacles:

1. A bottomless pit: If you enter this room, game over
2. Bats: If you enter the room which has bats, you will go back to the room you previously came from. They move around each time you do
3. The Wumpus: If you enter the room it is in, the creature kills and devours you. It moves each time you do and when you are in an adjacent room, you can smell its breath.

When you are in a room adjacent to the exit, you can feel a breeze. When you reach the exit, you win the game!

## Requirements

The original requirements were as follows:

1. Use an ArrayList to model the outer hallway
2. Use an ArrayList to model the inner hallway
3. A "Room" class must be created which can do the following

    `3a. Know which hallway it is in`

    `3b. Know its index in the ArrayList`

    `3c. Should know if it is an exit`

    `3d. Should know if anything is in it`

    `3e. Should be able to find which rooms are adjacent to it. `

        (Note: I remember distinctly 3e was removed during the exam because someone brought it up to the teacher. He admitted it was out of the scope of what he wanted. I remember this because it was the first time I saw a teacher change something on a final exam)

## My Program

In myy program, I've added a couple things such as a help screen. Please forgive me as it has been several years since I've actually done java, I mostly do C/C++/C# nowadays.

Since I no longer had my teacher as a resource, I took some assumtions/changes of my own.

1. The game by default will not move the Wumpus/Bats unless you are in hard mode. I think the game with the given instructions is difficult.
2. I've added a help option to toggle a map of where everything is
3. Player can get a gameover from the start (just like in games like minesweep)
4. Hard and Help modes can be activated by passing in arguments

### How to Build and Run

Have a java compiler and all you need to do is in a commandline `javac *.java` in the folder and to run the program `java WumpusGame`.

I've added support for two arguments. Doing `java WumpusGame hard` will enable hard mode where the wumpus and bats will move each time you do. Doing `java WumpusGame help` will toggle on the map of the cavern. You can combine the two into a call `java WumpusGame hard help` which will enable both features

I have also included build and run scripts.

## Closing

I wish I could thank my teacher. Because of his kindness, it kept me modivated to pursue my passion and become a very good programmer and engineer. I will never forget this class and my first failure as a student.