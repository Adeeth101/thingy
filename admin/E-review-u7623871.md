## Code Review

Reviewed by: Adith Rajesh Iyer, u7623871

Reviewing code written by: Cameron Werner, u7265364

Component: BoardOther Class

### Comments 

This is a class that has been implemented to check the validity of a rug placement. 
What this class does well is store all relevant information when it comes to a board in the game.
the class manages to store all the rugs' information, including id and colour, it stores the position information
by conveniently placing the rug in a certain position on the matrix. This is reflected in the strong constructor of the
code. My only nitpick was that since it only has one real use case, maybe it should have been a subclass within
the board class or the Marrakech class. Apart from that, the java code style is fairly consistent, the variables are 
understandable and the additional functions such as get rugs and is rug on board are all appropriately named.




