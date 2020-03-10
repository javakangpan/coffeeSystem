package demo.support;


import org.joda.money.Money;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * To do ...
 **/
public class MoneyFormatter implements Formatter<Money> {
    @Override
    public Money parse(String s, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(Money money, Locale locale) {
        return null;
    }
}
