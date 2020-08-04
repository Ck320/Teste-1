import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader
{	
	private String urlPath="http://www.ans.gov.br/images/stories/Plano_de_saude_e_Operadoras/tiss/Padrao_tiss/tiss3/Padrao_TISS_Componente_Organizacional_";
	private String fileName="teste.pdf";	
		
	public Downloader(int year,int month) throws IOException
	{	
		String versionID="";
		
		if(month<10) versionID = Integer.toString(year)+"0"+Integer.toString(month)+".pdf";
		else versionID = Integer.toString(year)+Integer.toString(month)+".pdf";
		
		URL url = new URL(urlPath+versionID);
		File file = new File(fileName);
		
	    try
	    {
	    	ReadableByteChannel rbc = Channels.newChannel(url.openStream());
	 	    FileOutputStream fos = new FileOutputStream(file);
	 	    
	 	    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		    rbc.close();
	    }
	    
	    catch(FileNotFoundException e)
	    {
	    	if(month==0) new Downloader(year-1,12);    	
	    	else new Downloader(year,month-1);
	    }  
		
	}
	public static void main(String[] args) throws IOException {
		Time t = new Time();
		new Downloader(t.getYear(),t.getMonth());
	}
}
