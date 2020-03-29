package br.com.koradi.dto.model;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

import static br.com.koradi.dto.model.ScoreDto.score;

public class UbsSerializer extends StdSerializer<UbsDto> {

  public static final String NÃO_SE_APLICA = "Não se aplica";

  public UbsSerializer() {
    super(UbsDto.class);
  }

  public UbsSerializer(Class<UbsDto> t) {
    super(t);
  }

  @Override
  public void serialize(UbsDto ubs, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonGenerationException {
    jgen.writeStartObject();
    jgen.writeStringField("id", ubs.getId());
    jgen.writeStringField("name", ubs.getName());
    jgen.writeStringField("address", ubs.getAddress() + " - " + ubs.getNeighborhood());
    jgen.writeStringField("city", ubs.getCity());
    jgen.writeStringField("phone", ubs.getPhone().equals(NÃO_SE_APLICA) ? "" : ubs.getPhone());
    jgen.writeObjectField("geocode", new GeocodeDto(ubs.getLatitude(), ubs.getLongitude()));
    jgen.writeObjectField(
        "scores",
        new ScoreDto(
            score(ubs.getSize()),
            score(ubs.getAdaptationforseniors()),
            score(ubs.getMedicalequipment()),
            score(ubs.getMedicine())));
    jgen.writeEndObject();
  }
}
