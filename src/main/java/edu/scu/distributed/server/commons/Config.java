package edu.scu.distributed.server.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import edu.scu.distributed.models.Configuration;
import java.io.File;
import java.io.IOException;
import lombok.Getter;

public class Config {

  @Getter private final Configuration configuration;

  public Config(String filename) throws IOException {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    mapper.findAndRegisterModules();
    configuration = mapper.readValue(new File(filename), Configuration.class);
  }
}
