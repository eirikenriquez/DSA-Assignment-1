This assignment is created by Eirik Mykel Navarro Enriquez for COMP610.
Notes:
	Question 1:
		- The snake game also ends if the snake hits a '0' number as the snake will lose it's head therefore the snake is dead.
		- The snake doesn't collide with itself as it wasn't stated as a requirement for the assignment.
	
	Question 2:
		- The water is sewage water
		- The Question is: “Which object have you chosen for the synchronize? Why?
		- My Answer is: I chose the Island object as the synchronized object for the Boats/Threads because all the Boat objects/threads update/access the boatDocked boolean in the Island object which enables the possibility of a race condition if the object weren't synchronized.