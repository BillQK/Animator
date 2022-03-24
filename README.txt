ANIMATOR (CS3500)
This program is to animate objects.

SUMMARY/OVERVIEW
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

Contributors names and contact info
Khanh Nguyen
nguyen.khanh1@northeastern.edu
Khoi Ngo
ngo.kho@northeastern.edu

