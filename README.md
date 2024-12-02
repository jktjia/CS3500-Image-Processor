# Image Processor

This repository contains an program to edit images built using java swing.

Start this program with `java -jar ImageProcessing.jar`

For instructions on how to use this repository from the command line, please see [here](USEME.md).

The rest of this README is taken from the readme written for the assignment.

## Code Organization

The code is divided into the main class ImageProcessor, which contains the method main, and can run
either an interactive program or a program from a pre-existing script, and three packages, the
controller package, the model package, and the view package.

The controller package contains the interface ImageController, which has the method run which runs
the controller and allows you to load, process, and save images. ImageController is implemented in
PPMTextImageController, which loads and saves PPM images and allows you to make grayscale versions
of images based on the red, blue, green, intensity, value, and luma components of the image,
horizontally and vertically flipped versions of images, and brightened/darkened versions of images.
TextImageController also implements ImageController, and extends PPMTextImageController, but in
addition to everything PPMTextImageController can do, TextImageController can load and save PNG,
JPG, and BMP files and create sepia, grayscale, blurred, and sharpened versions of image. The class
SaveUtils contains three methods for saving StoredImages to appendables or existing files.
GuiController also implements ImageController and allows for the same functionality of
TextImageController using a GuiView for inputs.

The controller package also contains the interface Features, which has methods for properties that
a view can observe from a controller without modifying the controller and for inputting information
to the controller. GuiController implements Features.

The model package contains this program's model, a series of images stored together under user given
names, and all of the functions that can be done to the model. The interface ImageStorage is the
main model, with the methods add and get, and is implemented in MappedStorage, which stores images
in a map of the names of the images to the images. Each image is stored as a StoredImage, which has
various properties that can be gotten. StoredImage is implemented int SimpleRGBImage.

All of the functions that can be done to an ImageStorage are in the functions package and implement
the interface ImageFunction, which has one method, run, which takes in an ImageStorage and does
something to it. There are four abstract classes for the shared functions of any ImageFunction
that creates a new image in storage by transforming an existing image (AbstractTransformImage), any
ImageFunction that creates a new grayscale image based on an existing image (AbstractNewGrayscale),
ImageFunctions that create new images by applying filters (AbstractFilterImage), and ImageFunctions
that use matrix multiplication to determine new values for pixel (ex. sepia)
(AbstractColorTransformation).

The view package contains the classes and interfaces for a GUI that is compatible with a
controller that extends Features. The interface GuiView represents a Gui view for image processing
using this program. It extends the interface ActionInteracter which represents a panel class that
interacts with the controller and requires an action listener and extends ControllerInteracter. The
interface ControllerInteracter represents a panel of the gui view that interacts with the
controller. The class ValueHistogram is a JPanel that shows a histogram representing the values of
the pixels in an image. ButtonCommandPanel and ImageNamesPanel both implement ActionInteracter and
and are JPanels that contain buttons for commands for the controller to do something with and show
the names of all the images in storage and allows the user to select one of the image names
respectively. SwingGuiView implements GuiView using the aforementioned classes.

### Acknowledgements 
This code was written by Andrea Joshua and Jamie Tjia.
Source of images (cat.ppm, cat2.png, cat3.jpg, cat4.bmp) were taken by Jamie Tjia and are their property.
The other examples were created by manually creating (typing out) small ppm files.

## Updates:
### Nov 14, 2022  
Added view package with GuiView and SwingGuiView. Also added ViewableImage and ViewableImage interfaces in StoredImage so that stored images can be viewed.
### Nov 15, 2022  
Continued implementing SwingGuiView. Added ControllerInteractable and made the new classes ImageNamesPanel and ButtonCommandPanel, which implement ControllerInteractable. Also made GuiView extend it. Created Features interface and GuiController and started implementing it.
### Nov 18, 2022  
Added ValueHistogram class and finished implementing SwingGuiView and associated classes and GuiController.
### Dec 6, 2022   
Added Mosaic function and new gui controller that extends old gui controller and supports mosaic functionality.
### Dec 7, 2022   
Added Downscale function and added it to GuiControllerV3, a new gui controller that extends GuiControllerV2. Also created new text controller that extends old text controller with mosaics and downscaling. Nothing else changed.