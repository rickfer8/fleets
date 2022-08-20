package com.fleets.seguros.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static java.util.Calendar.*;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.time.DateUtils.truncate;

/**
 * DateUtil.
 */
public class DateUtil {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";
    private static final TimeZone TIME_ZONE = TimeZone.getDefault();
	public static final Integer QUANTIDADE_MESES = 12;

	private DateUtil() {}

	/**
	 * Less than.
	 *
	 * @param date the date
	 * @param referenceDate the reference date
	 * @return boolean
	 */
	public static boolean lessThan(Date date, Date referenceDate) {
		if (isNull(date) || isNull(referenceDate)) {
			return false;
		}

		return date.before(referenceDate);
	}

	/**
	 * From date.
	 *
	 * @param data the data
	 * @return {@link LocalDate}
	 */
	public static LocalDate fromDate(Date data) {
		return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * New date.
	 *
	 * @param day the day
	 * @param month the month
	 * @param year the year
	 * @return {@link Date}
	 */
	public static Date newDate(int day, int month, int year) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, year);
		calendar.set(MONTH, month-1);
		calendar.set(DATE, day);

		return calendar.getTime();
	}

	/**
	 * Between.
	 *
	 * @param data the data
	 * @param dataInicio the data inicio
	 * @param dataFim the data fim
	 * @return boolean
	 */
	public static boolean between(Date data, Date dataInicio, Date dataFim) {
		return (data.equals(dataInicio) || data.equals(dataFim) ||
				(data.after(dataInicio) && data.before(dataFim)));
	}

	/**
	 * Quantidade meses entre datas.
	 *
	 * @param dataInicio
	 * @param dataFim
	 * @return {@link Integer}
	 */
	public static Integer quantidadeMesesEntreDatas(Date dataInicio, Date dataFim) {
		final Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(dataInicio);
		final Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(dataFim);

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		return diffYear * QUANTIDADE_MESES + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
	}

	/**
	 * Verifica se uma data é válida.
	 *
	 * @param date
	 * @param format
	 * @return {@link boolean}
	 */
	public static boolean isDateValid(String date, String format) {
		try {
			DateFormat df = new SimpleDateFormat(format);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Retorna o formate correspondente da data
	 * @param conteudo
	 * @return
	 */
	public static Optional<String> getFormatoData(final String conteudo){
		return Arrays.asList("dd-MM-yyyy", "dd-MMM-yyyy", DD_MM_YYYY, "dd/MMM/yyyy").stream()
				.filter( format -> DateUtil.isDateValid(conteudo, format) ).findFirst();
	}

	/**
	 * Retorna a data no padrão iso, independente do formato atual
	 */
	public static String formataDataPadraoISO(final String conteudo){
        return dateFormater(conteudo, "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	}

	public static String formataData(final String conteudo) {
        return dateFormater(conteudo, "dd/MM/yyyy");
	}

    private static String dateFormater(String conteudo, String formater){
        Optional<String> formatoData = getFormatoData(conteudo);
        try {
            if(formatoData.isPresent()){
                SimpleDateFormat formaterEntrada = new SimpleDateFormat(formatoData.get());
                SimpleDateFormat formaterSaida = new SimpleDateFormat(formater, new Locale("pt", "BR"));
                formaterSaida.setTimeZone(TIME_ZONE);
                return formaterSaida.format(formaterEntrada.parse(conteudo));
            }
            return null;
        } catch (ParseException e) {
            return null;
        }
    }

	public static String formataData(final Date date){
		SimpleDateFormat formaterSaida = new SimpleDateFormat(DD_MM_YYYY);
		formaterSaida.setTimeZone(TIME_ZONE);
		return formaterSaida.format(date);
	}

	public static String formataDataHora(final Date date){
		SimpleDateFormat formaterSaida = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		formaterSaida.setTimeZone(TIME_ZONE);
		return formaterSaida.format(date);
	}

	public static String formataHora(final Date date){
		SimpleDateFormat formaterSaida = new SimpleDateFormat("HH:mm:ss");
		formaterSaida.setTimeZone(TIME_ZONE);
		return formaterSaida.format(date);
	}

    /**
     * Metodo para retornar uma data
     * OBS: em month usar Calendar.Month
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getNewDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    public static Date truncarDate(Date data) {
    	return truncate(data, DATE);
    }
}
