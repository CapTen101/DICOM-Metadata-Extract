/*
 * pixelmed library are used here for the purpose.
 * @link http://www.pixelmed.com/dicomtoolkit.html
 */

import com.pixelmed.dicom.Attribute;
import com.pixelmed.dicom.AttributeList;
import com.pixelmed.dicom.AttributeTag;
import com.pixelmed.dicom.TagFromName;
import com.pixelmed.display.SourceImage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class DICOM {

    private static AttributeList list = new AttributeList();
    private static final String FILE_TEXT_EXT = ".dcm";

    public static void main(String[] args) throws IOException {

        /*
         * Below String stores the path to the current user directory.
         */

        String FILE_DIRECTORY = System.getProperty("user.dir");
        System.out.println("Your base directory is: " + FILE_DIRECTORY);

        /*
         * The follwing JSONArray stores data for every DICOM file as a seperate JSONObject
         */

        JSONArray metaDataArray = new JSONArray();

        /*
         * The follwing is the algorithm to get all the attributes available for extraction from the PixelMed Library.
         */

        Field[] tags = TagFromName.class.getFields();
        ArrayList<String> tagnames = new ArrayList<>();
        
        for (int j = 0; j < tags.length; j++) {
            try {
                String tags1 = tags[j].toString();
                String tagname = tags1.substring(83);
                tagnames.add(tagname);

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(e.toString());
                continue;
            }

            File file = new File(FILE_DIRECTORY + File.separator + "tags.txt");
            FileWriter fr1 = new FileWriter(file, false);
            BufferedWriter br1 = new BufferedWriter(fr1);
            br1.write(tagnames.toString());
            br1.close();
            fr1.close();
        }


        /*
         * Following ArrayList stores the path of all valid DICOM files in the current directory
         */

        ArrayList<String> FileList = new DICOM().listFile(FILE_DIRECTORY, FILE_TEXT_EXT);
        int ITERATION_PARAMETER = 0;

        try {
            while (ITERATION_PARAMETER < FileList.size()) {
                System.out.println("Found DICOM file: " + FileList.get(ITERATION_PARAMETER));
                list.read(FileList.get(ITERATION_PARAMETER));

                /*
                 * Following JSONObject stores the MetaData of the DICOM file
                 */

                JSONObject metaDataObject = new JSONObject();
                metaDataObject.put("Patient Name", getTagInformation(TagFromName.PatientName));
                metaDataObject.put("Patient ID", getTagInformation(TagFromName.PatientID));
                metaDataObject.put("Transfer Syntax", getTagInformation(TagFromName.TransferSyntaxUID));
                metaDataObject.put("SOP Class", getTagInformation(TagFromName.SOPClassUID));
                metaDataObject.put("Modality", getTagInformation(TagFromName.Modality));
                metaDataObject.put("Samples Per Pixel", getTagInformation(TagFromName.SamplesPerPixel));
                metaDataObject.put("Photometric Interpretation", getTagInformation(TagFromName.PhotometricInterpretation));
                metaDataObject.put("Pixel Spacing", getTagInformation(TagFromName.PixelSpacing));
                metaDataObject.put("Bits Allocated", getTagInformation(TagFromName.BitsAllocated));
                metaDataObject.put("Bits Stored", getTagInformation(TagFromName.BitsStored));
                metaDataObject.put("High Bit", getTagInformation(TagFromName.HighBit));
                SourceImage img = new com.pixelmed.display.SourceImage(list);
                metaDataObject.put("Number of frames", img.getNumberOfFrames());
                metaDataObject.put("Width", img.getWidth());                            // all frames will have same width
                metaDataObject.put("Height", img.getHeight());                          // all frames will have same height
                metaDataObject.put("Is Grayscale?", img.isGrayscale());
                metaDataObject.put("Pixel Data present?", !getTagInformation(TagFromName.PixelData).isEmpty());

                metaDataArray.put(metaDataObject);
                ++ITERATION_PARAMETER;

                /*
                 * FileWriter Object is used to write the obtained MetaData information into a text file in the same user directory
                 */

                File filedcm = new File(FILE_DIRECTORY + File.separator + "DICOM_MetaData.txt");
                FileWriter fr = new FileWriter(filedcm, true);
                BufferedWriter br = new BufferedWriter(fr);
                br.write("Patient Name: " + getTagInformation(TagFromName.PatientName) + "\n");
                br.write("Patient ID: " + getTagInformation(TagFromName.PatientID) + "\n");
                br.write("Transfer Syntax: " + getTagInformation(TagFromName.TransferSyntaxUID) + "\n");
                br.write("SOP Class: " + getTagInformation(TagFromName.SOPClassUID) + "\n");
                br.write("Modality: " + getTagInformation(TagFromName.Modality) + "\n");
                br.write("Samples Per Pixel: " + getTagInformation(TagFromName.SamplesPerPixel) + "\n");
                br.write("Photometric Interpretation: " + getTagInformation(TagFromName.PhotometricInterpretation) + "\n");
                br.write("Pixel Spacing: " + getTagInformation(TagFromName.PixelSpacing) + "\n");
                br.write("Bits Allocated: " + getTagInformation(TagFromName.BitsAllocated) + "\n");
                br.write("Bits Stored: " + getTagInformation(TagFromName.BitsStored) + "\n");
                br.write("High Bit: " + getTagInformation(TagFromName.HighBit) + "\n");
                SourceImage img1 = new com.pixelmed.display.SourceImage(list);
                br.write("Number of frames: " + img.getNumberOfFrames() + "\n");
                br.write("Width: " + img.getWidth() + "\n");// all frames will have same width
                br.write("Height: " + img.getHeight() + "\n");// all frames will have same height
                br.write("Is Grayscale?: " + img.isGrayscale() + "\n");
                br.write("Pixel Data present: " + (list.get(TagFromName.PixelData) != null) + "\n\n\n\n");

                list.clear();

//                for (int i = 0; i < FileList.size(); i++) {
//                    JSONObject parentObject = metaDataArray.getJSONObject(i);
//                    ArrayList<String> KeyList = new ArrayList<>(parentObject.keySet());
//                    Set<String> KeySet =parentObject.keySet();
//                    Iterator<String> it = KeySet.iterator();
//                    for (Iterator it = KeySet.iterator(); it.hasNext(); ) {
//                        String key = it.next().toString();
//                        String ValueofKey = parentObject.getString(key);
//                        br.write(key + ":" + ValueofKey);
//                    }
//                    while(it.hasNext()){
//                        String key = it.next();
//                        String ValueofKey = parentObject.getString(key);
//                        br.write(key + ":" + ValueofKey);
//                    }
//                    for(int j=0; j<KeyList.size(); j++){
//                        String key = KeyList.get(j);
//                        Object ValueofKey = parentObject.get(key);
//                        br.write(key + ":" + ValueofKey);
//                    }
//
//                }

                br.close();
                fr.close();

            }
            System.out.println("\n\nCheck Out the DICOM_MetaData.txt file in your project directory  :)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTagInformation(AttributeTag attrTag) {
        return Attribute.getDelimitedStringValuesOrDefault(list, attrTag, "NOT FOUND");
    }


    public ArrayList<String> listFile(String folder, String ext) {

        ArrayList<String> FileList = new ArrayList<String>();

        GenericExtensionFilter filter = new GenericExtensionFilter(ext);

        File dir = new File(folder);
        if (!dir.isDirectory()) {
            System.out.println("Directory does not exists: " + folder);
        }

        String[] list = dir.list(filter);

        assert list != null;
        if (list.length == 0) {
            System.out.println("No DICOM files found in current directory!");
        }

        for (String file : list) {
            String temp = folder + File.separator + file;
            FileList.add(temp);
        }

        return FileList;
    }

    public static class GenericExtensionFilter implements FilenameFilter {

        private String ext;

        public GenericExtensionFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext) || name.endsWith(".DCM") || name.endsWith(".DICOM"));
        }
    }
}