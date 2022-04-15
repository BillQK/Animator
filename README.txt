ANIMATOR (CS3500)
This program is to animate objects.

SUMMARY/OVERVIEW

ASSIGNMENT 5
Our Programs allow users to animate Rectangle and Ellipse. Every shape that is declared in
Our Model has a List of Shape, List of Commands,
and Time - an existing time (Start Time to End Time).

A Shape features includes:
 - Name
 - Position - This is our implementation of Posn class, our Class has an x and y coordinate,
 Noted: Position can be negative.
 - Width
 - Height
 - Color - This is a built-in awt library class.
 - Time - This is our implementation of Time class, our Class has a start time and an end time.
 Noted: Time cannot be negative, and end time cannot be greater than start time.

Our program also allow to animate commands on the given shape
A Command features includes:
 - ChangeColor - Requires an end Color.
 - ChangeDimension -Requires an end Height and end Width.
 - Move - Requires an end Position.


DESCRIPTION/IN-DEPTH
An in-depth paragraph about your project and overview of use.

Our program includes three parts: View, Controller, and Model.
Then our model package includes three packages: command, shape, utils
and two interfaces: IAnimatorModel and IAnimatorModelState
and our main model class: SimpleAnimatorModel.

Our SimpleAnimatorModel is our class and we also have a builder pattern in our class
and this is where we can call on to start adding shapes and commands. When using the builder,
before adding any shapes or commands, we need to set end time for the whole game
in order to start adding shapes and commands.

In model.shape package:
Our program starts by creating an Abstract Shape class, an Shape type enum class, a Posn class,
and a Time class.
Shape enum class represents the 2 shapes types we implement in our model are Rectangle and Ellipse.
Then we create 2 shape classes (Rectangle & Ellipse) which extend the Abstract Shape class.
Our Abstract Shape class method include getTheShape, getName, getType, getPosition, getColor,
setColor, getWidth, setWidth, getHeight, setHeight, getTime, setPosn, equals, hashCode.
These methods allow us to get the specific field of the shape
and able to set some specific field of the shape.

In model.command package:
Our program starts with creating 2 interfaces: ICommands and ICommandsState.
ICommandsState had the method to get the begin state of a command, get end state of a command,
get the full state of the command, get the shape the command being called on, get the command type,
get the start time of the command and get the end time of the command.
ICommands include one method to execute the command on the shape
given the current time of the model.
Then we create a CommandType enum class representing the 3 commands we can operate:
move, change color, and change dimensions.
We then create an Abstract ACommand class which implements the 2 interface
and operates all the methods.
Then we create three command classes which extend the Abstract command class.
In each of the three command classes, depending on what that command does,
we will set that field in our shape to that new command.

In model.utils package:
We put our Time and Posn class there.
We then create 2 other classes called ArgumentsCheck and RateOfChange.
ArgumentsCheck is the class where we write methods to check for overlapping time, inInterval time,
less than zero, and Empty String.
Our RateOfChange class is for calculating the amount of change when executing
a command at the current time of the model.

Example Output: 
Shape: 1 Rectangle
         START                                  END 
motion 1 Time X Y Width Height Red Green Blue   Time X Y Width Height Red Green Blue
motion 1 0.0 10.0 15.0 100.0 200.0 10 10 10     3.0 10.0 15.0 100.0 200.0 20 20 20 
motion 1 3.0 10.0 15.0 100.0 200.0 20 20 20     5.0 11.0 40.0 100.0 200.0 20 20 20 
motion 1 5.0 11.0 40.0 100.0 200.0 20 20 20     6.0 11.0 30.0 100.0 200.0 20 20 20 
motion 1 6.0 11.0 30.0 100.0 200.0 20 20 20     10.0 11.0 30.0 150.0 250.0 20 20 20 
motion 1 10.0 11.0 30.0 150.0 250.0 20 20 20     15.0 11.0 30.0 200.0 200.0 20 20 20 

Shape: 2 Ellipse
         START                                  END 
motion 2 Time X Y Width Height Red Green Blue   Time X Y Width Height Red Green Blue
motion 2 3.0 10.0 40.0 100.0 400.0 10 10 10     30.0 10.0 40.0 400.0 500.0 10 10 10 
motion 2 30.0 10.0 40.0 400.0 500.0 10 10 10     35.0 10.0 30.0 400.0 500.0 10 10 10 
motion 2 35.0 10.0 30.0 400.0 500.0 10 10 10     40.0 10.0 30.0 403.0 50.0 10 10 10 



ASSIGNMENT 6
We start our assignment 6 by first fixing our builder in the SimpleAnimatorModel class.
We decide to build a complete new and upgraded builder
which take in 2 hash map, a width, and a height.
We then also add an extra method in the IAnimatorModel which will help delete a command.
We also add in an EmptyCommand class which when we run the animations on the shape, when there is
a time range which doesn't have any animations, we then add an empty animation which did nothing on
the shape and just to return how the shape was.

We then move on to work on our view assignment
We first create an IAnimatorView interface which have multiple method
to support the three view class  a getDetails, writeFile(String fileName),
addListener(IAnimatorController listener), refresh, makeVisible, showErrorMessage(String error),
setShapes(List<AShape> losTempo).

We then start first with AnimatorTextView which we only implements and run on 2 operations which is
getDetails and writeFile(String fileName) method. The class had an IAnimatorModelState<AShape>
model which help us have a model which able to get all the method in model state, and able to print
out a String of the view. For the rest of the method, we throw
new UnsupportedOperationException(" ");

We then start next on our AnimatorSVGView extends AnimatorTextView class. We override
getDetails and writeFile(String fileName) method. Beside a model, we also add a double which
represent a speed for the model. For SVG view, we then need 2 methods
getSVG(tempo) and getSVGEndShape(). We then add these methods in the 2 shape class (Rectange and
Ellipse) to get back a specific string for the shape's type, width, height, X and Y position.

We then lastly work on our AnimatorVisualView which the class extends JFrame and implements
IAnimatorView. We had an APanel and a List<AShape> as our field for the class. In this class, we
support 4 methods for now which are refresh method (which we repaint our view every time we call),
makeVisible method which need to be call to be able to see the view, showErrorMessage(String error)
method which we show the error message on your view and log you out of the view. Lastly, the
setShapes(List<AShape> s) method which will set the given shape to the Panel which will present the
view. We then create a class call APanel which extends JPanel and this class is where we draw the
given shape that was given through the JFrame. We create a method called setShapes and override
paintComponent(Graphics g) method. In the paintComponent method, we decide to transform the
Graphics to Graphics2D for more operations.

We also create a main method which is where we start running our animation and command-line to run
the view. Beside that, we create an AnimatorViewCreator to make a factory method so that depend on
what the command-line want to show the view at, we will create the specify view as required.


ASSIGNMENT 7
For this assignment, we first create a new view class called AnimatorInteractiveView which extends
JFrame and implements IAnimatorView. This class operate almost the same to AnimatorVisualView, but
we add in 6 more fields in the class to represent the button click which are start/resume, pause,
restart, speed up, speed down, loop button and an extra boolean field to give true when the loop
button is click and turn on the loop cycle and false when it turn off the loop cycle.
We also add in 4 extra methods which are addListener to add an ActionListener for the 6 buttons
and an addKeyListener which add the KeyListener to all the 6 button fields. The other 2 method
setIsLoop which set the IsLoop field to the given boolean we want and getIsLoop is to get the
current isLoop field status.

Then we start to create an IAnimatorController interface to later on create 4 controller and each
controller is for 4 of the view class: InteractiveCtrl, VisualCtrl, TextCtrl, SVGCtrl. In our
interface, we have 3 methods which are getTimer which get the timer of the model, getSpeed to get
the speed from the user input and lastly start - a method so that when the controller called start,
it will start the view in the way the user want to see either svg, text, visual, or interactive.
For our Interactive controller, beside implements our IAnimatorController interface, we also need
to implement an ActionListener, and a KeyListener. By implementing these listener, we will also
have to override the method of that listener which is actionPerformed and KeyTyped, KeyPressed, and
KeyReleased to properly assign the key and button to do the action we decide.

While doing that, we add an extra method in the ICommand which is setShapes which set the shape of
the command to the given new shape we put in. The reason we did this is that everytime our
interactive view will show our deep copy of the shape and not on the main shape itself. Since
everytime our shape run the animation, we mutate straight on the shape and this won't allow to
implement the restart and loop since our model has been mutated with the animation/commands.

Then when we create our two animations class, we have another method in ICommand which will print
out the command in String format which let us know what it did. For example, "canvas 300 300",
"rectangle name background min-x 0 min-y 0 width 800 height 800 color 1 1 0.9 from 1 to 200 \n".
Furthermore, for the 2 animations, we create a CustomMotionFile,
QuickSortFile and ICreateMotionFile, which is our new interface for readfile part.

We also create an AnimationStart class which we pull out duplicate code in both our visual and
interactive controller which its jobs is how to run the actionPerformed. Other than that. we create
Mock view to test if our view called successfully to the constructor and we add test for those new
added method.

Our two Custom Animation is called QuickSort and ADayInEgypt. To create the animation, create an
ICreateMotionFile object and called createFile(String name), run it with the program.

Contributors names and contact info
Khanh Nguyen
nguyen.khanh1@northeastern.edu
Khoi Ngo
ngo.kho@northeastern.edu

