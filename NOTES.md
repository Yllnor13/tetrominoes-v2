# Ideas

tetromino size:
    
    4 by 4, since I dont need any more than that
    hard code the rotation in since I know that there will only be 7 tetrominoes and that no more will be added
        but if I want to make them rotate programattically
        in the inner 2x2 square I make it so that 1 goes down, one goes right one goes up and one goes up and one goes left
        in the outer 4x4 square, make it so that 4 steps have to be taken
            if cormer->go down/

    tetromino in play will need to check if its touching the pile cube at the bottom, so I should make
    it so that every cube has its own ID, or just make it so tha cubes have an active/inactive tag and run
    an if check if its touching that

    code everything in one file(?)

## MAIN
    
    main will act as a game manager

    I should make it produce the tetrominoes AND keep track of them and what they do.

## MINOMINOES

    needs to have an active and inactive state.
    active state is for tetrominoes that are already being used by the user, and inactive states is for when it has already settled on the field


## TENNIS (?)
    
    if I make a tennis class, I need to make...
    
     an insert class for inserting tetrominoes that will always check if the tetromino that is getting inserted into that spot is about to hit another minomino

        make the insert function check if a block is out of bounds

     needs to run a test that checks if blocks are stacked are stacked on every line when a block is placed (check if there is a row of inactive minominoes)

     turns out, making a minomino in tennis be equal ("=") to a minomino in a tetromino just makes it so that the tetromino also gets erased when the erase function at the start of the insert method gets called

    