# Running this program from the command line

Go to the src directory and compile the program with `javac *.java`

Then begin the program, use `java ImageProcessor -text`. 
After program starts to run, a menu of options to input will pop up including:
load, save, brighten, vertical flip, horizontal flip, value component. Correct syntax is provided
in menu, e.g. load must use term 'load' then supply the image path and finally give it the image a
name to in storage.

Example script for running ImageProcessor.main() (type this when program runs with no command line
arguments):
    load images/cat.ppm cat
    flip-horizontal cat horizontal
    save images/horizontal.ppm horizontal
    q
This should create a new file called horizontal.ppm in the images folder that is cat.ppm
flipped horizontally. It should look like cat.ppm flipped horizontally.

OR

You can provide `java ImageProcessor -file res/ExampleScript.txt` or the location of some other txt file containing a
script as a command line argument when running 'ImageProcessor'

OR

Running `java ImageProcessor` with no arguments causes it to run with a interactive GUI

## Valid Commands

### load 
Arguments: image-path image-name

Loads image from specified path

### save 
Arguments: image-path image-name

Saves image with given name to specified path

### red-component 
Arguments: image-name dest-image-name

Creates greyscale image with red component of image with given name

### blue-component 
Arguments: image-name dest-image-name

Creates greyscale image with blue component of image with given name

### green-component 
Arguments: image-name dest-image-name

Creates greyscale image with green component of image with given name

### value-component 
Arguments: image-name dest-image-name

Creates greyscale image with value component of image with given name

### luma-component 
Arguments: image-name dest-image-name

Creates greyscale image with luma component of image with given name

### intensity-component 
Arguments: image-name dest-image-name

Creates greyscale image with intensity-component of image with given name

### horizontal-flip 
Arguments: image-name dest-image-name

Flips an image horizontally to create a new image

### vertical-flip 
Arguments: image-name dest-image-name

Flips an image vertically to create a new image

### brighten 
Arguments: increment image-name dest-image-name

Brightens image by given increment to create a new image

### sharpen 
Arguments: image-name dest-image-name

Sharpens image

### blur 
Arguments: image-name dest-image-name

Blurs image

### grayscale 
Arguments: image-name dest-image-name

Creates a grayscale image based on luma of image with given name

### sepia 
Arguments: image-name dest-image-name

Creates a sepia version of image with given name

### mosaic 
Arguments: num-seeds image-name dest-image-name 

Creates a mosaic version of an image given the number of seeds

### downscale 
Arguments: width height image-name dest-image-name 

Downscales the image to the given width and height

### menu
Shows all valid commands for this program

### q or quit
Quits the program