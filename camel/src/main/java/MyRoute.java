import org.apache.camel.builder.RouteBuilder;

public class MyRoute extends RouteBuilder {

  public void configure() throws Exception {
    from("ftp:myserver/folder")
        .to("activemq:queue:cheese");
  }
}