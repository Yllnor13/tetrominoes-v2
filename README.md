# Tetrominoes v2


I watched the movie "tetris" and I thought that a tetris game where the UI is entirely in the terminal looked really endearing. I tried to make something similar in an earlier project, but due to a lack of planning ahead, I ended up making a lot of decisions that I would end up regretting down the line until I thought that the best choice was to start again with those mistakes in mind.


In this second attempt, I will make a plan and make requierments so that I can have a clearer idea of what I need to do.

## Requierments

### essential functional parts

1. The games user interface will be exclusively done through the terminal.

2. it will have almost (if not) all the same functionalities as a regular game of tetris, but at the very least, it will have the functions defined below

3. the game will have all of the 7 tetrominoes that compromises the game
    those being:
        it will have all 7 different tetrominoes
            O-Tetromino
                [][]
                [][]
            L-Tetromino
                []
                []
                [][]
            J-Tetromino
                  []
                  []
                [][]
            Z-Tetromino
                [][]
                  [][]
            I-Tetromino
                []
                []
                []
                []
            S-Tetromino
                  [][]
                [][]
            T-Tetromino
                  []
                [][][]

    every tetromino is compromised of a minomino

4. the game recognizes when the tetromino currently in play either hits the bottom of the tennis field or wherever the tetromino field is

5. The tetrominoes will be rotateable

6. you can save a tetromino to be used later

7. you can see the next 3 tetrominoes that are to come

8. the game should advance itself, so that the user doesnt have to manually make the tetrominoes go down, but the user should be able to make the tetrominoes go left and right without affecting it going down

9. the tetrominoes descent speed should gradually increase as the game goes on

10. once the bricks go above the field, its game over

### USER INTERFACE

The user interface will use square brackets and dots in the place of blocks and empty spaces
    a game should look like this:
    
    example 1: emtpy field, no saved blocks

    <!. . . . . . . . . . !>        <!SAVED===!>
    <!. . . . . . . . . . !>        <!. . . . !>
    <!. . . . . . . . . . !>        <!. . . . !>
    <!. . . . . . . . . . !>        <!. . . . !>
    <!. . . . . . . . . . !>        <!. . . . !>
    <!. . . . . . . . . . !>        <!========!>
    <!. . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!NEXT=TETROMINOES================!>
    <!. . . . . . . . . . !>        <!. . . . . . . . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!. . . . . . . . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!. . . . . . . . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!. . . . . . . . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!. . . . . . . . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!. . . . . . . . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!================================!>
    <!. . . . . . . . . . !>
    <!. . . . . . . . . . !>
    <!. . . . . . . . . . !>
    <!. . . . . . . . . . !>
    <!====================!>

    example 2: non-empty field with a saved block (L-tetromino) and 3 tetrominoes coming up (O-block, I-block and S-block)

    <!. . . . . . . . . . !>        <!SAVED===!>
    <!. . . . . . . . . . !>        <!. []. . !>
    <!. . . . . . . . . . !>        <!. []. . !>
    <!. . . . . . . . . . !>        <!. [][]. !>
    <!. . . . . . . . . . !>        <!. . . . !>
    <!. . . . . . . . . . !>        <!========!>
    <!. . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!NEXT=TETROMINOES================!>
    <!. . . . . . . . . . !>        <!. . . . . . . . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!. . . . . . . []. . . . . . . . !>
    <!. . . . . . . . . . !>        <!. [][]. . . . []. . . . . [][]. !>
    <!. . . . . . . . . . !>        <!. [][]. . . . []. . . . [][]. . !>
    <!. . . . . . . . . . !>        <!. . . . . . . []. . . . . . . . !>
    <!. . . . . . . . . . !>        <!. . . . . . . . . . . . . . . . !>
    <!. . . . . . . . . . !>        <!================================!>
    <!. . [][][]. [][]. . !>
    <!. . . [][][][][][][]!>
    <![][][][]. [][][][]. !>
    <![][][]. [][][][]. . !>
    <!====================!>


# Terminology

Tetromino: Tetromino refers to the 7 block pieces that will be used to play the game.
Tennis field: refers to the playing field in which the game takes place
minomino: a single 1x1 square that every tetromino is comprised of

I will use "Tetromino" and "block" interchangeably. Same goes for "tennis", "field" and "tennis field"