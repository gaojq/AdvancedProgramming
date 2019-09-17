/***
 * Jianqing Gao
 ***/
package hw2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

public class MergeFiles {
	public static void createDir(File newFiles) throws IOException{
		if(newFiles.isDirectory()){
			File[] list = newFiles.listFiles();
			String[] filesName = newFiles.list();
			for(int i= 0; i<list.length; i++)
				if(list[i].isDirectory()){
					File filename = new File(newFiles,filesName[i]+".txt");
					if (!filename.exists()) {//Create new file
						filename.createNewFile();
						filename.getPath();
					}
					File[] inFolder = list[i].listFiles();
					String[] inFolderPath = new String[inFolder.length];
					for(int j = 0; j<inFolder.length; j++){
						inFolderPath[j] = inFolder[j].toString();//Fetch every path
					}
					mergeFiles(filename.getPath(), inFolderPath);//Merge all files under a sensor to one file
				}
				
				
		}
	}
	
	public static final int BUFSIZE = 1024 * 8;
	
	//Merge file process
    @SuppressWarnings("resource")
	private static void mergeFiles(String outFile, String[] files) {
        FileChannel outChannel = null;
        System.out.println("Merge " + Arrays.toString(files) + " into " + outFile);
        try {
            outChannel = new FileOutputStream(outFile).getChannel();//Create out stream for saving files
            for(String f : files){
                Charset charset=Charset.forName("utf-8");
                CharsetDecoder chdecoder=charset.newDecoder();
                CharsetEncoder chencoder=charset.newEncoder();
                FileChannel fc = new FileInputStream(f).getChannel(); 
                ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
                CharBuffer charBuffer=chdecoder.decode(bb);
                ByteBuffer nbuBuffer=chencoder.encode(charBuffer);
                
                while(fc.read(nbuBuffer) != -1){
                    bb.flip(); 
                    nbuBuffer.flip();
                    outChannel.write(nbuBuffer);
                    bb.clear();
                    nbuBuffer.clear();
                }
                fc.close();
            }
            
            System.out.println("Merged");
        } 
        catch (IOException ioe) {
            ioe.printStackTrace();
        } 
        finally {
            try {
            	if (outChannel != null) {
            		outChannel.close();
            	}
            } 
            catch (IOException ignore) {}
        }
    }
}
