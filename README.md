# DICOM-Metadata-Extract (Code Challenge)
> A Simple JAVA project to extract metadata out of the DICOM image files using pixelmed.jar. 

* This Application automatically locates the DICOM image files from the project directory.
* It then extracts the necessary metadata out of them and stores it in a text file **DICOM_MetaData.txt** in the project directory.

Used libraries:
* pixelmed


**Ensure** the below prerequisites:
* All Dicom images must be placed in the current project directory.
* Maven in installed in your system.
(If not, run the following command)
`sudo apt install maven`


Steps to run the application:

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
