package universalTime;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TimeBoard extends JPanel implements ActionListener {
	private final String continents[] = { "Select A Country", "America", "Africa", "Europe", "Asia", "Oceania" };
	private final String[] tableHeaders = { "Country", "Time", "Date" };

	private Timer timer;

	private JScrollPane scrollView;
	private JComboBox<String> contDropDown;
	private JTable timeByContinent;

	private TimeZoneByContinent continentsTZ;
	private Object datetimeCountry[][] = { { "NA", "NA", "NA" } };

	private Map<String, String> contTimeZones;
	private String selectedContinent = null;

	private DefaultTableModel tableModel;
	private final int TABLE_COLUMNS = 3;

	public TimeBoard() {
		setupBoard();
		initVariables();
		setComponents();
	}

	private void setupBoard() {
		setLayout(new BorderLayout());
		setFocusable(true);
		setPreferredSize(new Dimension(300, 300));
		timer = new Timer(1000, this);
		timer.start();
	}

	private void initVariables() {
		contDropDown = new JComboBox<String>(continents);
		contDropDown.addActionListener(e -> setSelectedContinent());
		
		continentsTZ = new TimeZoneByContinent();
		
		tableModel = new DefaultTableModel(datetimeCountry, tableHeaders);
		timeByContinent = new JTable(tableModel);
		timeByContinent.setBackground(new Color(0xf5f6fa));

		scrollView = new JScrollPane(timeByContinent);
	}

	private void setSelectedContinent() {
		selectedContinent = (String) contDropDown.getSelectedItem();
		updateContinent();
	}

	private void updateContinent() {
		int idx = 0;

		contTimeZones = continentsTZ.getTimeZone(selectedContinent);

		Calendar datetime;

		datetimeCountry = new Object[contTimeZones.size()][TABLE_COLUMNS];

		for (Map.Entry<String, String> country : contTimeZones.entrySet()) {
			datetime = Calendar.getInstance(TimeZone.getTimeZone(country.getValue()));

			String[] countryInfo = country.getKey().split("/");

			String countryName = countryInfo[1];
			String timezoneLocation = countryInfo[2];

			String date = getDateString(datetime);
			String time = getTimeString(datetime);

			datetimeCountry[idx][0] = countryName + " - " + timezoneLocation;
			datetimeCountry[idx][1] = time;
			datetimeCountry[idx][2] = date;

			idx++;
		}
		
		tableModel = new DefaultTableModel(datetimeCountry, tableHeaders);
		timeByContinent.setModel(tableModel);	
	}

	private String getDateString(Calendar date) {

		String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

		int year = date.get(Calendar.YEAR);
		String month = months[date.get(Calendar.MONTH)];
		int day = date.get(Calendar.DATE);

		String dateString = day + "/" + month + "/" + year;

		return dateString;
	}

	private String getTimeString(Calendar date) {
		int hour = date.get(Calendar.HOUR);
		int minute = date.get(Calendar.MINUTE);
		int second = date.get(Calendar.SECOND);
		String am_pm = date.get(Calendar.AM_PM) == 0 ? " a.m" : " p.m";

		String timeString = hour + ":" + minute + ":" + second + am_pm;

		return timeString;
	}

	public void setComponents() {
		add(contDropDown, BorderLayout.NORTH);
		add(scrollView, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (selectedContinent != null) updateContinent();
	}
}
