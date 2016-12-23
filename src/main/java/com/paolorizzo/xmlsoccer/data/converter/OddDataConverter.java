package com.paolorizzo.xmlsoccer.data.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.pabloo99.xmlsoccer.api.dto.GetAllOddsResultDto;
import com.paolorizzo.predictor.dto.OddDto;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Fixture;
import com.paolorizzo.predictor.xmlsoccer.hibernate.model.XmlSoccer_Odd;

public class OddDataConverter {

	public static XmlSoccer_Odd convert(GetAllOddsResultDto getAllOddsResultDto) {
		XmlSoccer_Odd xmlSoccer_Odd = new XmlSoccer_Odd();
		xmlSoccer_Odd
				.setAway(new BigDecimal(getAllOddsResultDto.getAwayOdds()));
		xmlSoccer_Odd.setBookmaker(getAllOddsResultDto.getBookmaker());
		xmlSoccer_Odd
				.setDraw(new BigDecimal(getAllOddsResultDto.getDrawOdds()));
		xmlSoccer_Odd.setFixture(new XmlSoccer_Fixture(getAllOddsResultDto
				.getFixtureMatchId()));
		xmlSoccer_Odd.setOddHandicap(new BigDecimal(getAllOddsResultDto
				.getHandicap()));
		xmlSoccer_Odd
				.setHome(new BigDecimal(getAllOddsResultDto.getHomeOdds()));
		xmlSoccer_Odd.setBetType(getAllOddsResultDto.getType());
		xmlSoccer_Odd.setUpdateDate(getAllOddsResultDto.getUpdatedDate()
				.toGregorianCalendar().getTime());

		return xmlSoccer_Odd;
	}

	public static List<OddDto> convert(List<XmlSoccer_Odd> odds) {
		List<OddDto> dtos = new ArrayList<OddDto>();
		for (XmlSoccer_Odd odd : odds) {
			OddDto dto = new OddDto();
			dto.setAway(odd.getAway());
			dto.setBookmaker(odd.getBookmaker());
			dto.setDraw(odd.getDraw());
			dto.setOddHandicap(odd.getOddHandicap());
			dto.setHome(odd.getHome());
			dto.setBetType(odd.getBetType());
			dto.setUpdateDate(odd.getUpdateDate());
			dtos.add(dto);
		}

		return dtos;
	}

}
