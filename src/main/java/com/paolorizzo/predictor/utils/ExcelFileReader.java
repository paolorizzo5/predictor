package com.paolorizzo.predictor.utils;


public class ExcelFileReader {

	

	// public static Competition readAddCompetitionFile(InputStream inputStream,
	// String competitionName) throws IOException {
	//
	// Competition competition = new Competition();
	// competition.setName(competitionName);
	// HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
	// HSSFSheet sheet = workbook.getSheetAt(0);
	//
	// // Get iterator to all the rows in current sheet
	// Iterator<Row> rowIterator = sheet.iterator();
	//
	// List<Fixture> fixtures = new ArrayList<Fixture>();
	// while (rowIterator.hasNext()) {
	// Row row = rowIterator.next();
	// String homeTeam = row.getCell(0).getStringCellValue();
	// String awayTeam = row.getCell(1).getStringCellValue();
	// Date matchDate = new Date();
	// try {
	// matchDate = simpleDateFormat.parse(row.getCell(2)
	// .getStringCellValue());
	//
	// if (competition.getStartDate() == null) {
	// competition.setStartDate(matchDate);
	// }
	// competition.setEndDate(matchDate);
	//
	// } catch (ParseException e) {
	//
	// }
	//
	// Fixture fixture = new Fixture(homeTeam, awayTeam,
	// matchDate.getTime(), FixtureStatus._SCHEDULED);
	//
	// fixtures.add(fixture);
	//
	// }
	// competition.setFixtures(fixtures);
	// return competition;
	// }
	//
	// private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(
	// "dd-MM-yyyy");

}
