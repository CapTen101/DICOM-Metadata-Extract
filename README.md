# DICOM-Metadata-Extract
>(Code Challenge under [caMicroscope](https://github.com/camicroscope))

> A Simple JAVA project to extract metadata out of the DICOM image files using pixelmed.jar. 

> DICOM® (Digital Imaging and Communications in Medicine) is the international standard to transmit, store, retrieve, print, process, and display medical imaging information. Inside every DICOM file, there is not only an image but some metadata too containing various kinds of information about that particular image, test, patient ID, Body part examined etc. 


This Application automatically locates the DICOM image files from the project directory.
It then extracts the necessary metadata out of them and stores it in a text file **DICOM_MetaData.txt** in the project directory itself :innocent:

Used libraries: [PixelMed JAVA DICOM Toolkit](http://www.pixelmed.com/dicomtoolkit.html)


**Ensure the below prerequisites:**
* All Dicom images must be placed in the current project directory.
* Maven is installed in your system.
(If not, run the following command)
`sudo apt install maven`


**Steps to run the application:**

* Step-1: (Clone the project)

`git clone https://github.com/CapTen101/DICOM-Metadata-Extract.git`

* Step-1: (In the project directory, build the project)

`mvn compile`

* Step-2: (Install all the necessary dependencies)

`mvn install`

* Step-3: (Run the project)

`java -cp target/DICOM-Extract.jar DICOM`

You can observe the required output.

Thanks!
