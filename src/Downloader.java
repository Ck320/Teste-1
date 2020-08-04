import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/*
 * @author Maur�cio Sugimoto Polloni
 * 
 * Classe principal
 * 
 * A vers�o do arquivo � detectada baseada na data (YYYYMM)
 * 
 * Todos os arquivos resultantes deste codigo foram criados na pasta do projeto
 */

public class Downloader
{	
	private String urlPath="http://www.ans.gov.br/images/stories/Plano_de_saude_e_Operadoras/tiss/Padrao_tiss/tiss3/Padrao_TISS_Componente_Organizacional_";
	private String fileName="Padrao_TISS_Componente_Organizacional_";	
		
	public Downloader(int year,int month) throws IOException
	{	
		String versionID="";
		
		if(month<10) versionID = Integer.toString(year)+"0"+Integer.toString(month)+".pdf";
		else versionID = Integer.toString(year)+Integer.toString(month)+".pdf";
		
		URL url = new URL(urlPath+versionID);
		File file = new File(fileName+versionID);
		
	    try
	    {
	    	ReadableByteChannel rbc = Channels.newChannel(url.openStream());
	 	    FileOutputStream fos = new FileOutputStream(file);
	 	    
	 	    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		    rbc.close();
	    }
	    
	    catch(FileNotFoundException e) //ocorre caso a vers�o do arquivo n�o for encontrada
	    {
	    	if(month==0) new Downloader(year-1,12);    	//decrementa o mes ou ano at� encontrar o id da ultima vers�o do arquivo
	    	else new Downloader(year,month-1);
	    }  
		
	}
	
	public Downloader(String versionID) throws IOException  //Esta fun��o � extra, caso queira baixar uma vers�o especifica do arquivo (n�o foi usada na Main)
	{
		URL url = new URL(urlPath+versionID+".pdf");
		File file = new File(fileName+versionID+".pdf");
		
		 try
		    {
		    	ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		 	    FileOutputStream fos = new FileOutputStream(file);
		 	    
		 	    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
			    rbc.close();
		    }
		 catch(FileNotFoundException e) //ocorre caso a vers�o do arquivo n�o for encontrada
		    {
		    	System.out.println("Vers�o de arquivo n�o encontrada !!");
		    	System.out.println(urlPath+versionID+".pdf");
		    }  
	}
	
	public static void main(String[] args) throws IOException {
		Time t = new Time();
		new Downloader(t.getYear(),t.getMonth());
	}
}
