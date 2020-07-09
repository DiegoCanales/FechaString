package cl.dcnls.fechastring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class FechaString {
	// Date and Time Patterns
	// https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
	private static final String FORMATO_FECHA = "yyyy-MM-dd_HH:mm";
	
	public static Date stringToDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
		Date dateAux;
		try {
			dateAux = sdf.parse(date);
			return dateAux;
		} catch (ParseException e) {e.printStackTrace();}
		return null;
	}
	
	public static Calendar dateToCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Calendar stringToCalendar(String date){
		return dateToCalendar((stringToDate(date)));
	}
	
	public static String calendarToString(Calendar calendar){		
		SimpleDateFormat format = new SimpleDateFormat(FORMATO_FECHA);
		return format.format(calendar.getTime());
	}
	
	public static String addDays(String date,int amount){
		Calendar calendar = stringToCalendar(date);
		calendar.add(Calendar.DAY_OF_YEAR, amount);
		return FechaString.calendarToString(calendar);
	}
	
	public static String addMonths(String date, int amount){
		Calendar calendar = stringToCalendar(date);
		calendar.add(Calendar.MONTH, amount);
		return FechaString.calendarToString(calendar);
	}
	
	public static String addWeeks(String fecha, int amount){
		Calendar calendar = stringToCalendar(fecha);
		calendar.add(Calendar.WEEK_OF_YEAR, amount);
		return FechaString.calendarToString(calendar);
	}
	
	public static String addHours(String fecha, int amount){
		Calendar calendar = stringToCalendar(fecha);
		calendar.add(Calendar.HOUR_OF_DAY, amount);
		return FechaString.calendarToString(calendar);
	}
	
	public static int toCompare(String firstDate, String secondDate){
		Date firstDateAux = FechaString.stringToDate(firstDate);
		Date secondDateAux = FechaString.stringToDate(secondDate);
		if (firstDateAux.before(secondDateAux) == true){ //Si la primera fecha esta antes que la segunda 
			return -1;
		}
		if (firstDateAux.equals(secondDateAux) == true){ //Si la primera fecha es igual que la segunda
			return 0;
		}
		if (firstDateAux.after(secondDateAux) == true){  //Si la primera fecha esta despues que la segunda
			return 1;
		}
		return (Integer) null;
	}
	public static String getDayOfTheWeek(String date){
		List<String> days = Arrays.asList("Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado");
		Calendar calendar = FechaString.stringToCalendar(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return days.get(day-1);
	}
	public static int getDayOfTheYear(String date){
		Calendar calendar = FechaString.stringToCalendar(date);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		return day;
	}
	
	public static double getHourOfTheYear(String date){
		Calendar calendar = FechaString.stringToCalendar(date);
		double day = calendar.get(Calendar.DAY_OF_YEAR);
		double hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		double minuteOfHour = calendar.get(Calendar.MINUTE);
		double hourOfYear = day * 24 + hourOfDay + (minuteOfHour/60);
		return hourOfYear;
	}
	
	public static int getHourOfTheday(String date) {
		Calendar calendar = FechaString.stringToCalendar(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static String getMonth(String date){
		List<String> months = Arrays.asList("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septimbre","Octubre","Noviembre","Diciembre");
		Calendar calendar = FechaString.stringToCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		return months.get(month);
	}
	
	public static int daysBetween(String date, String otherDate){
		int day1 = FechaString.getDayOfTheYear(date);
		int day2 = FechaString.getDayOfTheYear(otherDate);
		if(day1 >= day2)
			return day1 - day2;
		else
			return day2 - day1;
	}
	
	public static double hoursBetween(String date, String otherDate){
		double hours1 = FechaString.getHourOfTheYear(date);
		double hours2 = FechaString.getHourOfTheYear(otherDate);

		if(hours1 >= hours2)
			return hours1 - hours2;
		else
			return hours2 - hours1;
	}
	
	public static String getDate(){
		Calendar calendar = Calendar.getInstance();
		return FechaString.calendarToString(calendar);
	}
	
	public static String dateToString(Date date){
		return FechaString.calendarToString(FechaString.dateToCalendar(date));
		
	}
	
	/**
	 * changeToFormat: cambia el formato de una fechastring en un formato objetivo
	 * @param originalDate
	 * @param originalFormat
	 * @param targetFormat
	 * @return
	 */
	public static String changeToFormat(String originalDate, String originalFormat, String targetFormat) {
		SimpleDateFormat of = new SimpleDateFormat(originalFormat);
		SimpleDateFormat tf = new SimpleDateFormat(targetFormat);
		try {
			Date d = of.parse(originalDate);
			return tf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * changeToFormat: cambia una fecha en formato definido en la clase por otro formato de fecha
	 * @param originalDate
	 * @param targetFormat
	 * @return
	 */
	public static String changeToFormat(String originalDate, String targetFormat) {
		return changeToFormat(originalDate, FORMATO_FECHA, targetFormat);
	}
}
