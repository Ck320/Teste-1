import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * @author Maurício Sugimoto Polloni
 * 
 * Classe responsavel por detectar a data, informação
 * vinda do computador
 */


public class Time
{
	private int year;
	private int month;
	
	Calendar c = Calendar.getInstance();
	DateFormat yearFormat = new SimpleDateFormat("yyyy");
	DateFormat monthFormat = new SimpleDateFormat("MM");
	
	public Time()
	{
		year = Integer.parseInt(yearFormat.format(c.getTime()));
		month = Integer.parseInt(monthFormat.format(c.getTime()));		
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}	
	
}
