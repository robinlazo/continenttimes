package universalTime;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeZoneByContinent {

	private Map<String, String> worldTimeZones;
	private Map<String, String> selectedCont = new HashMap<>();

	public TimeZoneByContinent() {
		worldTimeZones = new HashMap<>();
		setAsiaTimeZone();
		setAmericaTimeZone();
		setAfricaTimeZone();
		setEuropeTimeZone();
		setOceaniaTimeZone();
	}

	private void setAsiaTimeZone() {
		String country[] = { "China", "Korea Del Sur", "Indonesia", "India", "Japon", "Tailandia", "Rusia" };
		// ST = Standard Time = All the country has the same time zone
		String countryTZ[] = { "ST", "ST", "Yakarta", "ST", "ST", "ST", "Moscow" };
		String timezone[] = { "Asia/Shanghai", "Asia/Seoul", "Asia/Jakarta", "IST", "Asia/Tokyo", "Asia/Bangkok",
				"Europe/Moscow" };

		for (int i = 0; i < country.length; i++) {
			worldTimeZones.put("Asia/" + country[i] + "/" + countryTZ[i], timezone[i]);
		}
	}

	private void setAmericaTimeZone() {
		String country[] = {"Canada", "Estados Unidos", "Mexico", "El Salvador", "Costa Rica", "Colombia", 
				"Puerto Rico", "Brazil", "Peru", "Argentina"};
		String countryTZ[] = {"Ottawa", "Washington", "Ciudad-de-Mexico", "ST", "ST", "ST", "ST", "Brasilia", "ST", "ST"};
		String timezone[] = {"Canada/Eastern", "US/Eastern", "America/Mexico_City", "America/El_Salvador", 
				"America/Costa_Rica", "America/Bogota", "America/Puerto_Rico", "Brazil/East", "America/Lima", "America/Buenos_Aires"};

		for (int i = 0; i < country.length; i++) {
			worldTimeZones.put("America/" + country[i] + "/" + countryTZ[i], timezone[i]);
		}
	}

	private void setAfricaTimeZone() {
		String country[] = {"Marruecos", "Sudafrica", "Tunez", "Argelia", "Mozambique", "Tanzania", 
				"Egipto", "Nigeria"};
		String countryTZ[] = {"ST", "ST", "ST", "ST", "ST", "ST", "ST", "ST"};
		String timezone[] = {"Africa/Casablanca", "Africa/Johannesburg", "Africa/Tunis", "Africa/Algiers", 
				             "Africa/Maputo", "Africa/Dar_es_Salaam", "Africa/Cairo", "Africa/Lagos"};
		
		for (int i = 0; i < country.length; i++) {
			worldTimeZones.put("Africa/" + country[i] + "/" + countryTZ[i], timezone[i]);
		}
	}

	private void setOceaniaTimeZone() {
		String country[] = {"Fiyi", "Australia", "Nueva Zelanda"};
		String countryTZ[] = {"ST", "Sydney", "Wellington"};
		String timezone[] = {"Pacific/Fiji", "Australia/Sydney", "NZ"};
		
		for (int i = 0; i < country.length; i++) {
			worldTimeZones.put("Oceania/" + country[i] + "/" + countryTZ[i], timezone[i]);
		}
	}

	private void setEuropeTimeZone() {
		String country[] = {"Espana", "Italia", "Francia", "Alemania", "Inglaterra", "Portugal"};
		String countryTZ[] = {"ST", "ST", "ST", "ST", "ST", "Lisboa"};
		String timezone[] = {"Europe/Madrid", "Europe/Rome", "Europe/Paris", "Europe/Berlin", 
				"Europe/London", "Europe/Lisbon"};
		
		for (int i = 0; i < country.length; i++) {
			worldTimeZones.put("Europe/" + country[i] + "/" + countryTZ[i], timezone[i]);
		}
	}

	public Map<String, String> getTimeZone(String continent) {
		Stream<Map.Entry<String, String>> TZStream = worldTimeZones.entrySet().stream();
		
		switch(continent) {
		case "America" -> selectedCont = TZStream.filter(r -> r.getKey().startsWith("America"))
		                               .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		 
		case "Africa" -> selectedCont = TZStream.filter(r -> r.getKey().startsWith("Africa"))
							          .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		
		case "Europe" -> selectedCont = TZStream.filter(r -> r.getKey().startsWith("Europe"))
							          .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		
		case "Asia" -> selectedCont = TZStream.filter(r -> r.getKey().startsWith("Asia"))
						            .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		
		case "Oceania" -> selectedCont = TZStream.filter(r -> r.getKey().startsWith("Oceania"))
			                           .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		}
	
		return selectedCont;
	}
}
