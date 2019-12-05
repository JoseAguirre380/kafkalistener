import rules.RulesExecutor;

public class Main {
    public static void main(String[] args) {
        FreightShipment freightShipment = new FreightShipment();

        RulesExecutor.executeRules("{\n" +
                "   \"domain\": \"Produccion\",\n" +
                "   \"event\": \"AltaProduccion\",\n" +
                "   \"timestamp\": \"1574386120\",\n" +
                "    \"topic\": \"prueba\",\n" +
                "   \"data\": {\n" +
                "           \"CodigoRollo\": \"3A896515CM305\",\n" +
                "           \"ID_Viaje\": \"23868949\",\n" +
                "           \"ID_Orden\": \"7444609\",\n" +
                "           \"PlantaOrigen\": \"Universidad\",\n" +
                "           \"PlantaDestino\": \"Churubusco\",\n" +
                "           \"AlmacenOrigen\": \"Decapado\",\n" +
                "           \"AlmacenDestino\": \"NA\",\n" +
                "           \"Prioridad\": \"\",\n" +
                "           \"CantidadPiezas\": \"2\",\n" +
                "           \"Transporte\": \"144835\",\n" +
                "           \"EstadoViaje\": \"0\",\n" +
                "           \"FechaSolicita\": \"20190520\",\n" +
                "           \"Material\": \"ROLLO FRIO CRUDO\",\n" +
                "           \"ID_Material\": \"\",\n" +
                "           \"Peso\": \"14.638\"\n" +
                "   }\n" +
                "}");

//        al llamar el metodo StrtConsummer kafka empieza consumir el topico el cual al final te retorna el valor que recibe de kie server
        //System.out.println("RESPUESTA DE KIE SERVER --->" + freightShipment.startConsummer("ternium", "test", "mensaje de prubea"));
    }
}
