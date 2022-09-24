/*3 Crie uma classe Java que recebe via linha de comando o nome de um arquivo qualquer em formato JSON e o converte para os formatos CSV e XML. */
import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ConverterCSvXMl {
    public static void main(String[] args) throws Exception, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException  {
        
        Scanner scan = new Scanner(System.in);
        System.out.printf("Informe o Nome do arquivo.json para converter em Xml e Csv\n");

        File file = new File(scan.next());
        ObjectMapper Objectmapper = new ObjectMapper();
        ArrayPerfil perfil = Objectmapper.readValue(file,ArrayPerfil.class);

        File f = new File("Perfil.xml");
        XmlMapper xm = new XmlMapper();
        xm.enable(SerializationFeature.INDENT_OUTPUT);
        xm.writeValue(f, perfil);
        
        Writer writer = Files.newBufferedWriter(Paths.get("Perfil.csv"));
        StatefulBeanToCsv<ArrayPerfil> beanToCsv = new StatefulBeanToCsvBuilder<ArrayPerfil>(writer).build();
        beanToCsv.write(perfil);

        writer.flush();
        writer.close();
        scan.close();
    }
}