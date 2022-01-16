//package why.helloworld;
//
//import org.apache.camel.Exchange;
//import org.apache.camel.ExchangePattern;
//import org.apache.camel.Message;
//import org.apache.camel.Processor;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.impl.DefaultCamelContext;
//import org.apache.camel.model.ModelCamelContext;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public class APPHelloWorld01 extends RouteBuilder {
//
//    public static void main(String[] args) throws Exception {
//
//
//        ModelCamelContext camelContext = new DefaultCamelContext();
//        camelContext.start();
//        camelContext.addRoutes(new APPHelloWorld01());
//
//        synchronized (APPHelloWorld01.class) {
//            APPHelloWorld01.class.wait();
//        }
//    }
//
//    @Override
//    public void configure() throws Exception {
//        from("jetty:http://127.0.0.1:8282/doHelloWorld").process(new HttpProcessor())
//                .to("log:helloworld?showExchangeId=true");
//    }
//
//    public class HttpProcessor implements Processor {
//
//        @Override
//        public void process(Exchange exchange) throws Exception {
//            HttpMessage message = (HttpMessage) exchange.getIn();
//            InputStream bodyStream = (InputStream) message.getBody();
//            String inputContext = this.analysisMessage(bodyStream);
//            bodyStream.close();
//
//            if (exchange.getPattern() == ExchangePattern.InOut) {
//                Message outMessage = exchange.getOut();
//                outMessage.setBody(inputContext + " || out");
//            }
//        }
//
//        private String analysisMessage(InputStream bodyStream) throws IOException {
//            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//            byte[] contextBytes = new byte[4096];
//            int realLen;
//            while ((realLen = bodyStream.read(contextBytes, 0, 4096)) != -1) {
//                outStream.write(contextBytes, 0, realLen);
//            }
//
//            try {
//                return new String(outStream.toByteArray(), "UTF-8");
//            } finally {
//                outStream.close();
//            }
//        }
//    }
//
//}