package com.smartwork.contact;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.smartwork.contact.model.Contact;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Contact> csvToContacts(InputStream is) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            Map mapping = new HashMap();
            mapping.put("name", "name");
            mapping.put("url", "url");
            HeaderColumnNameTranslateMappingStrategy<Contact> strategy =
                    new HeaderColumnNameTranslateMappingStrategy<Contact>();
            strategy.setType(Contact.class);
            strategy.setColumnMapping(mapping);
            CSVReader csvReader = new CSVReader(new InputStreamReader(is));
            CsvToBean<Contact> csvToBean = new CsvToBean();
            csvToBean.setCsvReader(csvReader);
            csvToBean.setMappingStrategy(strategy);
            List<Contact> contactList = csvToBean.parse();

            return contactList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
