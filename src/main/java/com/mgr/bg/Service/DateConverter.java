package com.mgr.bg.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    private Logger log = LoggerFactory.getLogger(DateConverter.class);

    Date convertDateFromStringToLong(String dateToConvert) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(dateToConvert);
    }

    String getCurrentDateInFormatyyyyMMddHHmmss(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
